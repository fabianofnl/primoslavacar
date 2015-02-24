/**
 * 
 */
package com.primos.lavacar.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.primos.lavacar.view.AgendaViewBean;
import com.primos.lavacar.view.ServicoViewBean;

/**
 * Classe responsável pelo agendamento dos serviços
 * 
 * @author Claudemir
 * 
 */
@ManagedBean
@ViewScoped
public class AgendaManagedBean implements Serializable {

	private static final long serialVersionUID = -4173542154622910480L;

	private AgendaViewBean agendamento = new AgendaViewBean();
	private List<ServicoViewBean> listaServicos = new ArrayList<ServicoViewBean>();
	private List<AgendaViewBean> listaAgendamentos = new ArrayList<AgendaViewBean>();

	private ScheduleModel eventModelAgenda = new DefaultScheduleModel();
	private ScheduleEvent eventAgenda = new DefaultScheduleEvent();

	public void agendarServico(ActionEvent event) {

	}

	public void selecionarServico(SelectEvent event) {

	}

	public void selecionarData(SelectEvent event) {

	}

	public void moverServico(ScheduleEntryMoveEvent event) {

	}

	public void redimensionarServico(ScheduleEntryResizeEvent event) {

	}

	public List<ServicoViewBean> getListaServicos() {
		return listaServicos;
	}

	public void setListaServicos(List<ServicoViewBean> listaServicos) {
		this.listaServicos = listaServicos;
	}

	public List<AgendaViewBean> getListaAgendamentos() {
		return listaAgendamentos;
	}

	public void setListaAgendamentos(List<AgendaViewBean> listaAgendamentos) {
		this.listaAgendamentos = listaAgendamentos;
	}

	public AgendaViewBean getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(AgendaViewBean agendamento) {
		this.agendamento = agendamento;
	}

	public ScheduleModel getEventModelAgenda() {
		return eventModelAgenda;
	}

	public void setEventModelAgenda(ScheduleModel eventModelAgenda) {
		this.eventModelAgenda = eventModelAgenda;
	}

	public ScheduleEvent getEventAgenda() {
		return eventAgenda;
	}

	public void setEventAgenda(ScheduleEvent eventAgenda) {
		this.eventAgenda = eventAgenda;
	}

}
