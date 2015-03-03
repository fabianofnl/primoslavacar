/**
 * 
 */
package com.primos.lavacar.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.primos.lavacar.model.AgendaModel;
import com.primos.lavacar.model.AgendaModelImpl;
import com.primos.lavacar.model.ClienteModel;
import com.primos.lavacar.model.ClienteModelImpl;
import com.primos.lavacar.model.ServicoModel;
import com.primos.lavacar.model.ServicoModelImpl;
import com.primos.lavacar.view.AgendaViewBean;
import com.primos.lavacar.view.ClienteViewBean;
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

	private static final Logger LOG = Logger.getLogger(AgendaManagedBean.class);

	private AgendaViewBean agendamento = new AgendaViewBean();
	private AgendaViewBean agendamentoSelecionado = new AgendaViewBean();
	private ServicoViewBean servicoSelecionado = new ServicoViewBean();
	private ClienteViewBean clienteSelecionado = new ClienteViewBean();

	private List<ServicoViewBean> listaServicos = new ArrayList<ServicoViewBean>();
	private List<ClienteViewBean> listaClientes = new ArrayList<ClienteViewBean>();
	private List<AgendaViewBean> listaAgendamentos = new ArrayList<AgendaViewBean>();
	private List<AgendaViewBean> listaAgendamentosDarBaixa = new ArrayList<AgendaViewBean>();

	private ScheduleModel eventModelAgenda = new DefaultScheduleModel();
	private ScheduleEvent eventAgenda = new DefaultScheduleEvent();

	private AgendaModel agendaModel = new AgendaModelImpl();
	private ServicoModel servicoModel = new ServicoModelImpl();
	private ClienteModel clienteModel = new ClienteModelImpl();

	public void carregarTabela(ActionEvent event) {
		try {

			listaClientes = clienteModel.listarClientesAtivos();
			listaServicos = servicoModel.listarServicosAtivos();
			listaAgendamentosDarBaixa = agendaModel
					.listarAgendamentosDarBaixa();
			listaAgendamentos = agendaModel.listarAgendamentos();
			eventModelAgenda.clear();

			for (AgendaViewBean agendaViewBean : listaAgendamentos) {
				eventModelAgenda.addEvent(new DefaultScheduleEvent("Serviço: "
						+ agendaViewBean.getServico().getNome() + ", cliente: "
						+ agendaViewBean.getCliente().getNome(), agendaViewBean
						.getDataInicio(), agendaViewBean.getDataFim()));
			}

		} catch (ClassNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	public void agendarServico(ActionEvent event) {
		LOG.info("Agendar Serviço [Add or Update]");
		try {

			if (eventAgenda.getId() == null) {
				// eventModelAgenda.addEvent(eventAgenda);
				agendamento.setCliente(clienteSelecionado);
				agendamento.setServico(servicoSelecionado);
				agendaModel.agendarServico(agendamento);
			} else {
				// eventModelAgenda.updateEvent(eventAgenda);
			}
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							"Serviço agendado com sucesso."));

			carregarTabela(event);
			limparSessao();
		} catch (ClassNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	public void selecionarServico(SelectEvent event) {
		LOG.info("Selecionar Serviço");
		eventAgenda = (ScheduleEvent) event.getObject();
	}

	public void selecionarData(SelectEvent event) {
		LOG.info("Selecionar Data");
		eventAgenda = new DefaultScheduleEvent("", (Date) event.getObject(),
				(Date) event.getObject());
		agendamento.setDataInicio(eventAgenda.getStartDate());
		agendamento.setDataFim(eventAgenda.getEndDate());
	}

	public void moverServico(ScheduleEntryMoveEvent event) {
		LOG.info("Mover Serviço");
	}

	public void redimensionarServico(ScheduleEntryResizeEvent event) {
		LOG.info("Redimensionar Serviço");
	}

	public void baixarServico(ActionEvent event) {
		try {
			agendaModel.baixarServico(agendamentoSelecionado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							"Baixa efetuada com sucesso."));
			carregarTabela(event);
			limparSessao();
		} catch (ClassNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	public void cancelarServico(ActionEvent event) {
		try {
			agendaModel.cancelarServico(agendamentoSelecionado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							"Serviço cancelado com sucesso."));
			limparSessao();
			carregarTabela(event);
		} catch (ClassNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	private void limparSessao() {
		eventAgenda = new DefaultScheduleEvent();
		servicoSelecionado = new ServicoViewBean();
		clienteSelecionado = new ClienteViewBean();
		agendamentoSelecionado = new AgendaViewBean();
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

	public List<ClienteViewBean> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<ClienteViewBean> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<AgendaViewBean> getListaAgendamentosDarBaixa() {
		return listaAgendamentosDarBaixa;
	}

	public void setListaAgendamentosDarBaixa(
			List<AgendaViewBean> listaAgendamentosDarBaixa) {
		this.listaAgendamentosDarBaixa = listaAgendamentosDarBaixa;
	}

	public AgendaViewBean getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(AgendaViewBean agendamento) {
		this.agendamento = agendamento;
	}

	public AgendaViewBean getAgendamentoSelecionado() {
		return agendamentoSelecionado;
	}

	public void setAgendamentoSelecionado(AgendaViewBean agendamentoSelecionado) {
		this.agendamentoSelecionado = agendamentoSelecionado;
	}

	public ServicoViewBean getServicoSelecionado() {
		return servicoSelecionado;
	}

	public void setServicoSelecionado(ServicoViewBean servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}

	public ClienteViewBean getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(ClienteViewBean clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
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
