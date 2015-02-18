package com.primos.lavacar.view;

import java.io.Serializable;

/**
 * Classe POJO/BEAN/DTO que representa o Cliente do sistema
 * 
 * @author Claudemir
 * 
 */
public class ClienteViewBean implements Serializable {

	private static final long serialVersionUID = 1448451946953149867L;

	private Long cpf;
	private Long cpfAntigo;
	private String nome;
	private Long telefone;
	private String enderecoNumero;
	private String email;
	private String status;

	public ClienteViewBean() {
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Long getCpfAntigo() {
		return cpfAntigo;
	}

	public void setCpfAntigo(Long cpfAntigo) {
		this.cpfAntigo = cpfAntigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public String getEnderecoNumero() {
		return enderecoNumero;
	}

	public void setEnderecoNumero(String enderecoNumero) {
		this.enderecoNumero = enderecoNumero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result
				+ ((cpfAntigo == null) ? 0 : cpfAntigo.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((enderecoNumero == null) ? 0 : enderecoNumero.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteViewBean other = (ClienteViewBean) obj;
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
		if (enderecoNumero == null) {
			if (other.enderecoNumero != null)
				return false;
		} else if (!enderecoNumero.equals(other.enderecoNumero))
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
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
}
