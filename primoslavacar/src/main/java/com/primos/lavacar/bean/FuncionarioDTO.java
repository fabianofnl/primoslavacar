package com.primos.lavacar.bean;

import java.io.Serializable;

/**
 * Classe POJO/BEAN/DTO que representa o Funcionario do sistema
 * 
 * @author Claudemir
 * 
 */
public class FuncionarioDTO extends UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 3576824392171045517L;

	private Integer cpf;
	private String nome;
	private String email;
	private String status;

	public FuncionarioDTO() {
	}

	/**
	 * M�todo retorna <b>cpf</b> do usuario.
	 * 
	 * @return cpf
	 */
	public Integer getCpf() {
		return cpf;
	}

	/**
	 * M�todo atribui <b>cpf</b> ao usuario.
	 * 
	 * @param cpf
	 */
	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	/**
	 * M�todo retorna <b>nome</b> do usuario.
	 * 
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * M�todo atribui <b>nome</b> ao usuario.
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * M�todo retorna objeto <b>email</b> do usuario.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * M�todo atribui objeto <b>email</b> ao usuario
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * M�todo retorna objeto <b>status</b> do usuario.
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * M�todo atribui objeto <b>status</b> ao usuario
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
