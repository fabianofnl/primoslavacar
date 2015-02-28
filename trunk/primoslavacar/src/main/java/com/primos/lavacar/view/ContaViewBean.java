/**
 * 
 */
package com.primos.lavacar.view;

import java.io.Serializable;

/**
 * @author Fabiano
 * 
 */
public class ContaViewBean implements Serializable {

	private static final long serialVersionUID = 8498748549888502643L;

	private Integer id;
	private String nome;
	private String descricao;
	private String status;

	public ContaViewBean() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
