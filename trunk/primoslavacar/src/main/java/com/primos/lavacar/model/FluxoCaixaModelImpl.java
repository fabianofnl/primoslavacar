/**
 * 
 */
package com.primos.lavacar.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.primos.lavacar.utils.ConexaoBaseDados;
import com.primos.lavacar.view.FluxoCaixaViewBean;

/**
 * @author Claudemir
 * 
 */
public class FluxoCaixaModelImpl implements FluxoCaixaModel {

	private static final Logger LOG = Logger
			.getLogger(FluxoCaixaModelImpl.class);

	private static final String SELECT_FLUXO_CAIXA = "SELECT * FROM fluxocaixa(?, ?)";

	private static final String SELECT_ANO_BASE = "SELECT EXTRACT( YEAR FROM dataProcessamento) AS anobase "
			+ "FROM fluxocaixa GROUP BY 1 ORDER BY 1";

	@Override
	public List<FluxoCaixaViewBean> listarFluxoCaixa(String tipo,
			Integer anoBase) throws ClassNotFoundException, SQLException {
		LOG.info("Chamando método listarFluxoCaixa");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_FLUXO_CAIXA);
		pstmt.setString(1, tipo);
		pstmt.setInt(2, anoBase);
		rs = pstmt.executeQuery();

		List<FluxoCaixaViewBean> listaFluxoCaixaViewBeans = new ArrayList<FluxoCaixaViewBean>();
		FluxoCaixaViewBean fluxo = null;

		while (rs.next()) {
			fluxo = new FluxoCaixaViewBean();
			fluxo.setTitulo(rs.getString("titulo"));
			fluxo.setJaneiro(rs.getDouble("janeiro"));
			fluxo.setFevereiro(rs.getDouble("fevereiro"));
			fluxo.setMarco(rs.getDouble("marco"));
			fluxo.setAbril(rs.getDouble("abril"));
			fluxo.setMaio(rs.getDouble("maio"));
			fluxo.setJunho(rs.getDouble("junho"));
			fluxo.setJulho(rs.getDouble("julho"));
			fluxo.setAgosto(rs.getDouble("agosto"));
			fluxo.setSetembro(rs.getDouble("setembro"));
			fluxo.setOutubro(rs.getDouble("outubro"));
			fluxo.setNovembro(rs.getDouble("novembro"));
			fluxo.setDezembro(rs.getDouble("dezembro"));
			fluxo.setTotalPorTitulo(fluxo.getJaneiro() + fluxo.getFevereiro()
					+ fluxo.getMarco() + fluxo.getAbril() + fluxo.getMaio()
					+ fluxo.getJunho() + fluxo.getJulho() + fluxo.getAgosto()
					+ fluxo.getSetembro() + fluxo.getOutubro()
					+ fluxo.getNovembro() + fluxo.getDezembro());
			listaFluxoCaixaViewBeans.add(fluxo);
		}

		fecharConexao(rs, pstmt, conn);

		return listaFluxoCaixaViewBeans;
	}

	@Override
	public List<Integer> listarAnoBaso() throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método listarFluxoCaixa");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_ANO_BASE);
		rs = pstmt.executeQuery();

		List<Integer> lista = new ArrayList<Integer>();

		while (rs.next()) {
			Integer anoBase = rs.getInt("anobase");
			lista.add(anoBase);
		}

		fecharConexao(rs, pstmt, conn);

		return lista;
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
