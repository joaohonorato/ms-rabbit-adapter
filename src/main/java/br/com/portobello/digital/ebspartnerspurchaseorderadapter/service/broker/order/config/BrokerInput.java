package br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.broker.order.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface BrokerInput {

    String EXCHANGE_PARTNERS_PURCHASE_ORDER_AVAILABLE = "subscribeExchangePartnersPurchaseOrderAvailable";

    String EXCHANGE_PARTNERS_PURCHASE_ORDER_AVAILABLE_WITH_WAIT = "subscribeExchangePartnersPurchaseOrderAvailableWithWait";

    @Input(EXCHANGE_PARTNERS_PURCHASE_ORDER_AVAILABLE)
    SubscribableChannel subscribeInvoiceTrackingInformationAvailable();

    @Input(EXCHANGE_PARTNERS_PURCHASE_ORDER_AVAILABLE_WITH_WAIT)
    SubscribableChannel subscribeInvoiceTrackingInformationAvailableWithWait();
}
