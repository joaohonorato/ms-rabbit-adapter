package br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.vmi.event;

import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.events.EventPayload;

public class SupplierStockRequestDTO extends EventPayload {

    private SupplierStockDTO supplierStock;

    public SupplierStockRequestDTO() {
    }

    public SupplierStockRequestDTO(SupplierStockDTO supplierStock) {
        this.supplierStock = supplierStock;
    }

    public SupplierStockDTO getSupplierStock() {
        return supplierStock;
    }

    public void setSupplierStock(SupplierStockDTO supplierStock) {
        this.supplierStock = supplierStock;
    }
}
