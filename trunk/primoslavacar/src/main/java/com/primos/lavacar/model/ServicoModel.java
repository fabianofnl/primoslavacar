package com.primos.lavacar.model;

import java.sql.SQLException;
import java.util.List;

import com.primos.lavacar.view.ServicoViewBean;

/**
 * Inteface que possui todos os m�todos para gerenciar os servi�os
 * 
 * @author Claudemir
 * 
 */
public interface ServicoModel {

	/**
	 * M�todo que retorna uma lista de servi�os
	 * 
	 * @return List<ServicoViewBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<ServicoViewBean> listarServicos()
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que grava um servi�o no sistema
	 * 
	 * @param servicoNovo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void gravar(ServicoViewBean servicoNovo)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que altera um servi�o no sistema
	 * 
	 * @param servicoSelecionado
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterar(ServicoViewBean servicoSelecionado)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que inativa um servi�o no sistema
	 * 
	 * @param servicoSelecionado
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void inativar(ServicoViewBean servicoSelecionado)
			throws ClassNotFoundException, SQLException;

}
