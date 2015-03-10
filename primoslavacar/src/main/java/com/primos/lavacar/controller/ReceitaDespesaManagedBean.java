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
import com.primos.lavacar.model.ReceitaDespesaModel;
import com.primos.lavacar.model.ReceitaDespesaModelImpl;
import com.primos.lavacar.model.ServicoModel;
import com.primos.lavacar.model.ServicoModelImpl;
import com.primos.lavacar.view.ContaViewBean;
import com.primos.lavacar.view.ReceitaDespesaViewBean;
import com.primos.lavacar.view.ServicoViewBean;

/**
 * @author Claudemir
 * 
 */
@ManagedBean
@ViewScoped
public class ReceitaDespesaManagedBean implements Serializable {

	private static final long serialVersionUID = -2728428944291838197L;
	private static final Logger LOG = Logger
			.getLogger(ReceitaDespesaManagedBean.class);

	private List<ReceitaDespesaViewBean> listaReceitas = new ArrayList<ReceitaDespesaViewBean>();
	private List<ReceitaDespesaViewBean> listaDespesas = new ArrayList<ReceitaDespesaViewBean>();
	private List<ContaViewBean> listaContas = new ArrayList<ContaViewBean>();
	private List<ServicoViewBean> listaServicos = new ArrayList<ServicoViewBean>();

	private ReceitaDespesaViewBean receitaDespesaSelecionado = new ReceitaDespesaViewBean();
	private ReceitaDespesaViewBean despesaNova = new ReceitaDespesaViewBean();

	private ReceitaDespesaModel receitaDespesaModel = new ReceitaDespesaModelImpl();
	private ContaModel contaModel = new ContaModelImpl();
	private ServicoModel servicoModel = new ServicoModelImpl();

	public void carregarTabela(ActionEvent event) {
		LOG.info("carregarTabela");

		try {
			listaReceitas = receitaDespesaModel.listarReceitas();
			listaDespesas = receitaDespesaModel.listarDespesas();
			listaContas = contaModel.listarContasAtivas();
			listaServicos = servicoModel.listarServicosAtivos();
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

	public void cadastrarDespesa(ActionEvent event) {
		LOG.info("cadastrarDespesa");

		try {
			receitaDespesaModel.gravar(despesaNova);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							despesaNova.getTitulo()
									+ " cadastrada com sucesso!"));
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

	public void editarReceitaDespesa(ActionEvent event) {
		LOG.info("editarReceitaDespesa");

		try {
			receitaDespesaModel.alterar(receitaDespesaSelecionado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							receitaDespesaSelecionado.getTitulo()
									+ " alterada com sucesso!"));
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

	public void excluirReceitaDespesa(ActionEvent event) {
		LOG.info("excluirDespesa");

		try {
			receitaDespesaModel.excluir(receitaDespesaSelecionado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							receitaDespesaSelecionado.getTitulo()
									+ " excluída com sucesso!"));
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
		despesaNova = new ReceitaDespesaViewBean();
		receitaDespesaSelecionado = new ReceitaDespesaViewBean();
	}

	public List<ReceitaDespesaViewBean> getListaReceitas() {
		return listaReceitas;
	}

	public void setListaReceitas(List<ReceitaDespesaViewBean> listaReceitas) {
		this.listaReceitas = listaReceitas;
	}

	public List<ReceitaDespesaViewBean> getListaDespesas() {
		return listaDespesas;
	}

	public void setListaDespesas(List<ReceitaDespesaViewBean> listaDespesas) {
		this.listaDespesas = listaDespesas;
	}

	public List<ContaViewBean> getListaContas() {
		return listaContas;
	}

	public void setListaContas(List<ContaViewBean> listaContas) {
		this.listaContas = listaContas;
	}

	public List<ServicoViewBean> getListaServicos() {
		return listaServicos;
	}

	public void setListaServicos(List<ServicoViewBean> listaServicos) {
		this.listaServicos = listaServicos;
	}

	public ReceitaDespesaViewBean getReceitaDespesaSelecionado() {
		return receitaDespesaSelecionado;
	}

	public void setReceitaDespesaSelecionado(
			ReceitaDespesaViewBean receitaDespesaSelecionado) {
		this.receitaDespesaSelecionado = receitaDespesaSelecionado;
	}

	public ReceitaDespesaViewBean getDespesaNova() {
		return despesaNova;
	}

	public void setDespesaNova(ReceitaDespesaViewBean despesaNova) {
		this.despesaNova = despesaNova;
	}

}
