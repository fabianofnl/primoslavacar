package com.primos.lavacar.model;

import java.sql.SQLException;

import com.primos.lavacar.view.FuncionarioViewBean;

/**
 * Interface de conex�o com a base de dados para autentica��o de usu�rios do
 * sistema.
 * 
 * @author Claudemir
 * 
 */
public interface LoginModel {

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
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que altera a senha do usu�rio do sistema
	 * 
	 * @param funcionario
	 * @return String
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String alterarSenha(FuncionarioViewBean funcionario)
			throws ClassNotFoundException, SQLException;

}
