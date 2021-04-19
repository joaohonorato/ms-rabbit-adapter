package br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.broker.order.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface BrokerOutput {

    String PUBLISH_INVOICE_TRACKING_INFORMATION_AVAILABLE_WITH_WAIT = "publishDataRequestedWithWait";
    String PUBLISH_INVOICE_TRACKING_ADAPTER_OPERATION_ERROR = "publishOperationError";

    @Output(PUBLISH_INVOICE_TRACKING_ADAPTER_OPERATION_ERROR)
    MessageChannel publishInvoiceTrackingAdapterOperationError();

    @Output(PUBLISH_INVOICE_TRACKING_INFORMATION_AVAILABLE_WITH_WAIT)
    MessageChannel publishInvoiceTrackingInformationAvailableWithWait();
}
