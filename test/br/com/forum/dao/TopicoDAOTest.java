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
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.forum.bean.Topico;

public class TopicoDAOTest {
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
		jdt.setDataSet(loader.load("/inicioTopico.xml"));
		jdt.onSetup();
	}
	
	@Test
	public void testSelect() throws SQLException {
		TopicoDAO topicoDAO = new TopicoDAO(connection);
		List<Topico> list = topicoDAO.getTopicos();
		assertEquals(2, list.size());
	}

	@Test
	public void testInsere() throws SQLException, Exception {
		TopicoDAO topicoDAO = new TopicoDAO(connection);
		topicoDAO.insereTopico("TDD", "hein?", "joao");
			
		IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable actualTable = currentDataSet.getTable("TOPICO");
    	FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load("/verificaTopico.xml");
		ITable expectedTable = expectedDataSet.getTable("TOPICO");
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable, 
	            expectedTable.getTableMetaData().getColumns());
	
		Assertion.assertEquals(expectedTable, filteredTable); 
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
