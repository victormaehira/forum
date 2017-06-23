package br.com.forum.command;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.forum.bean.Usuario;
import br.com.forum.dao.UsuarioDAO;
import br.com.forum.dao.UsuarioDAOImpl;

public class CadastrarCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, Connection connection) throws ServletException, IOException {
		String nextPage = "index.jsp";
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		String nome = request.getParameter("nome");
		UsuarioDAO usuarioDao = new UsuarioDAOImpl(connection);
		try {
			Usuario usuario = new Usuario(login, email, nome, senha, 0);
			usuarioDao.inserir(usuario);
			request.setAttribute("mensagem", "Usuário cadastrado com sucesso");
		} catch (SQLException e) {
			request.setAttribute("mensagem", "Erro ao cadastrar usuário");
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
