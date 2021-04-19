package br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.broker.vmi;

import br.com.portobello.digital.commons.exceptions.ApplicationException;
import br.com.portobello.digital.commons.exceptions.ErrorDetail;
import br.com.portobello.digital.commons.exceptions.ErrorPayloadEvent;
import br.com.portobello.digital.commons.headers.HeaderHelper;
import br.com.portobello.digital.commons.log.MessageLog;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.vmi.event.SupplierStockRequestDTO;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.vmi.response.StockReponseDTO;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.vmi.response.SupplierStockResponseDTO;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.broker.vmi.config.BrokerInput;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.eventshub.client.SensediaApiIntegration;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.Headers;

import javax.validation.ConstraintViolationException;
import java.util.Map;

@EnableBinding(BrokerInput.class)
public class VMIInboundBroker {

    private Logger log = LoggerFactory.getLogger(VMIInboundBroker.class);

    @Value("${app.rabbitmq.maximumNumberOfRetries:3}")
    private int maximumNumberOfRetries;

    @Autowired
    private VMIOutboundBroker VMIOutboundBroker;

    @Autowired
    private SensediaApiIntegration apiIntegration;

    @StreamListener(target = BrokerInput.EXCHANGE_VMI_STOCK_AVAILABLE)
    public void subscribeVMIExchangeTrackingInformationAvailable(SupplierStockRequestDTO event, @Headers Map<String, Object> headers) {
        startDataRequested(event, headers);
    }

    @StreamListener(target = BrokerInput.EXCHANGE_VMI_STOCK_AVAILABLE_WITH_WAIT)
    public void subscribeVMIExchangeTrackingInformationAvailableWait(SupplierStockRequestDTO event, @Headers Map<String, Object> headers) {
        startDataRequested(event, headers);
    }

    private void startDataRequested(SupplierStockRequestDTO event, @Headers Map<String, Object> headers) {
        String id = event.getPayloadId();
        try {
            process(event);
            log.info("Post in Sensedia API VMI Stock - Data: {}", event.getPayloadId());
        } catch (ApplicationException e) {
            log.warn(MessageLog.builder(id, headers).message(e.getMessage()).payload(event).build(), e);
            sendMessageBeTrackingOperationError(event, e.getErrorDetail(), headers);
        } catch (ConstraintViolationException e) {
            log.warn(MessageLog.builder(id, headers).message(e.getMessage()).payload(event).build(), e);
            sendMessageBeTrackingOperationError(event, new ErrorDetail(HttpStatus.BAD_REQUEST, e.getMessage()), headers);
        } catch (Exception e) {
            log.error(MessageLog.builder(id, headers).message(e.getMessage()).payload(event).build(), e);
            sendMessageForWait(event, e, headers);
        }
    }

    private void process(SupplierStockRequestDTO event) {
        StockReponseDTO stockResponseDTO = apiIntegration.postStockInAPI(event);
        SupplierStockResponseDTO product = stockResponseDTO.getSupplierProductsStock().get(0);
        try {
            apiIntegration.getStockInAPI(product.getIdConsult());
        } catch (FeignException e) {
            log.error("Could not retrieve Status of product id, {}", product.getIdConsult(), e);
        }
    }

    private void sendMessageForWait(SupplierStockRequestDTO event, Exception e, Map<String, Object> headers) {
        if (HeaderHelper.getRetryCount(headers) < maximumNumberOfRetries) {
            VMIOutboundBroker.sendMessageVMITrackingInformationAvailableWait(event, event.getPayloadId(), headers);
        } else {
            ErrorDetail detail = new ErrorDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            sendMessageBeTrackingOperationError(event, detail, headers);
        }
    }

    private void sendMessageBeTrackingOperationError(SupplierStockRequestDTO event, ErrorDetail errosDetail, Map<String, Object> headers) {
        ErrorPayloadEvent<SupplierStockRequestDTO> error = new ErrorPayloadEvent<>(event).addErros(errosDetail);
        VMIOutboundBroker.sendMessageVMIAdapterOperationError(error, event.getPayloadId(), headers);
    }
}
