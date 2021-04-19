package br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.vmi.event;

import java.math.BigDecimal;

public class SupplierRequestProductsStockDTO {

    private String operation;
    private String supplierTransactionId;
    private String supplierProductId;
    private String supplierProductBatch;
    private String supplierProductGroup;
    private String supplierSalesUnitMeasure;
    private String receiverFiscalId;
    private String productId;
    private String productEAN;
    private BigDecimal availableStock;
    private String stockDate;
    private BigDecimal minimumSalesQuantity;
    private String purchaseDistributionProfile;

    public BigDecimal getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(BigDecimal availableStock) {
        this.availableStock = availableStock;
    }

    public BigDecimal getMinimumSalesQuantity() {
        return minimumSalesQuantity;
    }

    public void setMinimumSalesQuantity(BigDecimal minimumSalesQuantity) {
        this.minimumSalesQuantity = minimumSalesQuantity;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getProductEAN() {
        return productEAN;
    }

    public void setProductEAN(String productEAN) {
        this.productEAN = productEAN;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPurchaseDistributionProfile() {
        return purchaseDistributionProfile;
    }

    public void setPurchaseDistributionProfile(String purchaseDistributionProfile) {
        this.purchaseDistributionProfile = purchaseDistributionProfile;
    }

    public String getStockDate() {
        return stockDate;
    }

    public void setStockDate(String stockDate) {
        this.stockDate = stockDate;
    }

    public String getSupplierProductId() {
        return supplierProductId;
    }

    public void setSupplierProductId(String supplierProductId) {
        this.supplierProductId = supplierProductId;
    }

    public String getSupplierSalesUnitMeasure() {
        return supplierSalesUnitMeasure;
    }

    public void setSupplierSalesUnitMeasure(String supplierSalesUnitMeasure) {
        this.supplierSalesUnitMeasure = supplierSalesUnitMeasure;
    }

    public String getSupplierTransactionId() {
        return supplierTransactionId;
    }

    public void setSupplierTransactionId(String supplierTransactionId) {
        this.supplierTransactionId = supplierTransactionId;
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

    public String getReceiverFiscalId() {
        return receiverFiscalId;
    }

    public void setReceiverFiscalId(String receiverFiscalId) {
        this.receiverFiscalId = receiverFiscalId;
    }
}
