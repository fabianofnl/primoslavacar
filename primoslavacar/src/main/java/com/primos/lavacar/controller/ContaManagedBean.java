/**
 * 
 */
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

import com.primos.lavacar.model.ContaModel;
import com.primos.lavacar.model.ContaModelImpl;
import com.primos.lavacar.view.ContaViewBean;

/**
 * @author Claudemir
 * 
 */
@ManagedBean
@ViewScoped
public class ContaManagedBean implements Serializable {

	private static final long serialVersionUID = 4015250332789498283L;
	private static final Logger LOG = Logger.getLogger(ContaManagedBean.class);

	private ContaViewBean contaNova = new ContaViewBean();
	private ContaViewBean contaSelecionada = new ContaViewBean();

	private List<ContaViewBean> listaContas = new ArrayList<ContaViewBean>();

	private ContaModel contaModel = new ContaModelImpl();

	public void carregarTabela(ActionEvent event) {
		LOG.info("carregarTabela");

		try {
			listaContas = contaModel.listarContas();
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

	public void cadastrarConta(ActionEvent event) {
		LOG.info("cadastrarConta");

		try {
			contaModel.cadastrarConta(contaNova);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							"Conta cadastrada com sucesso."));

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

	public void editarConta(ActionEvent event) {
		LOG.info("editarConta");

		try {
			contaModel.editarConta(contaSelecionada);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							"Conta alterada com sucesso."));

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

	public void inativarConta(ActionEvent event) {
		LOG.info("inativarConta");

		try {
			contaModel.inativarConta(contaSelecionada);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							"Conta inativada com sucesso."));

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

	private void limparSessao() {
		contaNova = new ContaViewBean();
		contaSelecionada = new ContaViewBean();
	}

	public ContaViewBean getContaNova() {
		return contaNova;
	}

	public void setContaNova(ContaViewBean contaNova) {
		this.contaNova = contaNova;
	}

	public ContaViewBean getContaSelecionada() {
		return contaSelecionada;
	}

	public void setContaSelecionada(ContaViewBean contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}

	public List<ContaViewBean> getListaContas() {
		return listaContas;
	}

	public void setListaContas(List<ContaViewBean> listaContas) {
		this.listaContas = listaContas;
	}
}
