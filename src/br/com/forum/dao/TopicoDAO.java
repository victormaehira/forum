package br.com.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.forum.bean.Topico;

public class TopicoDAO {
	private Connection connection;
	private final String SELECT_ALL = "select * from topico";
	private final String INSERT = "insert into topico(id_topico, titulo, conteudo, login) values (nextval('topico_id_topico_seq'), ?, ?, ?)";
	private final String SELECT_BY_ID = "select * from topico where id_topico = ?";
	private String DELETE = "DELETE FROM TOPICO";
	
	public TopicoDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void insereTopico(String titulo, String conteudo, String login) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
		preparedStatement.setString(1, titulo);
		preparedStatement.setString(2, conteudo);
		preparedStatement.setString(3, login);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	public List<Topico> getTopicos() throws SQLException {
		List<Topico> list = new ArrayList<Topico>();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Integer id_topico = resultSet.getInt("id_topico");
			String titulo = resultSet.getString("titulo");
			String conteudo = resultSet.getString("conteudo");
			String login = resultSet.getString("login");
			Topico topico = new Topico(id_topico, titulo, conteudo, login);
			list.add(topico);
		}
		return list;
	}
	
	public Topico getTopico(Integer id_topico) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
		preparedStatement.setInt(1, id_topico);
		ResultSet resultSet = preparedStatement.executeQuery();
		Topico topico = new Topico();
		if (resultSet.next()) {
			topico.setId_topico(id_topico);
			topico.setTitulo(resultSet.getString("titulo"));
			topico.setConteudo(resultSet.getString("conteudo"));
			topico.setLogin(resultSet.getString("login"));
		}
		return topico;
	}
	
	public void clean() throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
}
