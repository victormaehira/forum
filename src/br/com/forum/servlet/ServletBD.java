package br.com.forum.servlet;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

public class ServletBD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds;

	public void init(ServletConfig config) {
		try {
			Context ctx = new InitialContext();
			Context envCtx = (Context) ctx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/DefaultDB");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}