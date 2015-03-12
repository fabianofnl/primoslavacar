/**
 * 
 */
package com.primos.lavacar.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.BarChartModel;

import com.primos.lavacar.model.FluxoCaixaModel;
import com.primos.lavacar.model.FluxoCaixaModelImpl;
import com.primos.lavacar.view.FluxoCaixaViewBean;
import com.primos.lavacar.view.TotalizadorViewBean;

/**
 * @author Claudemir
 * 
 */
@ManagedBean
@ViewScoped
public class FluxoCaixaManagedBean implements Serializable {

	private static final long serialVersionUID = 1083290488845497069L;

	private static final Logger LOG = Logger
			.getLogger(FluxoCaixaManagedBean.class);

	private static final String RECEITA = "R";
	private static final String DESPESA = "D";

	private Integer anoBase;

	private List<FluxoCaixaViewBean> listaFluxosCaixaReceita = new ArrayList<FluxoCaixaViewBean>();
	private List<FluxoCaixaViewBean> listaFluxosCaixaDespesa = new ArrayList<FluxoCaixaViewBean>();
	private List<Integer> listaAnoBase = new ArrayList<Integer>();

	private TotalizadorViewBean totalizador = new TotalizadorViewBean();

	private BarChartModel barChartModel = new BarChartModel();

	private FluxoCaixaModel fluxoCaixaModel = new FluxoCaixaModelImpl();

	public void carregarTabela(ActionEvent event) {

		try {

			if (anoBase == null || anoBase == 0)
				anoBase = new Integer(
						new SimpleDateFormat("yyyy").format(new Date()));

			listaAnoBase = fluxoCaixaModel.listarAnoBaso();
			listaFluxosCaixaReceita = fluxoCaixaModel.listarFluxoCaixa(RECEITA,
					anoBase);
			listaFluxosCaixaDespesa = fluxoCaixaModel.listarFluxoCaixa(DESPESA,
					anoBase);

			processarTotalizadores();

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

	private void processarTotalizadores() {
		for (FluxoCaixaViewBean fluxoReceita : listaFluxosCaixaReceita) {
			totalizador.setReceitaJaneiro(totalizador.getReceitaJaneiro()
					+ fluxoReceita.getJaneiro());
			totalizador.setReceitaFevereiro(totalizador.getReceitaFevereiro()
					+ fluxoReceita.getFevereiro());
			totalizador.setReceitaMarco(totalizador.getReceitaMarco()
					+ fluxoReceita.getMarco());
			totalizador.setReceitaAbril(totalizador.getReceitaAbril()
					+ fluxoReceita.getAbril());
			totalizador.setReceitaMaio(totalizador.getReceitaMaio()
					+ fluxoReceita.getMaio());
			totalizador.setReceitaJunho(totalizador.getReceitaJunho()
					+ fluxoReceita.getJunho());
			totalizador.setReceitaJulho(totalizador.getReceitaJulho()
					+ fluxoReceita.getJulho());
			totalizador.setReceitaAgosto(totalizador.getReceitaAgosto()
					+ fluxoReceita.getAgosto());
			totalizador.setReceitaSetembro(totalizador.getReceitaSetembro()
					+ fluxoReceita.getSetembro());
			totalizador.setReceitaOutubro(totalizador.getReceitaOutubro()
					+ fluxoReceita.getOutubro());
			totalizador.setReceitaNovembro(totalizador.getReceitaNovembro()
					+ fluxoReceita.getNovembro());
			totalizador.setReceitaDezembro(totalizador.getReceitaDezembro()
					+ fluxoReceita.getDezembro());
		}

		for (FluxoCaixaViewBean fluxoDespesa : listaFluxosCaixaDespesa) {
			totalizador.setDespesaJaneiro(totalizador.getDespesaJaneiro()
					+ fluxoDespesa.getJaneiro());
			totalizador.setDespesaFevereiro(totalizador.getDespesaFevereiro()
					+ fluxoDespesa.getFevereiro());
			totalizador.setDespesaMarco(totalizador.getDespesaMarco()
					+ fluxoDespesa.getMarco());
			totalizador.setDespesaAbril(totalizador.getDespesaAbril()
					+ fluxoDespesa.getAbril());
			totalizador.setDespesaMaio(totalizador.getDespesaMaio()
					+ fluxoDespesa.getMaio());
			totalizador.setDespesaJunho(totalizador.getDespesaJunho()
					+ fluxoDespesa.getJunho());
			totalizador.setDespesaJulho(totalizador.getDespesaJulho()
					+ fluxoDespesa.getJulho());
			totalizador.setDespesaAgosto(totalizador.getDespesaAgosto()
					+ fluxoDespesa.getAgosto());
			totalizador.setDespesaSetembro(totalizador.getDespesaSetembro()
					+ fluxoDespesa.getSetembro());
			totalizador.setDespesaOutubro(totalizador.getDespesaOutubro()
					+ fluxoDespesa.getOutubro());
			totalizador.setDespesaNovembro(totalizador.getDespesaNovembro()
					+ fluxoDespesa.getNovembro());
			totalizador.setDespesaDezembro(totalizador.getDespesaDezembro()
					+ fluxoDespesa.getDezembro());
		}
	}

	public Integer getAnoBase() {
		return anoBase;
	}

	public void setAnoBase(Integer anoBase) {
		this.anoBase = anoBase;
	}

	public List<FluxoCaixaViewBean> getListaFluxosCaixaReceita() {
		return listaFluxosCaixaReceita;
	}

	public void setListaFluxosCaixaReceita(
			List<FluxoCaixaViewBean> listaFluxosCaixaReceita) {
		this.listaFluxosCaixaReceita = listaFluxosCaixaReceita;
	}

	public List<FluxoCaixaViewBean> getListaFluxosCaixaDespesa() {
		return listaFluxosCaixaDespesa;
	}

	public void setListaFluxosCaixaDespesa(
			List<FluxoCaixaViewBean> listaFluxosCaixaDespesa) {
		this.listaFluxosCaixaDespesa = listaFluxosCaixaDespesa;
	}

	public List<Integer> getListaAnoBase() {
		return listaAnoBase;
	}

	public void setListaAnoBase(List<Integer> listaAnoBase) {
		this.listaAnoBase = listaAnoBase;
	}

	public TotalizadorViewBean getTotalizador() {
		return totalizador;
	}

	public void setTotalizador(TotalizadorViewBean totalizador) {
		this.totalizador = totalizador;
	}

	public BarChartModel getBarChartModel() {
		return barChartModel;
	}
}
