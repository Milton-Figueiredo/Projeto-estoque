
package br.com.curso.model;

public class TipoMovimento {
    private int idTipoMovimento;
    private String descricao;

    public TipoMovimento() {
        this.idTipoMovimento = 0;
        this.descricao = "";
    }

    public TipoMovimento(int idTipoMovimento, String descricao) {
        this.idTipoMovimento = idTipoMovimento;
        this.descricao = descricao;
    }

    public int getIdTipoMovimento() {
        return idTipoMovimento;
    }

    public void setIdTipoMovimento(int idTipoMovimento) {
        this.idTipoMovimento = idTipoMovimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
    
}
