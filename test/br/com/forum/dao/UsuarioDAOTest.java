package br.com.forum.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.forum.bean.Usuario;

public class UsuarioDAOTest {
	JdbcDatabaseTester jdt;
	Connection connection;

	@Before
	public void setUp() throws Exception {
		connection = getConnection();
		ComentarioDAO comentarioDAO = new ComentarioDAO(connection);
		comentarioDAO.clean();
		TopicoDAO topicoDAO = new TopicoDAO(connection);
		topicoDAO.clean();
		
		jdt = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/coursera", "postgres",
				"postgres");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
	}

	@Test
	public void testRanking() {
		UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl(connection);
		List<Usuario> list = usuarioDAO.ranking();
		assertEquals(2, list.size());
		assertEquals("joao", list.get(0).getLogin());
	}

	@Test
	public void testSelectByLogin() {
		UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl(connection);
		Usuario usuario = usuarioDAO.recuperar("joao");
		assertEquals("joao@gmail.com", usuario.getEmail());
	}

	@Test
	public void testUpdatePontosByLogin() {
		UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl(connection);
		usuarioDAO.adicionarPontos("joao", 10);
		usuarioDAO.adicionarPontos("joao", 10);
		Usuario usuarioJoao = usuarioDAO.recuperar("joao");
		assertEquals("20", Integer.toString(usuarioJoao.getPontos()));
	}

	@Test
	public void testInsere() throws SQLException, Exception {
		Usuario usuario = new Usuario("pedro", "pedro@gmail.com", "Pedro Silva", "senha", 0);
		UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl(connection);
		usuarioDAO.inserir(usuario);

		IDataSet currentDataSet = jdt.getConnection().createDataSet();
		ITable actualTable = currentDataSet.getTable("USUARIO");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load("/verifica.xml");
		ITable expectedTable = expectedDataSet.getTable("USUARIO");

		Assertion.assertEquals(expectedTable, actualTable);
	}

	@After
	public void off() throws Exception {
		jdt.getConnection().close();
		closeConnection(connection);
	}

	@SuppressWarnings({ "finally" })
	private Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/coursera", "postgres",
					"postgres");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			return connection;
		}
	}

	private void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
