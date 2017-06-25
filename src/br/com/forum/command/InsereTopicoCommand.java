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

public class InsereTopicoCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		String nextPage = "body.jsp";
		String titulo = request.getParameter("titulo");
		String conteudo = request.getParameter("conteudo");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			
		TopicoDAO topicoDAO = new TopicoDAO(connection);
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl(connection);
		List<Topico> topicos = new ArrayList<Topico>();
		try {
			topicoDAO.insereTopico(titulo, conteudo, usuario.getLogin());
			usuarioDAO.adicionarPontos(usuario.getLogin(), 10);
			topicos = topicoDAO.getTopicos();
			for (Topico topico: topicos) {
				Usuario autor = usuarioDAO.recuperar(topico.getLogin());
				topico.setNomeUsuario(autor.getNome());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("mensagem", "Erro ao cadastrar tópico");
			nextPage = "error.jsp";
		}
		
		request.setAttribute("topicos", topicos);
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);

	}

}
