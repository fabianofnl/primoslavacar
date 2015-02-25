package com.primos.lavacar.model;

import java.sql.SQLException;
import java.util.List;

import com.primos.lavacar.view.AgendaViewBean;

/**
 * @author Claudemir
 * 
 */
public interface AgendaModel {

	public List<AgendaViewBean> listarAgendamentos()
			throws ClassNotFoundException, SQLException;

	public List<AgendaViewBean> listarAgendamentosDarBaixa()
			throws ClassNotFoundException, SQLException;

	public void agendarServico(AgendaViewBean agendamento)
			throws ClassNotFoundException, SQLException;

}
