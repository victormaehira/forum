package br.com.forum.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.forum.command.Command;
import br.com.forum.command.LoginCommand;

@WebServlet("/Controller")
public class Controller extends ServletBD { 
	private final String CADASTRAR = "CADASTRAR";
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
		return new LoginCommand();
	}
}
