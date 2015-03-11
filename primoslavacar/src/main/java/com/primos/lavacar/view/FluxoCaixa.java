/**
 * 
 */
package com.primos.lavacar.view;

import java.io.Serializable;

/**
 * @author Claudemir
 * 
 */
public class FluxoCaixa implements Serializable {

	private static final long serialVersionUID = -868657873265079009L;

	private String titulo;
	private Double janeiro;
	private Double fevereiro;
	private Double marco;
	private Double abril;
	private Double maio;
	private Double junho;
	private Double julho;
	private Double agosto;
	private Double setembro;
	private Double outubro;
	private Double novembro;
	private Double dezembro;
	private Double totalPorTitulo;

	public FluxoCaixa() {
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getJaneiro() {
		return janeiro;
	}

	public void setJaneiro(Double janeiro) {
		this.janeiro = janeiro;
	}

	public Double getFevereiro() {
		return fevereiro;
	}

	public void setFevereiro(Double fevereiro) {
		this.fevereiro = fevereiro;
	}

	public Double getMarco() {
		return marco;
	}

	public void setMarco(Double marco) {
		this.marco = marco;
	}

	public Double getAbril() {
		return abril;
	}

	public void setAbril(Double abril) {
		this.abril = abril;
	}

	public Double getMaio() {
		return maio;
	}

	public void setMaio(Double maio) {
		this.maio = maio;
	}

	public Double getJunho() {
		return junho;
	}

	public void setJunho(Double junho) {
		this.junho = junho;
	}

	public Double getJulho() {
		return julho;
	}

	public void setJulho(Double julho) {
		this.julho = julho;
	}

	public Double getAgosto() {
		return agosto;
	}

	public void setAgosto(Double agosto) {
		this.agosto = agosto;
	}

	public Double getSetembro() {
		return setembro;
	}

	public void setSetembro(Double setembro) {
		this.setembro = setembro;
	}

	public Double getOutubro() {
		return outubro;
	}

	public void setOutubro(Double outubro) {
		this.outubro = outubro;
	}

	public Double getNovembro() {
		return novembro;
	}

	public void setNovembro(Double novembro) {
		this.novembro = novembro;
	}

	public Double getDezembro() {
		return dezembro;
	}

	public void setDezembro(Double dezembro) {
		this.dezembro = dezembro;
	}

	public Double getTotalPorTitulo() {
		return totalPorTitulo;
	}

	public void setTotalPorTitulo(Double totalPorTitulo) {
		this.totalPorTitulo = totalPorTitulo;
	}
}
