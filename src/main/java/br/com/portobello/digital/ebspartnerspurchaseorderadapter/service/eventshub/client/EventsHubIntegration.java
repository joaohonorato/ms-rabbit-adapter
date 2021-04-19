package br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.eventshub.client;

import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.order.OrderDTO;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.eventshub.config.SensediaAuthConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "EventsHubIntegration", url = "${app.eventsHub.baseUrl}", configuration = SensediaAuthConfig.class)
public interface EventsHubIntegration {

    @PostMapping(value = "${app.eventsHub.purchaseOrder}")
    void postInEventsHub(OrderDTO ordsTracking);
}
