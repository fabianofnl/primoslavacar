package com.primos.lavacar.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.primos.lavacar.utils.ConexaoBaseDados;
import com.primos.lavacar.view.ServicoViewBean;

/**
 * Classe que possui a implementação dos métodos de gerenciamento dos serviços
 * 
 * @author Claudemir
 * 
 */
public class ServicoModelImpl implements ServicoModel {

	private static final Logger LOG = Logger.getLogger(ServicoModelImpl.class);

	private static final String SELECT_TODOS_SERVICOS = "SELECT * FROM servico ORDER BY valor";

	private static final String INSERT_SERVICO = "INSERT INTO servico (nome, descricao, valor, status) "
			+ "VALUES (?, ?, ?, 'Ativo')";

	private static final String UPDATE_SERVICO = "UPDATE servico SET nome = ?, descricao = ?, valor = ?, status = ? "
			+ "WHERE id = ?";

	private static final String INATIVAR_SERVICO = "UPDATE servico SET status = 'Inativo' WHERE id = ?";

	private static final String SELECT_SERVICOS_ATIVOS = "SELECT * FROM servico WHERE status LIKE 'Ativo' "
			+ "ORDER BY valor";

	/**
	 * Método que retorna uma lista de serviços
	 * 
	 * @return List<ServicoViewBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<ServicoViewBean> listarServicos()
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarServicos");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TODOS_SERVICOS);
		rs = pstmt.executeQuery();

		List<ServicoViewBean> listaServicos = new ArrayList<ServicoViewBean>();
		ServicoViewBean servicoViewBean = null;

		while (rs.next()) {
			servicoViewBean = new ServicoViewBean();
			servicoViewBean.setId(rs.getInt("id"));
			servicoViewBean.setNome(rs.getString("nome"));
			servicoViewBean.setDescricao(rs.getString("descricao"));
			servicoViewBean.setValor(rs.getDouble("valor"));
			servicoViewBean.setStatus(rs.getString("status"));
			listaServicos.add(servicoViewBean);
		}

		fecharConexao(rs, pstmt, conn);

		return listaServicos;
	}

	/**
	 * Método que grava um serviço no sistema
	 * 
	 * @param servicoNovo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void gravar(ServicoViewBean servicoNovo)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método gravar");

		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_SERVICO);

		pstmt.setString(1, servicoNovo.getNome());
		pstmt.setString(2, servicoNovo.getDescricao());
		pstmt.setDouble(3, servicoNovo.getValor());

		pstmt.execute();

		fecharConexao(null, pstmt, conn);
	}

	/**
	 * Método que altera um serviço no sistema
	 * 
	 * @param servicoSelecionado
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterar(ServicoViewBean servicoSelecionado)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método alterar");

		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(UPDATE_SERVICO);

		pstmt.setString(1, servicoSelecionado.getNome());
		pstmt.setString(2, servicoSelecionado.getDescricao());
		pstmt.setDouble(3, servicoSelecionado.getValor());
		pstmt.setString(4, servicoSelecionado.getStatus());
		pstmt.setInt(5, servicoSelecionado.getId());

		pstmt.execute();

		fecharConexao(null, pstmt, conn);
	}

	/**
	 * Método que inativa um serviço no sistema
	 * 
	 * @param servicoSelecionado
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void inativar(ServicoViewBean servicoSelecionado)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método inativar");

		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INATIVAR_SERVICO);

		pstmt.setInt(1, servicoSelecionado.getId());

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

	/**
	 * Método que retorna uma lista de serviços ativos
	 * 
	 * @return List<ServicoViewBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<ServicoViewBean> listarServicosAtivos()
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarServicosAtivos");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_SERVICOS_ATIVOS);
		rs = pstmt.executeQuery();

		List<ServicoViewBean> listaServicos = new ArrayList<ServicoViewBean>();
		ServicoViewBean servicoViewBean = null;

		while (rs.next()) {
			servicoViewBean = new ServicoViewBean();
			servicoViewBean.setId(rs.getInt("id"));
			servicoViewBean.setNome(rs.getString("nome"));
			servicoViewBean.setDescricao(rs.getString("descricao"));
			servicoViewBean.setValor(rs.getDouble("valor"));
			servicoViewBean.setStatus(rs.getString("status"));
			listaServicos.add(servicoViewBean);
		}

		fecharConexao(rs, pstmt, conn);

		return listaServicos;
	}
}
