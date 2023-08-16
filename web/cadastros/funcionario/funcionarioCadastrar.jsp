<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<form name="cadastrarfuncionario" action="FuncionarioCadastrar" method="POST">
    
    <table align="center" border="0">
        
        <thead>
            <tr>
                <th colspan="2" align="center">
                    Cadastros de Funcionarios
                </th>
            </tr> 
            <tr>
                <th colspan="2" aling="center">${mensagem}</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>ID: </td>
                <td><input type="text" name="idfuncionario" id="idfuncionario" value="${funcionario.idFuncionario}" readonly="readonly"/> </td>
            </tr>
            <tr>
                <td>Nome Funcionario: </td>
                <td><input type="text" name="nomefuncionario" id="nomefuncionario" value="${funcionario.nomeFuncionario}" size="50" maxlength="50" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" name="cadastrar" id="cadastrar" value="Cadastrar" />
                    <input type="reset" name="limpar" id="limpar" value="Limpar" />
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2"><h5><a href="index.jsp"> Voltar à Pagina Inicial</a></h5></td>
            </tr>
        </tbody>
    </table>
</form>           
<%@ include file="/footer.jsp" %>
