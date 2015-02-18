package com.primos.lavacar.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.primos.lavacar.utils.ConexaoBaseDados;
import com.primos.lavacar.view.ClienteViewBean;

/**
 * Classe que possui a implementação dos métodos de gerenciamento dos clientes
 * 
 * @author Claudemir
 * 
 */
public class ClienteModelImpl implements ClienteModel {

	private static final Logger LOG = Logger.getLogger(ClienteModelImpl.class);

	private static final String SELECT_LISTA_TODOS_CLIENTES = "SELECT * FROM cliente ORDER BY nome";

	private static final String INSERT_CLIENTE = "INSERT INTO cliente "
			+ "(cpf, nome, endereconumero, email, telefone, status) "
			+ "VALUES (?, ?, ?, ?, ?, 'Ativo')";

	private static final String UPDATE_CLIENTE = "UPDATE cliente "
			+ "SET cpf = ?, nome = ?, endereconumero = ?, email = ?, telefone = ?, status = ? WHERE cpf = ?";

	private static final String INATIVAR_CLIENTE = "UPDATE cliente SET status = 'Inativo' WHERE cpf = ?";

	/**
	 * Método que retorna uma lista de todos os clientes cadastrados
	 * 
	 * @return List<ClienteViewBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<ClienteViewBean> listarClientes()
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarCliente");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_LISTA_TODOS_CLIENTES);
		rs = pstmt.executeQuery();

		List<ClienteViewBean> listaClientes = new ArrayList<ClienteViewBean>();
		ClienteViewBean cliente;

		while (rs.next()) {
			cliente = new ClienteViewBean();
			cliente.setCpf(rs.getLong("cpf"));
			cliente.setCpfAntigo(rs.getLong("cpf"));
			cliente.setNome(rs.getString("nome"));
			cliente.setEmail(rs.getString("email"));
			cliente.setEnderecoNumero(rs.getString("endereconumero"));
			cliente.setTelefone(rs.getLong("telefone"));
			cliente.setStatus(rs.getString("status"));
			listaClientes.add(cliente);
		}

		fecharConexao(rs, pstmt, conn);

		return listaClientes;
	}

	/**
	 * Método que cadastra um cliente
	 * 
	 * @param clienteNovo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void gravar(ClienteViewBean clienteNovo)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método gravar");

		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_CLIENTE);
		pstmt.setLong(1, clienteNovo.getCpf());
		pstmt.setString(2, clienteNovo.getNome());
		pstmt.setString(3, clienteNovo.getEnderecoNumero());
		pstmt.setString(4, clienteNovo.getEmail());
		pstmt.setLong(5, clienteNovo.getTelefone());

		pstmt.execute();

		fecharConexao(null, pstmt, conn);
	}

	/**
	 * Método que altera um cliente
	 * 
	 * @param clienteSelecionado
	 * @param cpfAntigo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterar(ClienteViewBean clienteSelecionado, Long cpfAntigo)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método alterar");

		LOG.info("CPF: " + clienteSelecionado.getCpf() + ", antigo: "
				+ cpfAntigo + " | " + clienteSelecionado.getStatus());

		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(UPDATE_CLIENTE);
		pstmt.setLong(1, clienteSelecionado.getCpf());
		pstmt.setString(2, clienteSelecionado.getNome());
		pstmt.setString(3, clienteSelecionado.getEnderecoNumero());
		pstmt.setString(4, clienteSelecionado.getEmail());
		pstmt.setLong(5, clienteSelecionado.getTelefone());
		pstmt.setString(6, clienteSelecionado.getStatus());
		pstmt.setLong(7, cpfAntigo);

		pstmt.execute();

		fecharConexao(null, pstmt, conn);
	}

	/**
	 * Método que inativa um cliente
	 * 
	 * @param cpf
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void inativar(Long cpf) throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método inativar");

		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INATIVAR_CLIENTE);
		pstmt.setLong(1, cpf);

		pstmt.execute();

		fecharConexao(null, pstmt, conn);
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
