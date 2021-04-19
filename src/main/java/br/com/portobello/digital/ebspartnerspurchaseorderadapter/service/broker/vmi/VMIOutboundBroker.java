package br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.broker.vmi;

import br.com.portobello.digital.commons.broker.GenericBrokerOutput;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.vmi.event.SupplierStockRequestDTO;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.broker.vmi.config.BrokerOutput;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@EnableBinding({BrokerOutput.class})
public class VMIOutboundBroker extends GenericBrokerOutput {

    private final BrokerOutput output;

    public VMIOutboundBroker(BrokerOutput output) {
        this.output = output;
    }

    @Override
    protected Object getPortOutbound() {
        return output;
    }

    public void sendMessageVMITrackingInformationAvailableWait(SupplierStockRequestDTO event, String id, Map<String, Object> headers) {
        buildSendBusinessMessage(output.publishVMIInvoiceTrackingInformationAvailableWithWait(), event, id,
                BrokerOutput.PUBLISH_INVOICE_VMI_TRACKING_INFORMATION_AVAILABLE_WITH_WAIT, headers);
    }

    public void sendMessageVMIAdapterOperationError(Object eventErrorPayload, String id, Map<String, Object> headers) {
        buildSendBusinessMessage(output.publishVMIInvoiceTrackingAdapterOperationError(), eventErrorPayload, id,
                BrokerOutput.PUBLISH_INVOICE_VMI_TRACKING_ADAPTER_OPERATION_ERROR, headers);
    }

}