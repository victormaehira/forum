<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro</title>
</head>
<body>
<h1>Cadastro</h1>
<form action="${pageContext.request.contextPath}/Controller?action=CADASTRAR" method="post">
	Login: &nbsp;<input type="text" name="login" size="50"><br>
	Email: &nbsp;<input type="text" name="email" size="50"><br>
	Nome: &nbsp;<input type="text" name="nome" size="50"><br>
	Senha: &nbsp;<input type="text" name="senha" size="50"><br>
	<input type="submit" value="Cadastrar"/>
</form>
</body>
</html>