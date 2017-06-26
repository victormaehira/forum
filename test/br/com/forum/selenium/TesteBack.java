package br.com.forum.selenium;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class TesteBack {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\java\\selenium-webdriver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8080/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testCadastro() throws Exception {
		String loginRandomico = UUID.randomUUID().toString();
		selenium.open("/forum/");
		selenium.click("link=Novo Cadastro");
		selenium.waitForPageToLoad("30000");
		selenium.type("name=login", loginRandomico);
		selenium.type("name=email", "testa@gmail.com");
		selenium.type("name=nome", "Testa da Silva");
		selenium.type("name=senha", "testa");
		selenium.click("css=input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		assertEquals("Usuário cadastrado com sucesso", selenium.getText("css=h5"));
	}
	
	@Test
	public void testRanking() throws Exception {
		selenium.open("/forum/Controller?action=EXIBE_TOPICOS");
		selenium.click("link=Ranking");
		selenium.waitForPageToLoad("30000");
		assertEquals("Ranking", selenium.getText("css=h1"));
	}
	
	@Test
	public void testInsereTopico() throws Exception {
		String loginRandomico = UUID.randomUUID().toString();
		selenium.open("/forum/");
		selenium.click("link=Novo Cadastro");
		selenium.waitForPageToLoad("30000");
		selenium.type("name=login", loginRandomico);
		selenium.type("name=email", "teste@gmail.com");
		selenium.type("name=nome", "teste da silva");
		selenium.type("name=senha", "teste");
		selenium.click("css=input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.type("name=login", loginRandomico);
		selenium.type("name=senha", "teste");
		selenium.click("css=input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Insere Tópico");
		selenium.waitForPageToLoad("30000");
		selenium.type("name=titulo", "Novo Topico");
		selenium.type("name=conteudo", "Novo Conteudo");
		selenium.click("css=input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		assertEquals("Novo Topico", selenium.getText("link=Novo Topico"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
