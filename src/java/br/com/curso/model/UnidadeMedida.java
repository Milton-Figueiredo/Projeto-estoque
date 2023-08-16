
package br.com.curso.model;

public class UnidadeMedida {
    private int idUnidadeMedida;
    private String descricao;
    private String sigla;

    public UnidadeMedida() {
        this.idUnidadeMedida = 0;
        this.descricao = "";
        this.sigla = "";
    }

    public UnidadeMedida(int idUnidadeMedida, String descricao, String sigla) {
        this.idUnidadeMedida = idUnidadeMedida;
        this.descricao = descricao;
        this.sigla = sigla;
    }

    public int getIdUnidadeMedida() {
        return idUnidadeMedida;
    }

    public void setIdUnidadeMedida(int idUnidadeMedida) {
        this.idUnidadeMedida = idUnidadeMedida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    
    
}
