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

import com.primos.lavacar.bean.FuncionarioDTO;
import com.primos.lavacar.bean.PerfilDTO;
import com.primos.lavacar.model.FuncionarioModel;
import com.primos.lavacar.model.FuncionarioModelImpl;

/**
 * Classe respons�vel pelo controle e gerenciamento dos Funcion�rios
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

	private List<FuncionarioDTO> listaFuncionarios = new ArrayList<FuncionarioDTO>();
	private List<PerfilDTO> listaPerfis = new ArrayList<PerfilDTO>();
	private FuncionarioDTO funcionarioSelecionado = new FuncionarioDTO();
	private FuncionarioDTO funcionarioNovo = new FuncionarioDTO();

	private FuncionarioModel funcionarioModel = new FuncionarioModelImpl();

	/**
	 * M�todo que carrega a lista de funcion�rios cadastrados na base de dados,
	 * essa chamada � feito atrav�s de uma requisi��o ajax do Primefaces/JSF
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
	 * M�todo que cadastra o funcion�rio no sistema.
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
	 * M�todo que efetiva a altera��o de dados do funcion�rio.
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
	 * M�todo que inativa um funcion�rio.
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
	 * M�todo que carrega a lista de perfis do sistema.
	 * 
	 * @param event
	 */
	public void carregarPerfil(ActionEvent event) {
		try {
			listaPerfis = funcionarioModel.listarPerfis();
			LOG.info("Total de Perfis: " + listaPerfis.size());
		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados n�o encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	/**
	 * M�todo que limpa os dados da sess�o ap�s o cadastro, edi��o ou inativa��o
	 * de um funcionario
	 */
	private void limparSessao() {
		funcionarioNovo = new FuncionarioDTO();
		funcionarioSelecionado = new FuncionarioDTO();

	}

	public List<FuncionarioDTO> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<FuncionarioDTO> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public FuncionarioDTO getFuncionarioSelecionado() {
		LOG.info("GET funcionario selecionado: "
				+ funcionarioSelecionado.getNome());
		funcionarioSelecionado.setCpfAntigo(funcionarioSelecionado.getCpf());
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(FuncionarioDTO funcionarioSelecionado) {
		LOG.info("SET funcionario selecionado: "
				+ funcionarioSelecionado.getNome());
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public FuncionarioDTO getFuncionarioNovo() {
		return funcionarioNovo;
	}

	public void setFuncionarioNovo(FuncionarioDTO funcionarioNovo) {
		this.funcionarioNovo = funcionarioNovo;
	}

	public List<PerfilDTO> getListaPerfis() {
		return listaPerfis;
	}

	public void setListaPerfis(List<PerfilDTO> listaPerfis) {
		this.listaPerfis = listaPerfis;
	}
}
