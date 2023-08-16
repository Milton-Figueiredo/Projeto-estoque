package br.com.curso.dao;

import br.com.curso.model.Funcionario;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO implements GenericDAO {
    
    private Connection conexao;
    
    public FuncionarioDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
       Funcionario oFuncionario = (Funcionario) objeto;
       Boolean retorno = false;
       if(oFuncionario.getIdFuncionario()==0){
           retorno = this.inserir(oFuncionario);
       }else{
           retorno = this.alterar(oFuncionario);
       }
       return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Funcionario oFuncionario = (Funcionario) objeto;
        PreparedStatement stmt = null;
        String sql = "Insert into funcionario (nomefuncionario) values (?)";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oFuncionario.getNomeFuncionario());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            try{
                System.out.println("Probleamas ao cadastrar Funcionario! Erro: " +  ex.getMessage());
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
       Funcionario oFuncionario = (Funcionario) objeto;
       PreparedStatement stmt = null;
       String sql = "update funcionario set nomefuncionario = ? where idfuncionario = ?";
       
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setString(1, oFuncionario.getNomeFuncionario());
           stmt.setInt(2, oFuncionario.getIdFuncionario());
           stmt.execute();
           conexao.commit();
           return true;
       }catch(Exception ex){
           try{
               System.out.println("Problemas ao alterar Funcionario! Erro: " + ex.getMessage());
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
       int idFuncionario = numero;
       PreparedStatement stmt = null;
       String sql = "delete from funcionario where idfuncionario = ?";
       
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, idFuncionario);
           stmt.execute();
           conexao.commit();
           return true;
       }catch(Exception ex){
           System.out.println("Problemas ao excluir Funcionario! Erro: " + ex.getMessage());
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
       int idFuncionario = numero;
       PreparedStatement stmt = null;
       ResultSet rs = null;
       Funcionario oFuncionario = null;
       String sql = "Select * from funcionario where idfuncionario = ?";
       
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, idFuncionario);
           rs = stmt.executeQuery();
           while(rs.next()){
               oFuncionario = new Funcionario();
               oFuncionario.setIdFuncionario(rs.getInt("idfuncionario"));
               oFuncionario.setNomeFuncionario(rs.getString("nomefuncionario"));
           }
           return oFuncionario;
       }catch(Exception ex){
           System.out.println("Problemas ao carregar Funcionario! Erro: " + ex.getMessage());
           return false;
       }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select  * from funcionario order by idfuncionario";
        
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Funcionario oFuncionario = new Funcionario();
                oFuncionario.setIdFuncionario(rs.getInt("idfuncionario"));
                oFuncionario.setNomeFuncionario(rs.getString("nomefuncionario"));
                resultado.add(oFuncionario);
            }
        }catch(Exception ex){
            System.out.println("Problemas ao listar Funcionario! Erro: " + ex.getMessage());
            
        }
        return resultado;
    }
    
}
