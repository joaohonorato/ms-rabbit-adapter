package br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ItemDTO {
    @JsonProperty("line_number")
    private Integer lineNumber;
    @JsonProperty("inventory_item_code")
    private String inventoryItemCode;
    @JsonProperty("unit_price")
    private BigDecimal unitPrice;
    @JsonProperty("ordered_quantity")
    private BigDecimal orderedQuantity;
    @JsonProperty("unit_of_measure")
    private String unitOfMeasure;
    @JsonProperty("ship_from_org_code")
    private String shipFromOrgCode;
    @JsonProperty("request_date")
    private LocalDateTime requestDate;
    @JsonProperty("schedule_ship_date")
    private LocalDateTime scheduleShipDate;
    private String familia;
    private String calibre;
    private String sku;
    private String grupo;
    private String camada;
    @JsonProperty("assistance_number")
    private String assistanceNumber;
    @JsonProperty("line_discount")
    private BigDecimal lineDiscount;
    @JsonProperty("earliest_acceptable_date")
    private LocalDateTime earliestAcceptableDate;
    @JsonProperty("ambient_type")
    private String ambientType;
    @JsonProperty("ship_set")
    private String shipSet;


    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getInventoryItemCode() {
        return inventoryItemCode;
    }

    public void setInventoryItemCode(String inventoryItemCode) {
        this.inventoryItemCode = inventoryItemCode;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(BigDecimal orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getShipFromOrgCode() {
        return shipFromOrgCode;
    }

    public void setShipFromOrgCode(String shipFromOrgCode) {
        this.shipFromOrgCode = shipFromOrgCode;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDateTime getScheduleShipDate() {
        return scheduleShipDate;
    }

    public void setScheduleShipDate(LocalDateTime scheduleShipDate) {
        this.scheduleShipDate = scheduleShipDate;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getCalibre() {
        return calibre;
    }

    public void setCalibre(String calibre) {
        this.calibre = calibre;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getCamada() {
        return camada;
    }

    public void setCamada(String camada) {
        this.camada = camada;
    }

    public String getAssistanceNumber() {
        return assistanceNumber;
    }

    public void setAssistanceNumber(String assistanceNumber) {
        this.assistanceNumber = assistanceNumber;
    }

    public BigDecimal getLineDiscount() {
        return lineDiscount;
    }

    public void setLineDiscount(BigDecimal lineDiscount) {
        this.lineDiscount = lineDiscount;
    }

    public LocalDateTime getEarliestAcceptableDate() {
        return earliestAcceptableDate;
    }

    public void setEarliestAcceptableDate(LocalDateTime earliestAcceptableDate) {
        this.earliestAcceptableDate = earliestAcceptableDate;
    }

    public String getAmbientType() {
        return ambientType;
    }

    public void setAmbientType(String ambientType) {
        this.ambientType = ambientType;
    }

    public String getShipSet() {
        return shipSet;
    }

    public void setShipSet(String shipSet) {
        this.shipSet = shipSet;
    }
}
