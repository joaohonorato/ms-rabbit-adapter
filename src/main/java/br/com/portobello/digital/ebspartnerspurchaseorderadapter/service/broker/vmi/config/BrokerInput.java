package br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.broker.vmi.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface BrokerInput {

    String EXCHANGE_VMI_STOCK_AVAILABLE = "subscribeExchangeVMIStockAvailable";

    String EXCHANGE_VMI_STOCK_AVAILABLE_WITH_WAIT = "subscribeExchangeVMIStockAvailableWithWait";

    @Input(EXCHANGE_VMI_STOCK_AVAILABLE)
    SubscribableChannel subscribeInvoiceTrackingInformationAvailable();

    @Input(EXCHANGE_VMI_STOCK_AVAILABLE_WITH_WAIT)
    SubscribableChannel subscribeInvoiceTrackingInformationAvailableWithWait();
}
