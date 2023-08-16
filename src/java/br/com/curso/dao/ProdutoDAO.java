package br.com.curso.dao;

import br.com.curso.model.Produto;
import br.com.curso.model.TipoProduto;
import br.com.curso.model.UnidadeMedida;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements GenericDAO {
    
    private Connection conexao;
    
    public ProdutoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Produto oProduto = (Produto) objeto;
        Boolean retorno = false;
        if(oProduto.getIdProduto()==0){
            retorno = this.inserir(oProduto);
        }else{
            retorno = this.alterar(oProduto);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Produto oProduto = (Produto) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into produto (nomeproduto, ultimoprecopago, saldoatual, idtipoproduto, idunidademedida) values (?, ?, ?, ?, ?)";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProduto.getNomeProduto());
            stmt.setDouble(2, oProduto.getUltimoPrecoPago());
            stmt.setDouble(3, oProduto.getSaldoAtual());
            stmt.setInt(4, oProduto.getTipoProduto().getIdTipoProduto());
            stmt.setInt(5, oProduto.getUnidadeMedida().getIdUnidadeMedida());
            stmt.execute();
            return true;
        }catch(Exception ex){
            try{
                System.out.println("Problemas ao cadastrar a Produto! Erro: " + ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch(Exception e){
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Produto oProduto = (Produto) objeto;
        PreparedStatement stmt = null;
        String sql = "update produto set nomeproduto = ?, ultimoprecopago = ?, saldoatual = ?, idtipoproduto = ?, idunidademedida = ? where idproduto = ?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProduto.getNomeProduto());
            stmt.setDouble(2, oProduto.getUltimoPrecoPago());
            stmt.setDouble(3, oProduto.getSaldoAtual());
            stmt.setInt(4, oProduto.getTipoProduto().getIdTipoProduto());
            stmt.setInt(5, oProduto.getUnidadeMedida().getIdUnidadeMedida());
            stmt.setInt(6, oProduto.getIdProduto());
            stmt.execute();
            return true;
        }catch(Exception ex){
            try{
                System.out.println("Problemas ao alterar Produto! Erro: " + ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch(Exception e){
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        int idProduto = numero;
        PreparedStatement stmt = null;
        String sql = "delete from produto idproduto = ?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception ex){
            System.out.println("Problemas ao Excluir Produto! Erro: " + ex.getMessage());
            try{
                conexao.rollback();
            }catch(Exception e){
                System.out.println("Erro rollback: " + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Object carregar(int numero) {
        int idProduto = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto oProduto = null;
        String sql = "select * from produto where idproduto = ?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            rs = stmt.executeQuery();
            while(rs.next()){
                oProduto = new Produto();
                oProduto.setIdProduto(rs.getInt("idproduto"));
                oProduto.setNomeProduto(rs.getString("nomeproduto"));
                oProduto.setUltimoPrecoPago(rs.getDouble("ultimoprecopago"));
                oProduto.setSaldoAtual(rs.getDouble("saldoatual"));
                
                TipoProdutoDAO oTipoProdutoDAO = new TipoProdutoDAO();
                oProduto.setTipoProduto((TipoProduto) oTipoProdutoDAO.carregar(rs.getInt("idtipoproduto")));
                
                UnidadeMedidaDAO oUnidadeMedidaDAO = new UnidadeMedidaDAO();
                oProduto.setUnidadeMedida((UnidadeMedida) oUnidadeMedidaDAO.carregar(rs.getInt("idunidademedida")));
            }
            return oProduto;
        }catch(Exception ex){
            System.out.println("Problemas ao carregar Produto! Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select * from produto order by idproduto";
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Produto oProduto = new Produto();
                oProduto.setIdProduto(rs.getInt("idproduto"));
                oProduto.setNomeProduto(rs.getString("nomeproduto"));
                oProduto.setUltimoPrecoPago(rs.getDouble("ultimoprecopago"));
                oProduto.setSaldoAtual(rs.getDouble("saldoatual"));
                
                TipoProdutoDAO oTipoProdutoDAO = null;
                try{
                    oTipoProdutoDAO = new TipoProdutoDAO();
                }catch(Exception ex){
                    System.out.println("Erro ao buscar tipo Produto: " +ex.getMessage());
                    ex.printStackTrace();
                }
                oProduto.setTipoProduto((TipoProduto) oTipoProdutoDAO.carregar(rs.getInt("idtipoproduto")));

                UnidadeMedidaDAO oUnidadeMedidaDAO = null;
                try{
                    oUnidadeMedidaDAO = new UnidadeMedidaDAO();
                }catch(Exception ex){
                    System.out.println("Erro ao buscar unidade medida: " +ex.getMessage());
                    ex.printStackTrace();
                }
                 oProduto.setUnidadeMedida((UnidadeMedida) oUnidadeMedidaDAO.carregar(rs.getInt("idunidademedida")));
                
                resultado.add(oProduto);
            }
        }catch(Exception ex){
            System.out.println("Problemas ao listar Produto! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
        return resultado;
    }
    
}
