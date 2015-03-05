package com.primos.lavacar.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.primos.lavacar.utils.ConexaoBaseDados;
import com.primos.lavacar.view.ContaViewBean;

public class ContaModelImpl implements ContaModel {

	private static final Logger LOG = Logger.getLogger(ContaModelImpl.class);

	private static final String SELECT_LISTA_TODAS_CONTAS = "SELECT * FROM conta ORDER BY nome";

	private static final String INSERT_CONTA = "INSERT INTO conta (nome, descricao, status) VALUES (?, ?, 'Ativo')";

	private static final String UPDATE_CONTA = "UPDATE conta SET nome = ?, descricao = ?, status = ? WHERE id = ?";

	private static final String INATIVAR_CONTA = "UPDATE conta SET status = 'Inativo' WHERE id = ?";

	private static final String SELECT_LISTA_CONTAS_ATIVAS = "SELECT * FROM conta WHERE status = 'Ativo' "
			+ "ORDER BY nome";

	public List<ContaViewBean> listarContas() throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método listarContas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_LISTA_TODAS_CONTAS);
		rs = pstmt.executeQuery();

		List<ContaViewBean> listaContas = new ArrayList<ContaViewBean>();
		ContaViewBean conta = null;

		while (rs.next()) {
			conta = new ContaViewBean();
			conta.setId(rs.getInt("id"));
			conta.setNome(rs.getString("nome"));
			conta.setDescricao(rs.getString("descricao"));
			conta.setStatus(rs.getString("status"));
			listaContas.add(conta);
		}

		fecharConexao(rs, pstmt, conn);

		return listaContas;
	}

	public void cadastrarConta(ContaViewBean contaNova)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método cadastrarConta");

		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_CONTA);
		pstmt.setString(1, contaNova.getNome());
		pstmt.setString(2, contaNova.getDescricao());

		pstmt.execute();

		fecharConexao(null, pstmt, conn);

	}

	public void editarConta(ContaViewBean contaSelecionada)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método editarConta");

		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(UPDATE_CONTA);
		pstmt.setString(1, contaSelecionada.getNome());
		pstmt.setString(2, contaSelecionada.getDescricao());
		pstmt.setString(3, contaSelecionada.getStatus());
		pstmt.setInt(4, contaSelecionada.getId());

		pstmt.execute();

		fecharConexao(null, pstmt, conn);

	}

	public void inativarConta(ContaViewBean contaSelecionada)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método inativarConta");

		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INATIVAR_CONTA);
		pstmt.setInt(1, contaSelecionada.getId());

		pstmt.execute();

		fecharConexao(null, pstmt, conn);

	}

	@Override
	public List<ContaViewBean> listarContasAtivas()
			throws ClassNotFoundException, SQLException {
		LOG.info("Chamando método listarContas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_LISTA_CONTAS_ATIVAS);
		rs = pstmt.executeQuery();

		List<ContaViewBean> listaContas = new ArrayList<ContaViewBean>();
		ContaViewBean conta = null;

		while (rs.next()) {
			conta = new ContaViewBean();
			conta.setId(rs.getInt("id"));
			conta.setNome(rs.getString("nome"));
			conta.setDescricao(rs.getString("descricao"));
			conta.setStatus(rs.getString("status"));
			listaContas.add(conta);
		}

		fecharConexao(rs, pstmt, conn);

		return listaContas;
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
