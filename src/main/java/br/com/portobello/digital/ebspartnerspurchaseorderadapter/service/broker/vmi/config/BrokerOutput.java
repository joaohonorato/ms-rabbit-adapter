package br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.broker.vmi.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface BrokerOutput {

    String PUBLISH_INVOICE_VMI_TRACKING_INFORMATION_AVAILABLE_WITH_WAIT = "publishVMIDataRequestedWithWait";
    String PUBLISH_INVOICE_VMI_TRACKING_ADAPTER_OPERATION_ERROR = "publishVMIOperationError";

    @Output(PUBLISH_INVOICE_VMI_TRACKING_ADAPTER_OPERATION_ERROR)
    MessageChannel publishVMIInvoiceTrackingAdapterOperationError();

    @Output(PUBLISH_INVOICE_VMI_TRACKING_INFORMATION_AVAILABLE_WITH_WAIT)
    MessageChannel publishVMIInvoiceTrackingInformationAvailableWithWait();
}
