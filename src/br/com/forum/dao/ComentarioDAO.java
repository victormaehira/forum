package br.com.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.forum.bean.Comentario;

public class ComentarioDAO {
	private Connection connection;
	private String INSERT = "insert into comentario (id_comentario, comentario, login, id_topico) values (nextval('comentario_id_comentario_seq'), ?, ?, ?)";
	private String SELECT_BY_TOPICO = "select * from comentario where id_topico = ?";
	
	public ComentarioDAO (Connection connection) {
		this.connection = connection;
	}

	public void insereComentario(String comentario, String login, Integer id_topico) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
		preparedStatement.setString(1, comentario);
		preparedStatement.setString(2, login);
		preparedStatement.setInt(3, id_topico);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	public List<Comentario> getComentariosByTopico(Integer id_topico) throws SQLException {
		List<Comentario> list = new ArrayList<Comentario>();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_TOPICO);
		preparedStatement.setInt(1, id_topico);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Comentario comentario = new Comentario();
			comentario.setId_topico(id_topico);
			comentario.setId_comentario(resultSet.getInt("id_comentario"));
			comentario.setLogin(resultSet.getString("login"));
			comentario.setComentario(resultSet.getString("comentario"));
			list.add(comentario);
		}

		return list;
	}
}
