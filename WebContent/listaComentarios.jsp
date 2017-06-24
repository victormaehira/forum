<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comentarios</title>
</head>
<body>
<h3>Tópico</h3>
Título: ${topico.titulo}<br><br>
Conteúdo: ${topico.conteudo}<br><br>
Autor: ${autor.nome}<br><br>
<a href="${pageContext.request.contextPath}/Controller?action=EXIBE_TOPICOS">Voltar para tela de tópicos</a><br><br>
<h3>Comentários</h3>

<table border="1">
	<tr>
		<td><b>COMENTÁRIO</b></td><td><b>NOME DO AUTOR</b></td>
	</tr>
	<c:forEach items="${comentarioList}" var="item">
		<tr>
	    	<td>${item.comentario}</td><td>${item.nomeUsuario}</td>
	    </tr>
	</c:forEach>
	
</table>

<br>
<form action="${pageContext.request.contextPath}/Controller?action=INSERE_COMENTARIO" method="post">
	<input type="hidden" value="${topico.id_topico}" name="id_topico"/>
	Comentário: <textarea name="comentario" rows="4" cols="50"></textarea><br>
	<input type="submit" value="Inserir Comentário">
</form>
</body>
</html>