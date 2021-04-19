package br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.dtos.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class EntregaDTO {
    @JsonProperty("ds_contato")
    private String descricaoContato;
    @JsonProperty("cd_area_contato")
    private Integer codigoAreaContato;
    @JsonProperty("nr_telefone_contato")
    private Integer numeroTelefoneContato;
    @JsonProperty("id_local_entrega")
    private Long idLocalEntrega;
    @JsonProperty("hr_entrega_inicio")
    private LocalDateTime horaEntregaInicio;
    @JsonProperty("hr_entrega_final")
    private LocalDateTime horaEntregaFinal;
    @JsonProperty("id_acessa_caminhao")
    private Long idAcessaCaminhao;
    @JsonProperty("ds_ponto_referencia")
    private String descricaoPontoReferencia;
    @JsonProperty("qt_dia_entrega_cliente")
    private Integer quantidadeDiaEntregaCliente;
    @JsonProperty("ds_armazenar")
    private String descricaoArmazenar;
    @JsonProperty("ds_dificuldade")
    private String descricacaoDificuldade;
    @JsonProperty("ds_comentario")
    private String descricaoComentario;
    @JsonProperty("ds_email")
    private String descricaoEmail;
    @JsonProperty("cd_area_contato2")
    private Long codigoAreaContato2;
    @JsonProperty("nr_telefone_contato2")
    private Integer numeroTelefoneContato2;

    public String getDescricaoContato() {
        return descricaoContato;
    }

    public void setDescricaoContato(String descricaoContato) {
        this.descricaoContato = descricaoContato;
    }

    public Integer getCodigoAreaContato() {
        return codigoAreaContato;
    }

    public void setCodigoAreaContato(Integer codigoAreaContato) {
        this.codigoAreaContato = codigoAreaContato;
    }

    public Integer getNumeroTelefoneContato() {
        return numeroTelefoneContato;
    }

    public void setNumeroTelefoneContato(Integer numeroTelefoneContato) {
        this.numeroTelefoneContato = numeroTelefoneContato;
    }

    public Long getIdLocalEntrega() {
        return idLocalEntrega;
    }

    public void setIdLocalEntrega(Long idLocalEntrega) {
        this.idLocalEntrega = idLocalEntrega;
    }

    public LocalDateTime getHoraEntregaInicio() {
        return horaEntregaInicio;
    }

    public void setHoraEntregaInicio(LocalDateTime horaEntregaInicio) {
        this.horaEntregaInicio = horaEntregaInicio;
    }

    public LocalDateTime getHoraEntregaFinal() {
        return horaEntregaFinal;
    }

    public void setHoraEntregaFinal(LocalDateTime horaEntregaFinal) {
        this.horaEntregaFinal = horaEntregaFinal;
    }

    public Long getIdAcessaCaminhao() {
        return idAcessaCaminhao;
    }

    public void setIdAcessaCaminhao(Long idAcessaCaminhao) {
        this.idAcessaCaminhao = idAcessaCaminhao;
    }

    public String getDescricaoPontoReferencia() {
        return descricaoPontoReferencia;
    }

    public void setDescricaoPontoReferencia(String descricaoPontoReferencia) {
        this.descricaoPontoReferencia = descricaoPontoReferencia;
    }

    public Integer getQuantidadeDiaEntregaCliente() {
        return quantidadeDiaEntregaCliente;
    }

    public void setQuantidadeDiaEntregaCliente(Integer quantidadeDiaEntregaCliente) {
        this.quantidadeDiaEntregaCliente = quantidadeDiaEntregaCliente;
    }

    public String getDescricaoArmazenar() {
        return descricaoArmazenar;
    }

    public void setDescricaoArmazenar(String descricaoArmazenar) {
        this.descricaoArmazenar = descricaoArmazenar;
    }

    public String getDescricacaoDificuldade() {
        return descricacaoDificuldade;
    }

    public void setDescricacaoDificuldade(String descricacaoDificuldade) {
        this.descricacaoDificuldade = descricacaoDificuldade;
    }

    public String getDescricaoComentario() {
        return descricaoComentario;
    }

    public void setDescricaoComentario(String descricaoComentario) {
        this.descricaoComentario = descricaoComentario;
    }

    public String getDescricaoEmail() {
        return descricaoEmail;
    }

    public void setDescricaoEmail(String descricaoEmail) {
        this.descricaoEmail = descricaoEmail;
    }

    public Long getCodigoAreaContato2() {
        return codigoAreaContato2;
    }

    public void setCodigoAreaContato2(Long codigoAreaContato2) {
        this.codigoAreaContato2 = codigoAreaContato2;
    }

    public Integer getNumeroTelefoneContato2() {
        return numeroTelefoneContato2;
    }

    public void setNumeroTelefoneContato2(Integer numeroTelefoneContato2) {
        this.numeroTelefoneContato2 = numeroTelefoneContato2;
    }
}
