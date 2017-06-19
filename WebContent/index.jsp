<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Login</h1>
<form method="POST" action="${pageContext.request.contextPath}/Login">
Usuário <input name="login" type=text><br>
Senha <input name="senha" type=password><br>
<input type=submit value="logar">
&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/cadastro.jsp">Novo Cadastro</a>
</form>
<br>
${mensagem}
<br><br><br>
<table>
	<tr>
		<td colspan=2>Sistema Fórum</td><td></td>
	</tr>
	<tr>
		<td>Curso:</td><td>Desenvolvimento Ágil com Java Avançado</td>
	</tr>
	<tr>
		<td>Nome:</td><td>Victor Yuji Maehira</td>
	</tr>
</table>
</body>
</html>