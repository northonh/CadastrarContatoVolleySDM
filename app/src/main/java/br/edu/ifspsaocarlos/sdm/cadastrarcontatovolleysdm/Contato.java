package br.edu.ifspsaocarlos.sdm.cadastrarcontatovolleysdm;

public class Contato {
    /* nome_completo não segue o padrão de nomenclatura por causa do WS. O nome deve ser assim
    * para que seja possível o Reflection com GSON */
    private String nome_completo;
    private String apelido;
    private String id;

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
