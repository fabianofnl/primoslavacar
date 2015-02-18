package com.primos.lavacar.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.primos.lavacar.utils.ConexaoBaseDados;
import com.primos.lavacar.view.FuncionarioViewBean;

/**
 * Classe de implementa��o de m�todos de conex�o com a base de dados para
 * autentica��o de usu�rio do sistema.
 * 
 * @author Claudemir
 * 
 */
public class LoginModelImpl implements LoginModel {

	private static final Logger LOG = Logger.getLogger(LoginModelImpl.class);

	private static final String SELECT_FUNCIONARIO_LOGIN = "SELECT * FROM perfil p, usuario u, funcionario f "
			+ "WHERE f.status != 'Inativo' AND p.id = u.perfilId AND u.usuario = f.usuario AND "
			+ "u.usuario = ? AND u.senha = MD5(?)";

	private static final String UPDATE_SENHA_USUARIO = "UPDATE usuario SET senha = MD5(?) "
			+ "FROM funcionario "
			+ "WHERE usuario.usuario = ? AND funcionario.email = ? "
			+ "RETURNING usuario.usuario";

	public LoginModelImpl() {
	}

	/**
	 * M�todo retorna objeto <b>funcionario</b> para realizar a autentica��o.
	 * 
	 * @param nomeUsuario
	 * @param senha
	 * @return FuncionarioDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public FuncionarioViewBean logar(String nomeUsuario, String senha)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando logar");

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		FuncionarioViewBean funcionario = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_FUNCIONARIO_LOGIN);
		pstmt.setString(1, nomeUsuario);
		pstmt.setString(2, senha);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			funcionario = new FuncionarioViewBean();

			funcionario.setPerfilId(rs.getInt("id"));
			funcionario.setDescricao(rs.getString("descricao"));
			funcionario.setRole(rs.getString("roleName"));
			funcionario.setCpf(rs.getLong("cpf"));
			funcionario.setNome(rs.getString("nome"));
			funcionario.setUsuario(rs.getString("usuario"));
			funcionario.setSenha(rs.getString("senha"));
			funcionario.setEmail(rs.getString("email"));
		}

		fecharConexao(rs, pstmt, conn);

		return funcionario;
	}

	/**
	 * M�todo que altera a senha do usu�rio do sistema
	 * 
	 * @param funcionario
	 * @return String
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String alterarSenha(FuncionarioViewBean funcionario)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando alterar senha");

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		String username = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(UPDATE_SENHA_USUARIO);
		pstmt.setString(1, funcionario.getSenha());
		pstmt.setString(2, funcionario.getUsuario());
		pstmt.setString(3, funcionario.getEmail());
		rs = pstmt.executeQuery();

		if (rs.next()) {
			username = rs.getString("usuario");
		}

		fecharConexao(rs, pstmt, conn);

		return username;
	}

	private void fecharConexao(ResultSet rs, PreparedStatement pstmt,
			Connection conn) throws SQLException {
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}
}
