package br.com.forum.command;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.forum.bean.Topico;
import br.com.forum.dao.TopicoDAO;

public class ExibeTopicosCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		String nextPage = "body.jsp";
		TopicoDAO topicoDAO = new TopicoDAO(connection);
		List<Topico> topicos = new ArrayList<Topico>();
		try {
			topicos = topicoDAO.getTopicos();
		} catch (SQLException e) {
			request.setAttribute("mensagem", "Erro ao logar");
			nextPage = "index.jsp";
		}
		request.setAttribute("topicos", topicos);
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
