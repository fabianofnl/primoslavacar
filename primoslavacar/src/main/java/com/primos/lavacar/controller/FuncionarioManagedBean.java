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

import com.primos.lavacar.model.FuncionarioModel;
import com.primos.lavacar.model.FuncionarioModelImpl;
import com.primos.lavacar.view.FuncionarioViewBean;
import com.primos.lavacar.view.PerfilViewBean;

/**
 * Classe responsável pelo controle e gerenciamento dos Funcionários
 * 
 * @author Claudemir
 * 
 */
@ManagedBean
@ViewScoped
public class FuncionarioManagedBean implements Serializable {

	private static final long serialVersionUID = 6346351023373255811L;
	private static final Logger LOG = Logger
			.getLogger(FuncionarioManagedBean.class);

	private List<FuncionarioViewBean> listaFuncionarios = new ArrayList<FuncionarioViewBean>();
	private List<PerfilViewBean> listaPerfis = new ArrayList<PerfilViewBean>();
	private FuncionarioViewBean funcionarioSelecionado = new FuncionarioViewBean();
	private FuncionarioViewBean funcionarioNovo = new FuncionarioViewBean();

	private FuncionarioModel funcionarioModel = new FuncionarioModelImpl();

	/**
	 * Método que carrega a lista de funcionários cadastrados na base de dados,
	 * essa chamada é feito através de uma requisição ajax do Primefaces/JSF
	 * 
	 * @param event
	 */
	public void carregarTabela(ActionEvent event) {

		try {
			listaFuncionarios = funcionarioModel.listarFuncionarios();

			LOG.info("Total de funcionarios: " + listaFuncionarios.size());

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
	 * Método que cadastra o funcionário no sistema.
	 * 
	 * @param event
	 */
	public void cadastrarFuncionario(ActionEvent event) {

		try {
			funcionarioModel.gravar(funcionarioNovo);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							funcionarioNovo.getNome()
									+ " cadastrado(a) com sucesso."));

			carregarTabela(event);
			limparSessao();

		} catch (ClassNotFoundException e) {

			/**
			 * Linha abaixo força um erro de conversão ou validação Devido ao
			 * uso do componente Dialog, foi necessário utilizar para que o
			 * Dialog permaneça aberto durante o erro.
			 */
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {

			/**
			 * Linha abaixo força um erro de conversão ou validação Devido ao
			 * uso do componente Dialog, foi necessário utilizar para que o
			 * Dialog permaneça aberto durante o erro.
			 */
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}

	}

	/**
	 * Método que efetiva a alteração de dados do funcionário.
	 * 
	 * @param event
	 */
	public void editarFuncionario(ActionEvent event) {

		try {
			LOG.info("CPF atual: " + funcionarioSelecionado.getCpf()
					+ " - CPF antiga: " + funcionarioSelecionado.getCpfAntigo());

			funcionarioModel.alterar(funcionarioSelecionado,
					funcionarioSelecionado.getCpfAntigo());

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							funcionarioSelecionado.getNome()
									+ " alterado(a) com sucesso."));
			carregarTabela(event);
			limparSessao();
		} catch (ClassNotFoundException e) {

			/**
			 * Linha abaixo força um erro de conversão ou validação Devido ao
			 * uso do componente Dialog, foi necessário utilizar para que o
			 * Dialog permaneça aberto durante o erro.
			 */
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}

	}

	/**
	 * Método que inativa um funcionário.
	 * 
	 * @param event
	 */
	public void inativarFuncionario(ActionEvent event) {

		try {
			funcionarioModel.inativar(funcionarioSelecionado.getCpf());

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							funcionarioSelecionado.getNome()
									+ " inativado(a) com sucesso."));
			carregarTabela(event);
			limparSessao();
		} catch (ClassNotFoundException e) {

			/**
			 * Linha abaixo força um erro de conversão ou validação Devido ao
			 * uso do componente Dialog, foi necessário utilizar para que o
			 * Dialog permaneça aberto durante o erro.
			 */
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}

	}

	/**
	 * Método que carrega a lista de perfis do sistema.
	 * 
	 * @param event
	 */
	public void carregarPerfil(ActionEvent event) {
		try {
			listaPerfis = funcionarioModel.listarPerfis();
			LOG.info("Total de Perfis: " + listaPerfis.size());
		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	/**
	 * Método que limpa os dados da sessão após o cadastro, edição ou inativação
	 * de um funcionario
	 */
	private void limparSessao() {
		funcionarioNovo = new FuncionarioViewBean();
		funcionarioSelecionado = new FuncionarioViewBean();

	}

	public List<FuncionarioViewBean> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<FuncionarioViewBean> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public FuncionarioViewBean getFuncionarioSelecionado() {
		LOG.info("GET funcionario selecionado: "
				+ funcionarioSelecionado.getNome());
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(FuncionarioViewBean funcionarioSelecionado) {
		LOG.info("SET funcionario selecionado: "
				+ funcionarioSelecionado.getNome());
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public FuncionarioViewBean getFuncionarioNovo() {
		return funcionarioNovo;
	}

	public void setFuncionarioNovo(FuncionarioViewBean funcionarioNovo) {
		this.funcionarioNovo = funcionarioNovo;
	}

	public List<PerfilViewBean> getListaPerfis() {
		return listaPerfis;
	}

	public void setListaPerfis(List<PerfilViewBean> listaPerfis) {
		this.listaPerfis = listaPerfis;
	}
}
