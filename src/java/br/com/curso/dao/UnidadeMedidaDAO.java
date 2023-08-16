package br.com.curso.dao;

import br.com.curso.model.UnidadeMedida;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UnidadeMedidaDAO implements GenericDAO {
    
    private Connection conexao;
    
    public UnidadeMedidaDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
       UnidadeMedida oUnidadeMedida = (UnidadeMedida) objeto;
       Boolean retorno = false;
       if(oUnidadeMedida.getIdUnidadeMedida()==0){
           retorno = this.inserir(oUnidadeMedida);
       }else{
           retorno = this.alterar(oUnidadeMedida);
       }
       return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        UnidadeMedida oUnidadeMedida = (UnidadeMedida) objeto;
        PreparedStatement stmt = null;
        String sql = "Insert into unidademedida (descricao, sigla) values (?, ?)";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oUnidadeMedida.getDescricao());
            stmt.setString(2, oUnidadeMedida.getSigla());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            try{
                System.out.println("Probleamas ao cadastrar UnidadeMedida! Erro: " +  ex.getMessage());
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
       UnidadeMedida oUnidadeMedida = (UnidadeMedida) objeto;
       PreparedStatement stmt = null;
       String sql = "update unidademedida set descricao = ?, sigla = ? where idunidademedida = ?";
       
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setString(1, oUnidadeMedida.getDescricao());
           stmt.setString(2, oUnidadeMedida.getSigla());
           stmt.setInt(3, oUnidadeMedida.getIdUnidadeMedida());
           stmt.execute();
           conexao.commit();
           return true;
       }catch(Exception ex){
           try{
               System.out.println("Problemas ao alterar UnidadeMedida! Erro: " + ex.getMessage());
               ex.printStackTrace();
               conexao.rollback();
           }catch(Exception e){
               System.out.println("Erro: "  + e.getMessage());
               e.printStackTrace();
           }
           return false;
       }
    }

    @Override
    public Boolean excluir(int numero) {
       int idUnidadeMedida = numero;
       PreparedStatement stmt = null;
       String sql = "delete from unidademedida where idunidademedida = ?";
       
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, idUnidadeMedida);
           stmt.execute();
           conexao.commit();
           return true;
       }catch(Exception ex){
           System.out.println("Problemas ao excluir UnidadeMedida! Erro: " + ex.getMessage());
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
       int idUnidadeMedida = numero;
       PreparedStatement stmt = null;
       ResultSet rs = null;
       UnidadeMedida oUnidadeMedida = null;
       String sql = "Select * from unidademedida where idunidademedida = ?";
       
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, idUnidadeMedida);
           rs = stmt.executeQuery();
           while(rs.next()){
               oUnidadeMedida = new UnidadeMedida();
               oUnidadeMedida.setIdUnidadeMedida(rs.getInt("idunidademedida"));
               oUnidadeMedida.setDescricao(rs.getString("descricao"));
               oUnidadeMedida.setSigla(rs.getString("sigla"));
           }
           return oUnidadeMedida;
       }catch(Exception ex){
           System.out.println("Problemas ao carregar UnidadeMedida! Erro: " + ex.getMessage());
           return false;
       }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select  * from unidademedida order by idunidademedida";
        
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                UnidadeMedida oUnidadeMedida = new UnidadeMedida();
                oUnidadeMedida.setIdUnidadeMedida(rs.getInt("idunidademedida"));
                oUnidadeMedida.setDescricao(rs.getString("descricao"));
                oUnidadeMedida.setSigla(rs.getString("sigla"));
                resultado.add(oUnidadeMedida);
            }
        }catch(Exception ex){
            System.out.println("Problemas ao listar UnidadeMedida! Erro: " + ex.getMessage());
            
        }
        return resultado;
    }
    
}
