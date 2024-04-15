package school.sptech.democonexaoapiexterna.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnderecoApiExternaDto {

  private String cep;

  @JsonProperty(value = "uf")
  private String estado;

  @JsonProperty(value = "localidade")
  private String cidade;

  private String bairro;

  @JsonProperty(value = "logradouro")
  private String rua;

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getRua() {
    return rua;
  }

  public void setRua(String rua) {
    this.rua = rua;
  }

  @Override
  public String toString() {
    return "Endereco{" +
           "cep='" + cep + '\'' +
           ", estado='" + estado + '\'' +
           ", cidade='" + cidade + '\'' +
           ", bairro='" + bairro + '\'' +
           ", rua='" + rua + '\'' +
           '}';
  }
}
