package br.com.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.forum.bean.Usuario;
import br.com.forum.exception.UsuarioNotFoundException;

public class UsuarioDAOImpl implements UsuarioDAO {
	private Connection connection;
	private final String INSERT = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?)";
	private final String SELECT_BY_LOGIN = "SELECT * FROM usuario WHERE login = ?";
	private final String SELECT_ALL = "SELECT * FROM usuario ORDER BY pontos DESC";
	private final String UPDATE_PONTOS = "UPDATE usuario SET pontos = ? WHERE login = ?";
	private final String LOGIN = "SELECT * FROM usuario WHERE login = ? and senha = ?";

	public UsuarioDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void inserir(Usuario u) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
		preparedStatement.setString(1, u.getLogin());
		preparedStatement.setString(2, u.getEmail());
		preparedStatement.setString(3, u.getNome());
		preparedStatement.setString(4, u.getSenha());
		preparedStatement.setInt(5, u.getPontos());
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	@Override
	public Usuario recuperar(String login) {
		Usuario usuario = new Usuario();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_LOGIN);
			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				usuario.setLogin(resultSet.getString("login"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setPontos(resultSet.getInt("pontos"));
			}
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return usuario;
	}

	@Override
	public void adicionarPontos(String login, int pontos) {
		try {
			Usuario usuario = recuperar(login);
			int total = pontos + usuario.getPontos();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PONTOS);
			preparedStatement.setInt(1, total);
			preparedStatement.setString(2, login);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

	}

	@Override
	public List<Usuario> ranking() {
		List<Usuario> list = new ArrayList<Usuario>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setLogin(resultSet.getString("login"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setPontos(resultSet.getInt("pontos"));
				list.add(usuario);
			}
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return list;

	}

	@Override
	public Usuario login(String login, String senha) throws SQLException, UsuarioNotFoundException {
		PreparedStatement preparedStatement = connection.prepareStatement(LOGIN);
		preparedStatement.setString(1, login);
		preparedStatement.setString(2, senha);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			String email = resultSet.getString("email");
			String nome = resultSet.getString("nome");
			Integer pontos = resultSet.getInt("pontos");
			return new Usuario(login, email, nome, senha, pontos);
		}
		throw new UsuarioNotFoundException();
	}
}
