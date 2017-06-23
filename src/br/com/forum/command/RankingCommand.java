package br.com.forum.command;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.forum.bean.Usuario;
import br.com.forum.dao.UsuarioDAO;
import br.com.forum.dao.UsuarioDAOImpl;

public class RankingCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		String nextPage = "ranking.jsp";
		UsuarioDAO usuarioDao = new UsuarioDAOImpl(connection);
		List<Usuario> list = usuarioDao.ranking();
		request.setAttribute("usuarios", list);
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
