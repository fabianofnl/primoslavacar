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
 * Classe respons�vel pelo controle e gerenciamento dos Servi�os
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
	 * M�todo que carrega a lista de servi�os cadastrados na base de dados, essa
	 * chamada � feito atrav�s de uma requisi��o ajax do Primefaces/JSF
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
							"Houve um erro na aplica��o, tente mais tarde"));
			LOG.error("Driver do banco de dados n�o encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplica��o, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	/**
	 * M�todo que cadastra um servi�o no sistema.
	 * 
	 * @param event
	 */
	public void cadastrarServico(ActionEvent event) {

		try {
			servicoModel.gravar(servicoNovo);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							"Servi�o cadastrado com sucesso"));
			carregarTabela(event);
			limparSessao();
		} catch (ClassNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplica��o, tente mais tarde"));
			LOG.error("Driver do banco de dados n�o encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplica��o, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}

	}

	/**
	 * M�todo que efetiva a altera��o de dados do Servi�o.
	 * 
	 * @param event
	 */
	public void editarServico(ActionEvent event) {

		try {
			servicoModel.alterar(servicoSelecionado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							"Servi�o alterado com sucesso"));
			carregarTabela(event);
			limparSessao();
		} catch (ClassNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplica��o, tente mais tarde"));
			LOG.error("Driver do banco de dados n�o encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplica��o, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	/**
	 * M�todo que inativa um Servi�o.
	 * 
	 * @param event
	 */
	public void inativarServico(ActionEvent event) {

		try {
			servicoModel.inativar(servicoSelecionado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							"Servi�o inativado com sucesso"));
			carregarTabela(event);
			limparSessao();
		} catch (ClassNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplica��o, tente mais tarde"));
			LOG.error("Driver do banco de dados n�o encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplica��o, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	/**
	 * M�todo que limpa os dados da sess�o ap�s o cadastro, edi��o ou inativa��o
	 * de um servi�o
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
