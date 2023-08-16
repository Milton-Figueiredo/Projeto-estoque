package br.com.curso.dao;

import br.com.curso.model.TipoProduto;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoProdutoDAO implements GenericDAO{
    
    private Connection conexao;
    
    public TipoProdutoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        TipoProduto oTipoProduto = (TipoProduto) objeto;
        Boolean retorno = false;
        if(oTipoProduto.getIdTipoProduto()==0){
            retorno = this.inserir(oTipoProduto);
        }else{
            retorno = this.alterar(oTipoProduto);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        TipoProduto oTipoProduto = (TipoProduto) objeto;
        PreparedStatement stmt = null;
        String sql = "Insert into tipoproduto (descricao) values (?)";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oTipoProduto.getDescricao());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            try{
                System.out.println("Problemas ao Cadastrar Tipo produto! Erro: " + ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch (Exception e){
                System.out.println("Erro " + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        TipoProduto oTipoProduto = (TipoProduto) objeto;
        PreparedStatement stmt = null;
        String sql = "update tipoproduto set descricao = ? where idtipoproduto = ?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oTipoProduto.getDescricao());
            stmt.setInt(2, oTipoProduto.getIdTipoProduto());
            stmt.execute();
            conexao.commit();
            return true;
        }
        catch (Exception ex){
            try{
                System.out.println("Problemas ao alterar Tipo produto! Erro: " + ex.getMessage());
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
        int idTipoProduto = numero;
       PreparedStatement stmt = null;
       String sql = "delete from tipoproduto where idtipoproduto = ?";
       
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, idTipoProduto);
           stmt.execute();
           conexao.commit();
           return true;
       }catch(Exception ex){
           System.out.println("Problemas ao excluir TipoProduto! Erro: " + ex.getMessage());
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
       int idTipoProduto = numero;
       PreparedStatement stmt = null;
       ResultSet rs = null;
       TipoProduto oTipoProduto = null;
       String sql = "Select * from tipoproduto where idtipoproduto = ?";
       
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, idTipoProduto);
           rs = stmt.executeQuery();
           while(rs.next()){
               oTipoProduto = new TipoProduto();
               oTipoProduto.setIdTipoProduto(rs.getInt("idtipoproduto"));
               oTipoProduto.setDescricao(rs.getString("descricao"));
           }
           return oTipoProduto;
       }catch(Exception ex){
           System.out.println("Problemas ao carregar TipoProduto! Erro: " + ex.getMessage());
           return false;
       }
    }

    @Override
    public List<Object> listar() {
         List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select  * from tipoproduto order by idtipoproduto";
        
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                TipoProduto oTipoProduto = new TipoProduto();
                oTipoProduto.setIdTipoProduto(rs.getInt("idtipoproduto"));
                oTipoProduto.setDescricao(rs.getString("descricao"));
                resultado.add(oTipoProduto);
            }
        }catch(Exception ex){
            System.out.println("Problemas ao listar TipoProduto! Erro: " + ex.getMessage());
            
        }
        return resultado;
    }
    
}
