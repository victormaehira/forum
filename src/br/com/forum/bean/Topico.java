package br.com.forum.bean;

public class Topico {
	private Integer id_topico;
	private String titulo;
	private String conteudo;
	private String login;
	public Topico(Integer id_topico, String titulo, String conteudo, String login) {
		super();
		this.id_topico = id_topico;
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.login = login;
	}
	public Topico() {
		// TODO Auto-generated constructor stub
	}
	public Integer getId_topico() {
		return id_topico;
	}
	public void setId_topico(Integer id_topico) {
		this.id_topico = id_topico;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
}
