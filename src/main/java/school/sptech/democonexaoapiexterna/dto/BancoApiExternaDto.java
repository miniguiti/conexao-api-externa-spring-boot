package school.sptech.democonexaoapiexterna.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BancoApiExternaDto {

  @JsonProperty("ispb")
  private String ispb;

  @JsonProperty("name")
  private String nome;

  @JsonProperty("code")
  private String codigo;

  @JsonProperty("fullName")
  private String nomeCompleto;

  public String getIspb() {
    return ispb;
  }

  public void setIspb(String ispb) {
    this.ispb = ispb;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNomeCompleto() {
    return nomeCompleto;
  }

  public void setNomeCompleto(String nomeCompleto) {
    this.nomeCompleto = nomeCompleto;
  }
}
