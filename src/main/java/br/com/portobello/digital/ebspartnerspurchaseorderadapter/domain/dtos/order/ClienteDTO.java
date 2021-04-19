package br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteDTO {
    @JsonProperty("sold_to_fiscal_id")
    private String soldToFiscalId;
    @JsonProperty("cust_ind_type")
    private Long customIndType;
    @JsonProperty("cust_reg_number")
    private String customRegNumber;
    @JsonProperty("cust_state_inscription")
    private String customStateInscription;
    @JsonProperty("cust_city_inscription")
    private String customCityInscription;
    @JsonProperty("cust_suframa_inscription")
    private String customSuframaInscription;
    @JsonProperty("cust_name")
    private String customName;
    @JsonProperty("cust_zip_code")
    private String customZipCode;
    @JsonProperty("cust_address1")
    private String customAddress1;
    @JsonProperty("cust_address2")
    private Integer customAddress2;
    @JsonProperty("cust_address3")
    private String customAddress3;
    @JsonProperty("cust_address4")
    private String customAddress4;
    @JsonProperty("cust_city")
    private String customCity;
    @JsonProperty("cust_state")
    private String customState;
    @JsonProperty("cust_country")
    private String customCountry;
    @JsonProperty("cust_city_ibge")
    private String customCityIbge;
    @JsonProperty("cust_state_ibge")
    private String customStateIbge;
    @JsonProperty("cust_phone_number")
    private String customPhoneNumber;
    @JsonProperty("packing_instructions")
    private String packingInstructions;
    @JsonProperty("shipping_instructions")
    private String shippingInstructions;
    @JsonProperty("invoice_instructions")
    private String invoiceInstructions;
    @JsonProperty("customer_preference_set_code")
    private String customerPreferenceSetCode;

    public String getSoldToFiscalId() {
        return soldToFiscalId;
    }

    public void setSoldToFiscalId(String soldToFiscalId) {
        this.soldToFiscalId = soldToFiscalId;
    }

    public Long getCustomIndType() {
        return customIndType;
    }

    public void setCustomIndType(Long customIndType) {
        this.customIndType = customIndType;
    }

    public String getCustomRegNumber() {
        return customRegNumber;
    }

    public void setCustomRegNumber(String customRegNumber) {
        this.customRegNumber = customRegNumber;
    }

    public String getCustomStateInscription() {
        return customStateInscription;
    }

    public void setCustomStateInscription(String customStateInscription) {
        this.customStateInscription = customStateInscription;
    }

    public String getCustomCityInscription() {
        return customCityInscription;
    }

    public void setCustomCityInscription(String customCityInscription) {
        this.customCityInscription = customCityInscription;
    }

    public String getCustomSuframaInscription() {
        return customSuframaInscription;
    }

    public void setCustomSuframaInscription(String customSuframaInscription) {
        this.customSuframaInscription = customSuframaInscription;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getCustomZipCode() {
        return customZipCode;
    }

    public void setCustomZipCode(String customZipCode) {
        this.customZipCode = customZipCode;
    }

    public String getCustomAddress1() {
        return customAddress1;
    }

    public void setCustomAddress1(String customAddress1) {
        this.customAddress1 = customAddress1;
    }

    public Integer getCustomAddress2() {
        return customAddress2;
    }

    public void setCustomAddress2(Integer customAddress2) {
        this.customAddress2 = customAddress2;
    }

    public String getCustomAddress3() {
        return customAddress3;
    }

    public void setCustomAddress3(String customAddress3) {
        this.customAddress3 = customAddress3;
    }

    public String getCustomAddress4() {
        return customAddress4;
    }

    public void setCustomAddress4(String customAddress4) {
        this.customAddress4 = customAddress4;
    }

    public String getCustomCity() {
        return customCity;
    }

    public void setCustomCity(String customCity) {
        this.customCity = customCity;
    }

    public String getCustomState() {
        return customState;
    }

    public void setCustomState(String customState) {
        this.customState = customState;
    }

    public String getCustomCountry() {
        return customCountry;
    }

    public void setCustomCountry(String customCountry) {
        this.customCountry = customCountry;
    }

    public String getCustomCityIbge() {
        return customCityIbge;
    }

    public void setCustomCityIbge(String customCityIbge) {
        this.customCityIbge = customCityIbge;
    }

    public String getCustomStateIbge() {
        return customStateIbge;
    }

    public void setCustomStateIbge(String customStateIbge) {
        this.customStateIbge = customStateIbge;
    }

    public String getCustomPhoneNumber() {
        return customPhoneNumber;
    }

    public void setCustomPhoneNumber(String customPhoneNumber) {
        this.customPhoneNumber = customPhoneNumber;
    }

    public String getPackingInstructions() {
        return packingInstructions;
    }

    public void setPackingInstructions(String packingInstructions) {
        this.packingInstructions = packingInstructions;
    }

    public String getShippingInstructions() {
        return shippingInstructions;
    }

    public void setShippingInstructions(String shippingInstructions) {
        this.shippingInstructions = shippingInstructions;
    }

    public String getInvoiceInstructions() {
        return invoiceInstructions;
    }

    public void setInvoiceInstructions(String invoiceInstructions) {
        this.invoiceInstructions = invoiceInstructions;
    }

    public String getCustomerPreferenceSetCode() {
        return customerPreferenceSetCode;
    }

    public void setCustomerPreferenceSetCode(String customerPreferenceSetCode) {
        this.customerPreferenceSetCode = customerPreferenceSetCode;
    }
}
