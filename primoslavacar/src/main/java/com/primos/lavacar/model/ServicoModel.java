package com.primos.lavacar.model;

import java.sql.SQLException;
import java.util.List;

import com.primos.lavacar.view.ServicoViewBean;

/**
 * Inteface que possui todos os métodos para gerenciar os serviços
 * 
 * @author Claudemir
 * 
 */
public interface ServicoModel {

	/**
	 * Método que retorna uma lista de serviços
	 * 
	 * @return List<ServicoViewBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<ServicoViewBean> listarServicos()
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que grava um serviço no sistema
	 * 
	 * @param servicoNovo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void gravar(ServicoViewBean servicoNovo)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que altera um serviço no sistema
	 * 
	 * @param servicoSelecionado
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterar(ServicoViewBean servicoSelecionado)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que inativa um serviço no sistema
	 * 
	 * @param servicoSelecionado
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void inativar(ServicoViewBean servicoSelecionado)
			throws ClassNotFoundException, SQLException;

}
