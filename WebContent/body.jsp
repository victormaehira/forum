<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Topicos</title>
</head>
<body>
<h1>Topicos</h1>
<a href="${pageContext.request.contextPath}/topico.jsp">Insere Tópico</a>&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/Controller?action=RANKING">Ranking</a><br><br>
<table border="1">
	<tr>
		<td><b>TÓPICO</b></td><td><b>NOME</b></td>
	</tr>
	<c:forEach items="${topicos}" var="topico">
		<tr>
	    	<td><a href="${pageContext.request.contextPath}/Controller?action=EXIBE_TOPICO&id_topico=${topico.id_topico}">${topico.titulo}</a></td><td>${topico.nomeUsuario}</td>
	    </tr>
	</c:forEach>
	
</table>
</body>
</html>