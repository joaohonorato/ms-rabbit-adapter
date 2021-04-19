package br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.vmi.response;

import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.events.EventPayload;

public class SupplierStockResponseDTO extends EventPayload {
    private String fiscalId;
    private SupplierProductsStockDTO supplierProductsStock;
    private String returnCode;
    private String returnMessage;
    private String idConsult;

    public String getFiscalId() {
        return fiscalId;
    }

    public void setFiscalId(String fiscalId) {
        this.fiscalId = fiscalId;
    }

    public SupplierProductsStockDTO getSupplierProductsStock() {
        return supplierProductsStock;
    }

    public void setSupplierProductsStock(SupplierProductsStockDTO supplierProductsStock) {
        this.supplierProductsStock = supplierProductsStock;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getIdConsult() {
        return idConsult;
    }

    public void setIdConsult(String idConsult) {
        this.idConsult = idConsult;
    }
}
