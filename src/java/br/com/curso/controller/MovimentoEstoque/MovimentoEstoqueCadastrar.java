/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.curso.controller.MovimentoEstoque;

import br.com.curso.dao.GenericDAO;
import br.com.curso.dao.MovimentoEstoqueDAO;
import br.com.curso.model.Funcionario;
import br.com.curso.model.MovimentoEstoque;
import br.com.curso.model.Produto;
import br.com.curso.model.TipoMovimento;
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
@WebServlet(name = "MovimentoEstoqueCadastrar", urlPatterns = {"/MovimentoEstoqueCadastrar"})
public class MovimentoEstoqueCadastrar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=iso-8859-1");
            
            int idMovimento = Integer.parseInt(request.getParameter("idmovimento"));
            String entradaSaida = request.getParameter("entradasaida");
            String documento = request.getParameter("documento");
            String data = request.getParameter("data");
            double quantidade = Double.parseDouble(request.getParameter("quantidade"));
            double valorMovimentado = Double.parseDouble(request.getParameter("valormovimentado"));
            int idProduto = Integer.parseInt(request.getParameter("idproduto"));
            int idTipoMovimento = Integer.parseInt(request.getParameter("idtipomovimento"));
            int idFuncionario = Integer.parseInt(request.getParameter("idfuncionario"));
            String mensagem = null;
            
                MovimentoEstoque oMovimentoEstoque = new MovimentoEstoque();
                oMovimentoEstoque.setIdMovimento(idMovimento);
                oMovimentoEstoque.setEntradaSaida(entradaSaida);
                oMovimentoEstoque.setDocumento(documento);
                oMovimentoEstoque.setData(data);
                oMovimentoEstoque.setQuantidade(quantidade);
                oMovimentoEstoque.setValorMovimentado(valorMovimentado);
                oMovimentoEstoque.setProduto(new Produto(idProduto, ""));
                oMovimentoEstoque.setTipoMovimeto(new TipoMovimento(idTipoMovimento, ""));
                oMovimentoEstoque.setFuncionario(new Funcionario(idFuncionario, ""));
            try{
                GenericDAO dao = new MovimentoEstoqueDAO();
                if(dao.cadastrar(oMovimentoEstoque)){
                    mensagem = "Movimento cadastrado!";
                }else{
                    mensagem = "Problemas ao cadastrar Movimento! Verifique os dados informados";
                }
                request.setAttribute("mensagem", mensagem);
                response.sendRedirect("MovimentoEstoqueListar");
   
            }catch(Exception ex){
                System.out.println("Problemas no Servlet ao cadastrar Movimento! Erro :" + ex.getMessage());
                ex.printStackTrace();
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
