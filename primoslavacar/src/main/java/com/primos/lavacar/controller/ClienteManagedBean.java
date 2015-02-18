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

import com.primos.lavacar.model.ClienteModel;
import com.primos.lavacar.model.ClienteModelImpl;
import com.primos.lavacar.view.ClienteViewBean;

/**
 * Classe respons�vel pelo controle e gerenciamento dos Clientes
 * 
 * @author Claudemir
 * 
 */
@ManagedBean
@ViewScoped
public class ClienteManagedBean implements Serializable {

	private static final long serialVersionUID = 6346351023373255811L;
	private static final Logger LOG = Logger
			.getLogger(ClienteManagedBean.class);

	private List<ClienteViewBean> listaClientes = new ArrayList<ClienteViewBean>();
	private ClienteViewBean clienteSelecionado = new ClienteViewBean();
	private ClienteViewBean clienteNovo = new ClienteViewBean();

	private ClienteModel clienteModel = new ClienteModelImpl();

	/**
	 * M�todo que carrega a lista de clientes cadastrados na base de dados,
	 * essa chamada � feito atrav�s de uma requisi��o ajax do Primefaces/JSF
	 * 
	 * @param event
	 */
	public void carregarTabela(ActionEvent event) {

		try {
			listaClientes = clienteModel.listarClientes();

			LOG.info("Total de funcionarios: " + listaClientes.size());

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
	 * M�todo que cadastra o cliente no sistema.
	 * 
	 * @param event
	 */
	public void cadastrarCliente(ActionEvent event) {

		try {
			clienteModel.gravar(clienteNovo);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							clienteNovo.getNome()
									+ " cadastrado(a) com sucesso."));

			carregarTabela(event);
			limparSessao();

		} catch (ClassNotFoundException e) {

			/**
			 * Linha abaixo for�a um erro de convers�o ou valida��o Devido ao
			 * uso do componente Dialog, foi necess�rio utilizar para que o
			 * Dialog permane�a aberto durante o erro.
			 */
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplica��o, tente mais tarde"));
			LOG.error("Driver do banco de dados n�o encontrado", e);
		} catch (SQLException e) {

			/**
			 * Linha abaixo for�a um erro de convers�o ou valida��o Devido ao
			 * uso do componente Dialog, foi necess�rio utilizar para que o
			 * Dialog permane�a aberto durante o erro.
			 */
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplica��o, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}

	}

	/**
	 * M�todo que efetiva a altera��o de dados do cliente.
	 * 
	 * @param event
	 */
	public void editarCliente(ActionEvent event) {

		try {
			LOG.info("CPF atual: " + clienteSelecionado.getCpf()
					+ " - CPF antiga: " + clienteSelecionado.getCpfAntigo());

			clienteModel.alterar(clienteSelecionado,
					clienteSelecionado.getCpfAntigo());

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							clienteSelecionado.getNome()
									+ " alterado(a) com sucesso."));
			carregarTabela(event);
			limparSessao();
		} catch (ClassNotFoundException e) {

			/**
			 * Linha abaixo for�a um erro de convers�o ou valida��o Devido ao
			 * uso do componente Dialog, foi necess�rio utilizar para que o
			 * Dialog permane�a aberto durante o erro.
			 */
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplica��o, tente mais tarde"));
			LOG.error("Driver do banco de dados n�o encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplica��o, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}

	}

	/**
	 * M�todo que inativa um cliente.
	 * 
	 * @param event
	 */
	public void inativarCliente(ActionEvent event) {

		try {
			clienteModel.inativar(clienteSelecionado.getCpf());

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							clienteSelecionado.getNome()
									+ " inativado(a) com sucesso."));
			carregarTabela(event);
			limparSessao();
		} catch (ClassNotFoundException e) {

			/**
			 * Linha abaixo for�a um erro de convers�o ou valida��o Devido ao
			 * uso do componente Dialog, foi necess�rio utilizar para que o
			 * Dialog permane�a aberto durante o erro.
			 */
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplica��o, tente mais tarde"));
			LOG.error("Driver do banco de dados n�o encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplica��o, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}

	}

	/**
	 * M�todo que limpa os dados da sess�o ap�s o cadastro, edi��o ou inativa��o
	 * de um cliente
	 */
	private void limparSessao() {
		clienteNovo = new ClienteViewBean();
		clienteSelecionado = new ClienteViewBean();

	}

	public List<ClienteViewBean> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<ClienteViewBean> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ClienteViewBean getClienteSelecionado() {
		LOG.info("GET cliente selecionado: " + clienteSelecionado.getNome());
		return clienteSelecionado;
	}

	public void setClienteSelecionado(ClienteViewBean clienteSelecionado) {
		LOG.info("SET cliente selecionado: " + clienteSelecionado.getNome());
		this.clienteSelecionado = clienteSelecionado;
	}

	public ClienteViewBean getClienteNovo() {
		return clienteNovo;
	}

	public void setClienteNovo(ClienteViewBean clienteNovo) {
		this.clienteNovo = clienteNovo;
	}
}
