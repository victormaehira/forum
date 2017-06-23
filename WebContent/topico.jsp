<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Topico</title>
</head>
<body>
<h3>Cadastro de Tópico</h3>
<form action="${pageContext.request.contextPath}/Controller?action=INSERE_TOPICO" method="post">
	Titulo: <input type="text" size="50" name="titulo"><br><br>
	Conteúdo: <textarea name="conteudo" rows="4" cols="50"></textarea><br>
	<input type="submit" value="Inserir">
</form>
</body>
</html>