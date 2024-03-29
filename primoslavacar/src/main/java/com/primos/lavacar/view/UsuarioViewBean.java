package com.primos.lavacar.view;

import java.io.Serializable;

/**
 * Classe POJO/BEAN/DTO que representa o Usu�rio para realiza��o da autentica��o
 * do sistema.
 * 
 * @author Claudemir
 * 
 */
public class UsuarioViewBean extends PerfilViewBean implements Serializable {

	private static final long serialVersionUID = 8932921618960781400L;

	private String usuario;
	private String senha;

	public UsuarioViewBean() {
	}

	/**
	 * M�todo retorna <b>nomeUsuario</b> do usuario.
	 * 
	 * @return usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * M�todo atribui <b>usuario</b> ao usuario.
	 * 
	 * @param nomeUsuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * M�todo retorna <b>senha</b> do usuario.
	 * 
	 * @return senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * M�todo atribui <b>senha</b> ao usuario.
	 * 
	 * @param senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		UsuarioViewBean other = (UsuarioViewBean) obj;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
}
