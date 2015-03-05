/**
 * 
 */
package com.primos.lavacar.view;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Claudemir
 * 
 */
public class ReceitaDespesaViewBean implements Serializable {

	private static final long serialVersionUID = 850683553010345370L;

	private Integer id;
	private Integer idAgenda;
	private String titulo;
	private String tipo;
	private Date dataProcessamento;
	private Double valor;

	public ReceitaDespesaViewBean() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(Integer idAgenda) {
		this.idAgenda = idAgenda;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
