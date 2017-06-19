package br.com.forum.command;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

	public void execute(HttpServletRequest request, HttpServletResponse response, Connection connection) throws ServletException, IOException;
	
}