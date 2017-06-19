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
import br.com.forum.exception.UsuarioNotFoundException;

public class LoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, Connection connection) throws ServletException, IOException {
		String nextPage = "body.jsp";
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		UsuarioDAO usuarioDao = new UsuarioDAOImpl(connection);
		try {
			Usuario usuario = usuarioDao.login(login, senha);
			if (usuario != null) {
				request.getSession(true).setAttribute("usuario", usuario);
			}
		} catch (SQLException | UsuarioNotFoundException e) {
			request.setAttribute("mensagem", "Erro ao logar");
			nextPage = "index.jsp";
		}
		request.setAttribute("login", login);
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
