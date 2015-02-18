package com.primos.lavacar.model;

import java.sql.SQLException;
import java.util.List;

import com.primos.lavacar.view.ClienteViewBean;

/**
 * Inteface que possui todos os m�todos para gerenciar os clientes
 * 
 * @author Claudemir
 * 
 */
public interface ClienteModel {

	/**
	 * M�todo que retorna uma lista de todos os clientes cadastrados
	 * 
	 * @return List<ClienteViewBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<ClienteViewBean> listarClientes()
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que cadastra um cliente
	 * 
	 * @param clienteNovo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void gravar(ClienteViewBean clienteNovo)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que altera um cliente
	 * 
	 * @param clienteSelecionado
	 * @param cpfAntigo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterar(ClienteViewBean clienteSelecionado, Long cpfAntigo)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que inativa um cliente
	 * 
	 * @param cpf
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void inativar(Long cpf) throws ClassNotFoundException, SQLException;

}
