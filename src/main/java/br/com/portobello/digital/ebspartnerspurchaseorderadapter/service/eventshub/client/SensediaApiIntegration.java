package br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.eventshub.client;

import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.vmi.event.SupplierStockDTO;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.vmi.event.SupplierStockRequestDTO;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.vmi.response.StockReponseDTO;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.eventshub.config.SensediaAuthConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "SensediaOrderIntegration", url = "${app.api.baseUrl}", configuration = SensediaAuthConfig.class)
public interface SensediaApiIntegration {

    @PostMapping(value = "${app.api.stockEndpoint}")
    StockReponseDTO postStockInAPI(SupplierStockRequestDTO stockTracking);

    @GetMapping(value = "${app.api.stockLeroyEndpoint}")
    SupplierStockDTO getStockInAPI(@RequestParam("id") String id);
}
