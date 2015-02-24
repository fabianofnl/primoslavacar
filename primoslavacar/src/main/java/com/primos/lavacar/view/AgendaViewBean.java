/**
 * 
 */
package com.primos.lavacar.view;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe POJO/BEAN/DTO que representa a Agenda do sistema
 * 
 * @author Claudemir
 * 
 */
public class AgendaViewBean implements Serializable {

	private static final long serialVersionUID = 8602343283699434071L;

	private Integer id;
	private ServicoViewBean servico;
	private ClienteViewBean cliente;
	private Date dataHoraInicio;
	private Date dataHoraFim;

	public AgendaViewBean() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ServicoViewBean getServico() {
		return servico;
	}

	public void setServico(ServicoViewBean servico) {
		this.servico = servico;
	}

	public ClienteViewBean getCliente() {
		return cliente;
	}

	public void setCliente(ClienteViewBean cliente) {
		this.cliente = cliente;
	}

	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}
}
