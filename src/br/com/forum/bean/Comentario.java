package br.com.forum.bean;

public class Comentario {
	private Integer id_comentario;
	private String comentario;
	private String login;
	private Integer id_topico;
	private String nomeUsuario;

	public Integer getId_comentario() {
		return id_comentario;
	}

	public void setId_comentario(Integer id_comentario) {
		this.id_comentario = id_comentario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getId_topico() {
		return id_topico;
	}

	public void setId_topico(Integer id_topico) {
		this.id_topico = id_topico;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
}
