package school.sptech.democonexaoapiexterna.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BancoDto {

  private String ispb;
  private String nome;
  private String codigo;
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

  @Override
  public String toString() {
    return "BancoDto{" +
            "ispb='" + ispb + '\'' +
            ", nome='" + nome + '\'' +
            ", codigo='" + codigo + '\'' +
            ", nomeCompleto='" + nomeCompleto + '\'' +
            '}';
  }
}
