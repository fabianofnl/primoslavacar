package com.primos.lavacar.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.primos.lavacar.model.ServicoModel;
import com.primos.lavacar.model.ServicoModelImpl;
import com.primos.lavacar.view.ServicoViewBean;

/**
 * Classe responsável pelo controle e gerenciamento dos Serviços
 * 
 * @author Claudemir
 * 
 */
@ManagedBean
@ViewScoped
public class ServicoManagedBean implements Serializable {

	private static final long serialVersionUID = -3054057207566030599L;

	private static final Logger LOG = Logger
			.getLogger(ServicoManagedBean.class);

	private List<ServicoViewBean> listaServicos = new ArrayList<ServicoViewBean>();
	private ServicoViewBean servicoNovo = new ServicoViewBean();
	private ServicoViewBean servicoSelecionado = new ServicoViewBean();

	private ServicoModel servicoModel = new ServicoModelImpl();

	/**
	 * Método que carrega a lista de serviços cadastrados na base de dados, essa
	 * chamada é feito através de uma requisição ajax do Primefaces/JSF
	 * 
	 * @param event
	 */
	public void carregarTabela(ActionEvent event) {

		try {
			listaServicos = servicoModel.listarServicos();
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

	/**
	 * Método que cadastra um serviço no sistema.
	 * 
	 * @param event
	 */
	public void cadastrarServico(ActionEvent event) {

		try {
			servicoModel.gravar(servicoNovo);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							"Serviço cadastrado com sucesso"));
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

	/**
	 * Método que efetiva a alteração de dados do Serviço.
	 * 
	 * @param event
	 */
	public void editarServico(ActionEvent event) {

		try {
			servicoModel.alterar(servicoSelecionado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							"Serviço alterado com sucesso"));
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

	/**
	 * Método que inativa um Serviço.
	 * 
	 * @param event
	 */
	public void inativarServico(ActionEvent event) {

		try {
			servicoModel.inativar(servicoSelecionado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							"Serviço inativado com sucesso"));
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

	/**
	 * Método que limpa os dados da sessão após o cadastro, edição ou inativação
	 * de um serviço
	 */
	private void limparSessao() {
		servicoNovo = new ServicoViewBean();
		servicoSelecionado = new ServicoViewBean();
	}

	public List<ServicoViewBean> getListaServicos() {
		return listaServicos;
	}

	public void setListaServicos(List<ServicoViewBean> listaServicos) {
		this.listaServicos = listaServicos;
	}

	public ServicoViewBean getServicoNovo() {
		return servicoNovo;
	}

	public void setServicoNovo(ServicoViewBean servicoNovo) {
		this.servicoNovo = servicoNovo;
	}

	public ServicoViewBean getServicoSelecionado() {
		return servicoSelecionado;
	}

	public void setServicoSelecionado(ServicoViewBean servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}
}
