package com.primos.lavacar.view;

import java.io.Serializable;

/**
 * Classe POJO/BEAN/DTO que representa o Funcionario do sistema
 * 
 * @author Claudemir
 * 
 */
public class FuncionarioViewBean extends UsuarioViewBean implements
		Serializable {

	private static final long serialVersionUID = 7055122086563417770L;

	private Long cpf;
	private Long cpfAntigo;
	private String nome;
	private String email;
	private String status;

	public FuncionarioViewBean() {
	}

	/**
	 * M�todo retorna <b>cpf</b> do usuario.
	 * 
	 * @return cpf
	 */
	public Long getCpf() {
		return cpf;
	}

	/**
	 * M�todo atribui <b>cpf</b> ao usuario.
	 * 
	 * @param cpf
	 */
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	/**
	 * M�todo retorna <b>cpfAntigo</b> do usuario.
	 * 
	 * @return cpfAntigo
	 */
	public Long getCpfAntigo() {
		return cpfAntigo;
	}

	/**
	 * M�todo atribui <b>cpfAntigo</b> ao usuario.
	 * 
	 * @param cpfAntigo
	 */
	public void setCpfAntigo(Long cpfAntigo) {
		this.cpfAntigo = cpfAntigo;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result
				+ ((cpfAntigo == null) ? 0 : cpfAntigo.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuncionarioViewBean other = (FuncionarioViewBean) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (cpfAntigo == null) {
			if (other.cpfAntigo != null)
				return false;
		} else if (!cpfAntigo.equals(other.cpfAntigo))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}
