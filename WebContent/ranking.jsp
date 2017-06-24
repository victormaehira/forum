<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ranking</title>
</head>
<body>
<h1>Ranking</h1>
<a href="javascript:history.back()">Voltar</a>
<br>
<br>

<table border="1">
	<tr>
		<td><b>POSI��O</b></td><td><b>NOME</b></td><td><b>LOGIN</b></td><td><b>PONTOS</b></td>
	</tr>
	<c:set var="count" value="${0}" /> 
	<c:forEach items="${usuarios}" var="usuario">
		<c:set var="count" value="${count+1}" />
		<tr>
	    	<td>${count}</td><td>${usuario.nome}</td><td>${usuario.login}</td><td>${usuario.pontos}</td>
	    </tr>
	</c:forEach>
	
</table>

</body>
</html>