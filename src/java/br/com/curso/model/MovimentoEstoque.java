package br.com.curso.model;

public class MovimentoEstoque {
    private int idMovimento;
    private String entradaSaida;
    private String documento;
    private String data;
    private double quantidade;
    private double valorMovimentado;
    private Produto produto;
    private TipoMovimento tipoMovimento;
    private Funcionario funcionario;

    public MovimentoEstoque() {
        this.idMovimento = 0;
        this.entradaSaida = "";
        this.documento = "";
        this.data = "";
        this.quantidade = 0;
        this.valorMovimentado = 0;
        this.produto = new Produto();
        this.tipoMovimento = new TipoMovimento();
        this.funcionario = new Funcionario();  
    }

    public MovimentoEstoque(int idMovimento, String entradaSaida, String documento, String data, double quantidade, double valorMovimentado, Produto produto, TipoMovimento tipoMovimento, Funcionario funcionario) {
        this.idMovimento = idMovimento;
        this.entradaSaida = entradaSaida;
        this.documento = documento;
        this.data = data;
        this.quantidade = quantidade;
        this.valorMovimentado = valorMovimentado;
        this.produto = produto;
        this.tipoMovimento = tipoMovimento;
        this.funcionario = funcionario;
    }

    public int getIdMovimento() {
        return idMovimento;
    }

    public void setIdMovimento(int idMovimento) {
        this.idMovimento = idMovimento;
    }

    public String getEntradaSaida() {
        return entradaSaida;
    }

    public void setEntradaSaida(String entradaSaida) {
        this.entradaSaida = entradaSaida;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorMovimentado() {
        return valorMovimentado;
    }

    public void setValorMovimentado(double valorMovimentado) {
        this.valorMovimentado = valorMovimentado;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimeto(TipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    
    
    
}
