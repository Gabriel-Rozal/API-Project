package Models;
public class QueryCep{
  String cep;
  String logradouro;
  String complemento;
  String bairro;
  String localidade;
  String uf;

  @Override
  public String toString() {
    return
        "cep=" + cep +
        ", logradouro='" + logradouro + '\'' +
        ", complemento=" + complemento +
        ", bairro='" + bairro + '\'' +
        ", localidade='" + localidade + '\'' +
        ", uf='" + uf + '\'' +
        '}';
  }

  public QueryCep (Endereco converter) {
    this.cep = converter.cep();
    this.logradouro = converter.logradouro();
    this.complemento = converter.complemento();
    this.bairro = converter.bairro();
    this.localidade = converter.localidade();
    this.uf = converter.uf();


  }
}
