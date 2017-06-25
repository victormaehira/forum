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

public class ExibeTopicoCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		String nextPage = "listaComentarios.jsp";
		String id_topico = request.getParameter("id_topico");
		
		TopicoDAO topicoDAO = new TopicoDAO(connection);
		UsuarioDAO usuarioDAO =  new UsuarioDAOImpl(connection);
		ComentarioDAO comentarioDAO = new ComentarioDAO(connection);
		Topico topico = new Topico();
		Usuario autor = new Usuario();
		List<Comentario> comentarioList = new ArrayList<Comentario>();
		try {
			topico = topicoDAO.getTopico(Integer.parseInt(id_topico));
			autor = usuarioDAO.recuperar(topico.getLogin());
			comentarioList = comentarioDAO.getComentariosByTopico(Integer.parseInt(id_topico));
			for (Comentario comentario: comentarioList) {
				Usuario comentarista = usuarioDAO.recuperar(comentario.getLogin());
				comentario.setNomeUsuario(comentarista.getNome());
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("autor", autor);
		request.setAttribute("topico", topico);
		request.setAttribute("comentarioList", comentarioList);
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
