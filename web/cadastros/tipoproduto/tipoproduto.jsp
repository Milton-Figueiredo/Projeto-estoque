<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<h2>TipoProdutos</h2>
<table id="datatable" class="display"> 
    <thead>
        <tr>
            <th align="left">ID</th>
            <th align="left">Descricao</th>
            <th align="right"></th>
            <th align="right"></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="tipoproduto" items="${tipoprodutos}">
            <tr>
                <td align="left">${tipoproduto.idTipoProduto}</td>
                <td aling="left">${tipoproduto.descricao}</td>
                <td align="center">
        <a href=
           "${pageContext.request.contextPath}/TipoProdutoExcluir?idTipoProduto=${tipoproduto.idTipoProduto}">
            Excluir</a></td>
            <td align="center">
                <a href=
                   "${pageContext.request.contextPath}/TipoProdutoCarregar?idTipoProduto=${tipoproduto.idTipoProduto}">
                    Alterar</a></td>
            </tr>
        </c:forEach>
    </tbody>
    
</table>
    
<div align="center">
    <a href="${pageContext.request.contextPath}/TipoProdutoNovo">Novo</a>
    <a href="index.jsp">Voltar a pagina Principal</a>
</div>

    <script>
        $(document).ready(function(){
            console.log('entrei ready');
            //caregamos a datatable
            //$("#datatable").DataTable({});
            $('#datatable').DataTable({
                "OLanguage": {
                    "sProcessing": "Processando...",
                    "sLengthMenu": "Mostrar _MENU_ registros",
                    "sZeroRecords": "Nenhum registro encontrado.",
                    "sInfo": "Mostrando de _START_ at�_END_de_TOTAL_registros",
                    "sInfoEmpty": "Mostrando de 0 at� 0 de registros",
                    "sInfoFiltered": "",
                    "sInfoPostFix": "",
                    "sSearch": "Buscar:",
                    "sUrl": "",
                    "oPaginate": {
                        "sFirst": "Primeiro",
                        "sPrevious": "Anterior",
                        "sNext": "Seguinte",
                        "sLast": "Ultimo"
                    }
                }
            });
        });
    </script>
    
<%@ include file="/footer.jsp" %>


