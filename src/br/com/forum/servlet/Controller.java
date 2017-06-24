package br.com.forum.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.forum.command.CadastrarCommand;
import br.com.forum.command.Command;
import br.com.forum.command.ExibeTopicoCommand;
import br.com.forum.command.ExibeTopicosCommand;
import br.com.forum.command.InsereComentarioCommand;
import br.com.forum.command.InsereTopicoCommand;
import br.com.forum.command.LoginCommand;
import br.com.forum.command.RankingCommand;

@WebServlet("/Controller")
public class Controller extends ServletBD { 
	private final String CADASTRAR = "CADASTRAR";
	private final String RANKING = "RANKING";
	private final String INSERE_TOPICO = "INSERE_TOPICO";
	private final String INSERE_COMENTARIO = "INSERE_COMENTARIO";
	private final String EXIBE_TOPICO = "EXIBE_TOPICO";
	private final String EXIBE_TOPICOS = "EXIBE_TOPICOS";
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			Connection connection = getConnection();
			FactoryCommand(action).execute(request, response, connection);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	private Command FactoryCommand (String action) {
		if (CADASTRAR.equals(action)) {
			return new CadastrarCommand();
		}
		if (RANKING.equals(action)) {
			return new RankingCommand();
		}
		if (INSERE_TOPICO.equals(action)) {
			return new InsereTopicoCommand();
		}
		if (EXIBE_TOPICO.equals(action)) {
			return new ExibeTopicoCommand();
		}
		if (INSERE_COMENTARIO.equals(action)) {
			return new InsereComentarioCommand();
		}
		if (EXIBE_TOPICOS.equals(action)) {
			return new ExibeTopicosCommand();
		}
		return new LoginCommand();
	}
}
