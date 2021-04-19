package br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.vmi.event;

import java.util.List;

public class SupplierStockDTO {
    private List<SupplierRequestProductsStockDTO> supplierProductsStock;

    public SupplierStockDTO() {
    }

    public SupplierStockDTO(List<SupplierRequestProductsStockDTO> supplierProductsStock) {
        this.supplierProductsStock = supplierProductsStock;
    }

    public List<SupplierRequestProductsStockDTO> getSupplierProductsStock() {
        return supplierProductsStock;
    }

    public void setSupplierProductsStock(List<SupplierRequestProductsStockDTO> supplierProductsStock) {
        this.supplierProductsStock = supplierProductsStock;
    }

}
