package br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.order;

import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.events.EventPayload;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO extends EventPayload {
    @JsonProperty("cust_po_number")
    private String customPurchaseOrderNumber;
    @JsonProperty("cust_po_number2")
    private String customPurchaseOrderNumber2;
    @JsonProperty("cust_so_number")
    private String customSalesOrderNumber;
    @JsonProperty("order_type")
    private String orderType;
    @JsonProperty("currence_code")
    private String currenceCode;
    @JsonProperty("pricing_date")
    private LocalDateTime pricingDate;
    @JsonProperty("request_date")
    private LocalDateTime requestDate;
    @JsonProperty("domestic_foreign_ind")
    private String domesticForeignInd;
    @JsonProperty("sold_from_fiscal_id")
    private String soldFromFiscalId;
    @JsonProperty("order_source")
    private String orderSource;
    @JsonProperty("ship_set_code")
    private String shipSetCode;
    @JsonProperty("payment_term_code")
    private String paymentTermCode;
    @JsonProperty("source_hdr_id")
    private String sourceHdrId;
    @JsonProperty("cliente")
    private ClienteDTO cliente;
    @JsonProperty("entrega")
    private EntregaDTO entrega;
    @JsonProperty("items")
    private List<ItemDTO> items;
    private String emailAccWhenSuccess;
    private String emailAccWhenError;

    public String getCustomPurchaseOrderNumber() {
        return customPurchaseOrderNumber;
    }

    public void setCustomPurchaseOrderNumber(String customPurchaseOrderNumber) {
        this.customPurchaseOrderNumber = customPurchaseOrderNumber;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCurrenceCode() {
        return currenceCode;
    }

    public void setCurrenceCode(String currenceCode) {
        this.currenceCode = currenceCode;
    }

    public LocalDateTime getPricingDate() {
        return pricingDate;
    }

    public void setPricingDate(LocalDateTime pricingDate) {
        this.pricingDate = pricingDate;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public String getDomesticForeignInd() {
        return domesticForeignInd;
    }

    public void setDomesticForeignInd(String domesticForeignInd) {
        this.domesticForeignInd = domesticForeignInd;
    }

    public String getSoldFromFiscalId() {
        return soldFromFiscalId;
    }

    public void setSoldFromFiscalId(String soldFromFiscalId) {
        this.soldFromFiscalId = soldFromFiscalId;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public EntregaDTO getEntrega() {
        return entrega;
    }

    public void setEntrega(EntregaDTO entrega) {
        this.entrega = entrega;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public String getShipSetCode() {
        return shipSetCode;
    }

    public void setShipSetCode(String shipSetCode) {
        this.shipSetCode = shipSetCode;
    }

    public String getPaymentTermCode() {
        return paymentTermCode;
    }

    public void setPaymentTermCode(String paymentTermCode) {
        this.paymentTermCode = paymentTermCode;
    }

    public String getEmailAccWhenSuccess() {
        return emailAccWhenSuccess;
    }

    public void setEmailAccWhenSuccess(String emailAccWhenSuccess) {
        this.emailAccWhenSuccess = emailAccWhenSuccess;
    }

    public String getEmailAccWhenError() {
        return emailAccWhenError;
    }

    public void setEmailAccWhenError(String emailAccWhenError) {
        this.emailAccWhenError = emailAccWhenError;
    }

    public String getCustomPurchaseOrderNumber2() {
        return customPurchaseOrderNumber2;
    }

    public void setCustomPurchaseOrderNumber2(String customPurchaseOrderNumber2) {
        this.customPurchaseOrderNumber2 = customPurchaseOrderNumber2;
    }

    public String getCustomSalesOrderNumber() {
        return customSalesOrderNumber;
    }

    public void setCustomSalesOrderNumber(String customSalesOrderNumber) {
        this.customSalesOrderNumber = customSalesOrderNumber;
    }

    public String getSourceHdrId() {
        return sourceHdrId;
    }

    public void setSourceHdrId(String sourceHdrId) {
        this.sourceHdrId = sourceHdrId;
    }
}