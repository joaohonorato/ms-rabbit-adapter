package br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.broker.order;

import br.com.portobello.digital.commons.broker.GenericBrokerOutput;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.order.OrderDTO;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.broker.order.config.BrokerOutput;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@EnableBinding({BrokerOutput.class})
public class OrderOutboundBroker extends GenericBrokerOutput {

    private final BrokerOutput output;

    public OrderOutboundBroker(BrokerOutput output) {
        this.output = output;
    }

    @Override
    protected Object getPortOutbound() {
        return output;
    }

    public void sendMessageTrackingInformationAvailableWait(OrderDTO event, String id, Map<String, Object> headers) {
        buildSendBusinessMessage(output.publishInvoiceTrackingInformationAvailableWithWait(), event, id,
                BrokerOutput.PUBLISH_INVOICE_TRACKING_INFORMATION_AVAILABLE_WITH_WAIT, headers);
    }

    public void sendMessageAdapterOperationError(Object eventErrorPayload, String id, Map<String, Object> headers) {
        buildSendBusinessMessage(output.publishInvoiceTrackingAdapterOperationError(), eventErrorPayload, id,
                BrokerOutput.PUBLISH_INVOICE_TRACKING_ADAPTER_OPERATION_ERROR, headers);
    }

}