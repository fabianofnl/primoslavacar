package com.primos.lavacar.view;

import java.io.Serializable;

/**
 * Classe POJO/BEAN/DTO que representa o Funcionario do sistema
 * 
 * @author Claudemir
 * 
 */
public class FuncionarioViewBean extends UsuarioViewBean implements Serializable {

	private static final long serialVersionUID = 3576824392171045517L;

	private Integer cpf;
	private Integer cpfAntigo;
	private String nome;
	private String email;
	private String status;

	public FuncionarioViewBean() {
	}

	/**
	 * Método retorna <b>cpf</b> do usuario.
	 * 
	 * @return cpf
	 */
	public Integer getCpf() {
		return cpf;
	}

	/**
	 * Método atribui <b>cpf</b> ao usuario.
	 * 
	 * @param cpf
	 */
	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	/**
	 * Método retorna <b>cpfAntigo</b> do usuario.
	 * 
	 * @return cpfAntigo
	 */
	public Integer getCpfAntigo() {
		return cpfAntigo;
	}

	/**
	 * Método atribui <b>cpfAntigo</b> ao usuario.
	 * 
	 * @param cpfAntigo
	 */
	public void setCpfAntigo(Integer cpfAntigo) {
		this.cpfAntigo = cpfAntigo;
	}

	/**
	 * Método retorna <b>nome</b> do usuario.
	 * 
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Método atribui <b>nome</b> ao usuario.
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Método retorna objeto <b>email</b> do usuario.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Método atribui objeto <b>email</b> ao usuario
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Método retorna objeto <b>status</b> do usuario.
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Método atribui objeto <b>status</b> ao usuario
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
