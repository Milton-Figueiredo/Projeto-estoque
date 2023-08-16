/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.curso.controller.Produto;

import br.com.curso.dao.GenericDAO;
import br.com.curso.dao.ProdutoDAO;
import br.com.curso.model.Produto;
import br.com.curso.model.TipoProduto;
import br.com.curso.model.UnidadeMedida;
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
@WebServlet(name = "ProdutoCadastrar", urlPatterns = {"/ProdutoCadastrar"})
public class ProdutoCadastrar extends HttpServlet {

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
            int idProduto = Integer.parseInt(request.getParameter("idproduto"));
            int idTipoProduto = Integer.parseInt(request.getParameter("idtipoproduto"));
            int idUnidadeMedida = Integer.parseInt(request.getParameter("idunidademedida"));
            String nomeProduto = request.getParameter("nomeproduto");
            double ultimoPrecoPago = Double.parseDouble(request.getParameter("ultimoprecopago"));
            String mensagem = null;
            
            try{
                Produto oProduto = new Produto();
                oProduto.setIdProduto(idProduto);
                oProduto.setTipoProduto(new TipoProduto(idTipoProduto, ""));
                oProduto.setUnidadeMedida(new UnidadeMedida(idUnidadeMedida, "", ""));
                oProduto.setNomeProduto(nomeProduto);
                oProduto.setUltimoPrecoPago(ultimoPrecoPago);
                
                GenericDAO dao = new ProdutoDAO();
                if(dao.cadastrar(oProduto)){
                    mensagem = "Produto Cadastrado";
                }else{
                    mensagem = "Problemas ao Cadastrar Produto! Verifique os dados informados";
                }
                request.setAttribute("mensagem", mensagem);
                response.sendRedirect("ProdutoListar");
            }catch(Exception ex){
               System.out.println("Probleamas no Servlet ao cadastrar Produto! Erro: " + ex.getMessage());
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
