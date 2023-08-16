/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.curso.controller.Funcionario;

import br.com.curso.dao.GenericDAO;
import br.com.curso.dao.FuncionarioDAO;
import br.com.curso.model.Funcionario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author muril
 */
@WebServlet(name = "FuncionarioCadastrar", urlPatterns = {"/FuncionarioCadastrar"})
public class FuncionarioCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=iso-8859-1");
            
           int idFuncionario = Integer.parseInt(request.getParameter("idfuncionario"));
           String nomefuncionario = request.getParameter("nomefuncionario");
           String mensagem = null;
           
           Funcionario oFuncionario = new Funcionario();
           oFuncionario.setIdFuncionario(idFuncionario);
           oFuncionario.setNomeFuncionario(nomefuncionario);
           
           try{
               GenericDAO dao = new FuncionarioDAO();
               if(dao.cadastrar(oFuncionario)){
                   mensagem = "Funcionario Cadastrada com sucesso!";
               }else{
                   mensagem = "Problemas ao cadastrar Funcionario! Verifique os dados informados e tente novamente";
               }
               request.setAttribute("mensagem", mensagem);
               response.sendRedirect("FuncionarioListar");
           }catch(Exception ex){
               System.out.println("Problemas no servlet ao cadastrar Funcionario! Erro: " + ex.getMessage());
           }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
