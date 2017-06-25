package br.com.forum.dao;

import static org.junit.Assert.assertEquals;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.forum.bean.Comentario;
import br.com.forum.bean.Topico;

public class ComentarioDAOTest {
	JdbcDatabaseTester jdt;
	Connection connection;
	
	@Before
	public void setUp() throws Exception {
		connection = getConnection();
		ComentarioDAO comentarioDAO = new ComentarioDAO(connection);
		comentarioDAO.clean();
		jdt = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/coursera", "postgres",
				"postgres");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicioComentario.xml"));
		jdt.onSetup();
	}
	
	@Test
	public void testeInsere() throws Exception {
		TopicoDAO topicoDAO = new TopicoDAO(connection);
		List<Topico> list = topicoDAO.getTopicos();
		ComentarioDAO comentarioDAO = new ComentarioDAO(connection);
		comentarioDAO.insereComentario("LEROLERO", "joao", list.get(0).getId_topico());
		
		IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable actualTable = currentDataSet.getTable("COMENTARIO");
    	FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load("/verificaComentario.xml");
		ITable expectedTable = expectedDataSet.getTable("COMENTARIO");
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable, 
	            expectedTable.getTableMetaData().getColumns());
	
		Assertion.assertEquals(expectedTable, filteredTable); 
	}
	
	@Test
	public void testeSelectByTopico() throws Exception {
		TopicoDAO topicoDAO = new TopicoDAO(connection);
		List<Topico> list = topicoDAO.getTopicos();
		ComentarioDAO comentarioDAO = new ComentarioDAO(connection);
		comentarioDAO.insereComentario("LEROLERO", "joao", list.get(0).getId_topico());
		List<Comentario> listComentario = comentarioDAO.getComentariosByTopico(list.get(0).getId_topico());
		assertEquals(1, listComentario.size());
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
