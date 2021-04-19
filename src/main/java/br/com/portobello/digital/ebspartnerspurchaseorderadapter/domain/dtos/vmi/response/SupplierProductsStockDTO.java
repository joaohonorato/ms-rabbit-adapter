package br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.vmi.response;

public class SupplierProductsStockDTO {

    private String operation;
    private String supplierTransactionId;
    private String supplierProductId;
    private String supplierProductBatch;
    private String supplierProductGroup;
    private String supplierSalesUnitMeasure;
    private String receiverFiscalId;
    private String productId;
    private String productEAN;
    private String availableStock;
    private String stockDate;
    private String minimumSalesQuantity;
    private String purchaseDistributionProfile;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getSupplierTransactionId() {
        return supplierTransactionId;
    }

    public void setSupplierTransactionId(String supplierTransactionId) {
        this.supplierTransactionId = supplierTransactionId;
    }

    public String getSupplierProductId() {
        return supplierProductId;
    }

    public void setSupplierProductId(String supplierProductId) {
        this.supplierProductId = supplierProductId;
    }

    public String getSupplierProductBatch() {
        return supplierProductBatch;
    }

    public void setSupplierProductBatch(String supplierProductBatch) {
        this.supplierProductBatch = supplierProductBatch;
    }

    public String getSupplierProductGroup() {
        return supplierProductGroup;
    }

    public void setSupplierProductGroup(String supplierProductGroup) {
        this.supplierProductGroup = supplierProductGroup;
    }

    public String getSupplierSalesUnitMeasure() {
        return supplierSalesUnitMeasure;
    }

    public void setSupplierSalesUnitMeasure(String supplierSalesUnitMeasure) {
        this.supplierSalesUnitMeasure = supplierSalesUnitMeasure;
    }

    public String getReceiverFiscalId() {
        return receiverFiscalId;
    }

    public void setReceiverFiscalId(String receiverFiscalId) {
        this.receiverFiscalId = receiverFiscalId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductEAN() {
        return productEAN;
    }

    public void setProductEAN(String productEAN) {
        this.productEAN = productEAN;
    }

    public String getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(String availableStock) {
        this.availableStock = availableStock;
    }

    public String getStockDate() {
        return stockDate;
    }

    public void setStockDate(String stockDate) {
        this.stockDate = stockDate;
    }

    public String getMinimumSalesQuantity() {
        return minimumSalesQuantity;
    }

    public void setMinimumSalesQuantity(String minimumSalesQuantity) {
        this.minimumSalesQuantity = minimumSalesQuantity;
    }

    public String getPurchaseDistributionProfile() {
        return purchaseDistributionProfile;
    }

    public void setPurchaseDistributionProfile(String purchaseDistributionProfile) {
        this.purchaseDistributionProfile = purchaseDistributionProfile;
    }
}
