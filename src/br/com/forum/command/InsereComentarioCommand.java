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

import br.com.forum.bean.Comentario;
import br.com.forum.bean.Topico;
import br.com.forum.bean.Usuario;
import br.com.forum.dao.ComentarioDAO;
import br.com.forum.dao.TopicoDAO;
import br.com.forum.dao.UsuarioDAO;
import br.com.forum.dao.UsuarioDAOImpl;

public class InsereComentarioCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		String nextPage = "listaComentarios.jsp";
		String id_topico = request.getParameter("id_topico");
		String comentario = request.getParameter("comentario");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		
		TopicoDAO topicoDAO = new TopicoDAO(connection);
		ComentarioDAO comentarioDAO = new ComentarioDAO(connection);
		UsuarioDAO usuarioDAO =  new UsuarioDAOImpl(connection);
		List<Comentario> comentarioList = new ArrayList<Comentario>();
		Topico topico = new Topico();
		Usuario autor = new Usuario();
		try {
			comentarioDAO.insereComentario(comentario, usuario.getLogin(), Integer.parseInt(id_topico));
			topico = topicoDAO.getTopico(Integer.parseInt(id_topico));
			autor = usuarioDAO.recuperar(topico.getLogin());
			usuarioDAO.adicionarPontos(usuario.getLogin(), 3);
			comentarioList = comentarioDAO.getComentariosByTopico(Integer.parseInt(id_topico));
			for (Comentario item: comentarioList) {
				Usuario comentarista = usuarioDAO.recuperar(item.getLogin());
				item.setNomeUsuario(comentarista.getNome());
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("autor", autor);
		request.setAttribute("topico", topico);
		request.setAttribute("comentarioList", comentarioList);
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
