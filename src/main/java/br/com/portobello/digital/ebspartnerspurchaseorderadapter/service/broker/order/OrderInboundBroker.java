package br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.broker.order;

import br.com.portobello.digital.commons.exceptions.ApplicationException;
import br.com.portobello.digital.commons.exceptions.ErrorDetail;
import br.com.portobello.digital.commons.exceptions.ErrorPayloadEvent;
import br.com.portobello.digital.commons.headers.HeaderHelper;
import br.com.portobello.digital.commons.log.MessageLog;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.order.OrderDTO;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.broker.order.config.BrokerInput;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.eventshub.client.EventsHubIntegration;
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
public class OrderInboundBroker {

    @Autowired
    OrderOutboundBroker orderOutboundBroker;
    @Autowired
    EventsHubIntegration eventsHubIntegration;
    private Logger log = LoggerFactory.getLogger(OrderInboundBroker.class);
    @Value("${app.rabbitmq.maximumNumberOfRetries:3}")
    private int maximumNumberOfRetries;
    @Value("${app.email.success}")
    private String emailAccWhenSuccess;

    @Value("${app.email.error}")
    private String emailAccWhenError;

    @StreamListener(target = BrokerInput.EXCHANGE_PARTNERS_PURCHASE_ORDER_AVAILABLE)
    public void subscribeExchangeTrackingInformationAvailable(OrderDTO event, @Headers Map<String, Object> headers) {
        startDataRequested(event, headers);
    }

    @StreamListener(target = BrokerInput.EXCHANGE_PARTNERS_PURCHASE_ORDER_AVAILABLE_WITH_WAIT)
    public void subscribeExchangeTrackingInformationAvailableWait(OrderDTO event, @Headers Map<String, Object> headers) {
        startDataRequested(event, headers);
    }

    private void startDataRequested(OrderDTO event, @Headers Map<String, Object> headers) {
        String id = event.getPayloadId();
        try {
            event.setEmailAccWhenSuccess(emailAccWhenSuccess);
            event.setEmailAccWhenError(emailAccWhenError);
            eventsHubIntegration.postInEventsHub(event);
            log.info("Post in eventsHub InvoiceTrackingInformationAvailable - Id nota fiscal: {}", event.getPayloadId());
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

    private void sendMessageForWait(OrderDTO event, Exception e, Map<String, Object> headers) {
        if (HeaderHelper.getRetryCount(headers) < maximumNumberOfRetries) {
            orderOutboundBroker.sendMessageTrackingInformationAvailableWait(event, event.getPayloadId(), headers);
        } else {
            ErrorDetail detail = new ErrorDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            sendMessageBeTrackingOperationError(event, detail, headers);
        }
    }

    private void sendMessageBeTrackingOperationError(OrderDTO event, ErrorDetail errosDetail, Map<String, Object> headers) {
        ErrorPayloadEvent<OrderDTO> error = new ErrorPayloadEvent<>(event).addErros(errosDetail);
        orderOutboundBroker.sendMessageAdapterOperationError(error, event.getPayloadId(), headers);
    }
}
