/**
 * 
 */
package com.primos.lavacar.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.primos.lavacar.utils.ConexaoBaseDados;
import com.primos.lavacar.view.AgendaViewBean;
import com.primos.lavacar.view.ClienteViewBean;
import com.primos.lavacar.view.ServicoViewBean;

/**
 * @author Claudemir
 * 
 */
public class AgendaModelImpl implements AgendaModel {

	private final static Logger LOG = Logger.getLogger(AgendaModelImpl.class);

	private static final String SELECT_LISTA_TODOS_AGENDAMENTOS = "SELECT a.id AS idagenda, s.id AS idservico, "
			+ "s.nome AS nomeservico, c.nome AS nomecliente, * "
			+ "FROM agenda a, cliente c, servico s "
			+ "WHERE a.cpf = c.cpf AND a.idServico = s.id ORDER BY a.dataInicio";

	private static final String SELECT_LISTA_AGENDAMENTOS_BAIXA = "SELECT a.id AS idagenda, s.id AS idservico, "
			+ "s.nome AS nomeservico, c.nome AS nomecliente, * "
			+ "FROM agenda a, cliente c, servico s "
			+ "WHERE a.cpf = c.cpf AND a.idServico = s.id AND a.baixa = false ORDER BY a.dataInicio";

	private static final String INSERT_AGENDAMENTO = "INSERT INTO agenda (idServico, cpf, dataInicio, dataFim, baixa) "
			+ "VALUES (?, ?, ?, ?, ?)";

	private static final String DELETE_AGENDAMENTO = "DELETE FROM agenda WHERE id = ?";

	private static final String BAIXAR_SERVICO_AGENDAMENTO = "BEGIN; "
			+ "UPDATE agenda SET baixa = true WHERE id = ?; "
			+ "INSERT INTO fluxocaixa (titulo, tipo, dataProcessamento, valor, idAgenda) VALUES (?, 'R', ?, ?, ?); "
			+ "COMMIT;";

	public List<AgendaViewBean> listarAgendamentos()
			throws ClassNotFoundException, SQLException {
		LOG.info("Chamando método listarAgendamentos");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_LISTA_TODOS_AGENDAMENTOS);
		rs = pstmt.executeQuery();

		List<AgendaViewBean> listaAgendamentos = new ArrayList<AgendaViewBean>();
		AgendaViewBean agenda = null;
		ClienteViewBean cliente = null;
		ServicoViewBean servico = null;

		while (rs.next()) {
			agenda = new AgendaViewBean();
			agenda.setId(rs.getInt("idagenda"));
			agenda.setDataInicio(rs.getTimestamp("dataInicio") == null ? null
					: new Date(rs.getTimestamp("dataInicio").getTime()));
			agenda.setDataFim(rs.getTimestamp("dataInicio") == null ? null
					: new Date(rs.getTimestamp("dataFim").getTime()));

			cliente = new ClienteViewBean();
			cliente.setCpf(rs.getLong("cpf"));
			cliente.setNome(rs.getString("nomecliente"));
			cliente.setEnderecoNumero(rs.getString("enderecoNumero"));
			cliente.setEmail(rs.getString("email"));
			cliente.setTelefone(rs.getLong("telefone"));
			cliente.setStatus(rs.getString("status"));

			agenda.setCliente(cliente);

			servico = new ServicoViewBean();
			servico.setId(rs.getInt("idservico"));
			servico.setNome(rs.getString("nomeservico"));
			servico.setDescricao(rs.getString("descricao"));
			servico.setValor(rs.getDouble("valor"));
			servico.setStatus(rs.getString("status"));

			agenda.setServico(servico);
			listaAgendamentos.add(agenda);
		}

		fecharConexao(rs, pstmt, conn);

		return listaAgendamentos;
	}

	public List<AgendaViewBean> listarAgendamentosDarBaixa()
			throws ClassNotFoundException, SQLException {
		LOG.info("Chamando método listarAgendamentosDarBaixa");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_LISTA_AGENDAMENTOS_BAIXA);
		rs = pstmt.executeQuery();

		List<AgendaViewBean> listaAgendamentosBaixa = new ArrayList<AgendaViewBean>();
		AgendaViewBean agenda = null;
		ClienteViewBean cliente = null;
		ServicoViewBean servico = null;

		while (rs.next()) {
			agenda = new AgendaViewBean();
			agenda.setId(rs.getInt("idagenda"));
			agenda.setDataInicio(rs.getTimestamp("dataInicio") == null ? null
					: new Date(rs.getTimestamp("dataInicio").getTime()));
			agenda.setDataFim(rs.getTimestamp("dataInicio") == null ? null
					: new Date(rs.getTimestamp("dataFim").getTime()));

			cliente = new ClienteViewBean();
			cliente.setCpf(rs.getLong("cpf"));
			cliente.setNome(rs.getString("nomecliente"));
			cliente.setEnderecoNumero(rs.getString("enderecoNumero"));
			cliente.setEmail(rs.getString("email"));
			cliente.setTelefone(rs.getLong("telefone"));
			cliente.setStatus(rs.getString("status"));

			agenda.setCliente(cliente);

			servico = new ServicoViewBean();
			servico.setId(rs.getInt("idservico"));
			servico.setNome(rs.getString("nomeservico"));
			servico.setDescricao(rs.getString("descricao"));
			servico.setValor(rs.getDouble("valor"));
			servico.setStatus(rs.getString("status"));

			agenda.setServico(servico);
			listaAgendamentosBaixa.add(agenda);
		}

		fecharConexao(rs, pstmt, conn);

		return listaAgendamentosBaixa;
	}

	public void agendarServico(AgendaViewBean agendamento)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método agendarServico");

		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_AGENDAMENTO);
		pstmt.setInt(1, agendamento.getServico().getId());
		pstmt.setLong(2, agendamento.getCliente().getCpf());
		pstmt.setTimestamp(3, new Timestamp(agendamento.getDataInicio()
				.getTime()));
		pstmt.setTimestamp(4, new Timestamp(agendamento.getDataFim().getTime()));
		pstmt.setBoolean(5, false);
		pstmt.execute();

		fecharConexao(null, pstmt, conn);
	}

	public void cancelarServico(AgendaViewBean agendamentoSelecionado)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método cancelarServico");

		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DELETE_AGENDAMENTO);
		pstmt.setInt(1, agendamentoSelecionado.getId());
		pstmt.execute();

		fecharConexao(null, pstmt, conn);
	}

	public void baixarServico(AgendaViewBean agendamentoSelecionado)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método baixarServico");

		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(BAIXAR_SERVICO_AGENDAMENTO);
		pstmt.setInt(1, agendamentoSelecionado.getId());
		pstmt.setString(2, agendamentoSelecionado.getServico().getNome());
		pstmt.setDate(3, new java.sql.Date(new Date().getTime()));
		pstmt.setDouble(4, agendamentoSelecionado.getServico().getValor());
		pstmt.setInt(5, agendamentoSelecionado.getId());

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
