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
	private Date dataInicio;
	private Date dataFim;

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

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
}
