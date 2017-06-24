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
import br.com.forum.bean.Usuario;
import br.com.forum.dao.TopicoDAO;
import br.com.forum.dao.UsuarioDAO;
import br.com.forum.dao.UsuarioDAOImpl;

public class ExibeTopicosCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		String nextPage = "body.jsp";
		TopicoDAO topicoDAO = new TopicoDAO(connection);
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl(connection);
		List<Topico> topicos = new ArrayList<Topico>();
		try {
			topicos = topicoDAO.getTopicos();
			for (Topico topico: topicos) {
				Usuario autor = usuarioDAO.recuperar(topico.getLogin());
				topico.setNomeUsuario(autor.getNome());
			}
		} catch (SQLException e) {
			request.setAttribute("mensagem", "Erro ao logar");
			nextPage = "index.jsp";
		}
		request.setAttribute("topicos", topicos);
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
