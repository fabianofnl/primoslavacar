/**
 * 
 */
package com.primos.lavacar.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.primos.lavacar.utils.ConexaoBaseDados;
import com.primos.lavacar.view.ReceitaDespesaViewBean;

/**
 * @author Claudemir
 * 
 */
public class ReceitaDespesaModelImpl implements ReceitaDespesaModel {

	private static final Logger LOG = Logger
			.getLogger(ReceitaDespesaModelImpl.class);

	private static final String SELECT_LISTA_TODAS_RECEITAS = "SELECT * FROM fluxocaixa WHERE tipo = 'R' "
			+ "ORDER BY dataProcessamento";

	private static final String SELECT_LISTA_TODAS_DESPESAS = "SELECT * FROM fluxocaixa WHERE tipo = 'D' "
			+ "ORDER BY dataProcessamento";

	private static final String INSERT_DESPESA = "INSERT INTO fluxocaixa (titulo, tipo, dataProcessamento, valor) "
			+ "VALUES (?, 'D', ?, ?)";

	private static final String UPDATE_DESPESA = "UPDATE fluxocaixa "
			+ "SET titulo = ?, dataProcessamento = ?, valor = ? WHERE id = ?";

	private static final String DELETE_RECEITA_UPDATE_AGENDA = "BEGIN; "
			+ "DELETE FROM fluxocaixa WHERE id = ?;"
			+ "UPDATE agenda SET baixa = false WHERE id = ?;" + "COMMIT;";

	private static final String DELETE_DESPESA = "DELETE FROM fluxocaixa WHERE id = ?";

	public List<ReceitaDespesaViewBean> listarReceitas()
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarReceitas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_LISTA_TODAS_RECEITAS);
		rs = pstmt.executeQuery();

		List<ReceitaDespesaViewBean> listaReceitaDespesas = new ArrayList<ReceitaDespesaViewBean>();
		ReceitaDespesaViewBean receitaDespesaViewBean = null;

		while (rs.next()) {
			receitaDespesaViewBean = new ReceitaDespesaViewBean();
			receitaDespesaViewBean.setId(rs.getInt("id"));
			receitaDespesaViewBean.setIdAgenda(rs.getInt("idAgenda"));
			receitaDespesaViewBean.setTitulo(rs.getString("titulo"));
			receitaDespesaViewBean.setTipo(rs.getString("tipo"));
			receitaDespesaViewBean.setDataProcessamento(new Date(rs.getDate(
					"dataProcessamento").getTime()));
			receitaDespesaViewBean.setValor(rs.getDouble("valor"));

			listaReceitaDespesas.add(receitaDespesaViewBean);
		}

		return listaReceitaDespesas;
	}

	public List<ReceitaDespesaViewBean> listarDespesas()
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarDespesas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_LISTA_TODAS_DESPESAS);
		rs = pstmt.executeQuery();

		List<ReceitaDespesaViewBean> listaReceitaDespesas = new ArrayList<ReceitaDespesaViewBean>();
		ReceitaDespesaViewBean receitaDespesaViewBean = null;

		while (rs.next()) {
			receitaDespesaViewBean = new ReceitaDespesaViewBean();
			receitaDespesaViewBean.setId(rs.getInt("id"));
			receitaDespesaViewBean.setTitulo(rs.getString("titulo"));
			receitaDespesaViewBean.setTipo(rs.getString("tipo"));
			receitaDespesaViewBean.setDataProcessamento(new Date(rs.getDate(
					"dataProcessamento").getTime()));
			receitaDespesaViewBean.setValor(rs.getDouble("valor"));

			listaReceitaDespesas.add(receitaDespesaViewBean);
		}

		fecharConexao(rs, pstmt, conn);

		return listaReceitaDespesas;
	}

	public void gravar(ReceitaDespesaViewBean despesaNova)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método gravar");

		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_DESPESA);
		pstmt.setString(1, despesaNova.getTitulo());
		pstmt.setDate(2, new java.sql.Date(despesaNova.getDataProcessamento()
				.getTime()));
		pstmt.setDouble(3, despesaNova.getValor());
		pstmt.execute();

		fecharConexao(null, pstmt, conn);

	}

	public void alterar(ReceitaDespesaViewBean receitaDespesaSelecionado)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método alterar");

		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(UPDATE_DESPESA);
		pstmt.setString(1, receitaDespesaSelecionado.getTitulo());
		pstmt.setDate(2, new java.sql.Date(receitaDespesaSelecionado
				.getDataProcessamento().getTime()));
		pstmt.setDouble(3, receitaDespesaSelecionado.getValor());
		pstmt.setInt(4, receitaDespesaSelecionado.getId());
		pstmt.execute();

		fecharConexao(null, pstmt, conn);

	}

	public void excluir(ReceitaDespesaViewBean receitaDespesaSelecionado)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método excluir");

		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = ConexaoBaseDados.getConexaoInstance();

		if (receitaDespesaSelecionado.getTipo().equalsIgnoreCase("R")) {
			pstmt = conn.prepareStatement(DELETE_RECEITA_UPDATE_AGENDA);
			pstmt.setInt(1, receitaDespesaSelecionado.getId());
			pstmt.setInt(2, receitaDespesaSelecionado.getIdAgenda());
		} else {
			pstmt = conn.prepareStatement(DELETE_DESPESA);
			pstmt.setInt(1, receitaDespesaSelecionado.getId());
		}

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
