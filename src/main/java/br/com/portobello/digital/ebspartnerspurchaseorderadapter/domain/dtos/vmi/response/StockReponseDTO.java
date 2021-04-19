package br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.vmi.response;

import java.util.List;

public class StockReponseDTO {
    private List<SupplierStockResponseDTO> supplierProductsStock;

    public StockReponseDTO() {
    }

    public List<SupplierStockResponseDTO> getSupplierProductsStock() {
        return supplierProductsStock;
    }

    public void setSupplierProductsStock(List<SupplierStockResponseDTO> supplierProductsStock) {
        this.supplierProductsStock = supplierProductsStock;
    }
}
