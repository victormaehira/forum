package br.com.forum.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.forum.bean.Usuario;
import br.com.forum.exception.UsuarioNotFoundException;

public interface UsuarioDAO {

	// insere um novo usuário no banco de dados
	public void inserir(Usuario u) throws SQLException;

	// recupera o usuário pelo seu login
	public Usuario recuperar(String login);

	// adiciona os pontos para o usuário no banco
	public void adicionarPontos(String login, int pontos);

	// retorna a lista de usuários ordenada por pontos (maior primeiro)
	public List<Usuario> ranking();

	public Usuario login(String login, String senha)throws SQLException, UsuarioNotFoundException;
}