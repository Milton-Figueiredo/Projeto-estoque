package br.com.curso.model;

public class TipoProduto {
    private int idTipoProduto;
    private String descricao;

    public TipoProduto() {
        this.idTipoProduto = 0;
        this.descricao = "";
    }

    public TipoProduto(int idTipoProduto, String descricao) {
        this.idTipoProduto = idTipoProduto;
        this.descricao = descricao;
    }

    public int getIdTipoProduto() {
        return idTipoProduto;
    }

    public void setIdTipoProduto(int idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
          
}
