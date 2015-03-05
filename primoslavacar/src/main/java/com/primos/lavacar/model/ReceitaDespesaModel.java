/**
 * 
 */
package com.primos.lavacar.model;

import java.sql.SQLException;
import java.util.List;

import com.primos.lavacar.view.ReceitaDespesaViewBean;

/**
 * @author Claudemir
 * 
 */
public interface ReceitaDespesaModel {

	public List<ReceitaDespesaViewBean> listarReceitas()
			throws ClassNotFoundException, SQLException;

	public List<ReceitaDespesaViewBean> listarDespesas()
			throws ClassNotFoundException, SQLException;

	public void gravar(ReceitaDespesaViewBean despesaNova)
			throws ClassNotFoundException, SQLException;

	public void alterar(ReceitaDespesaViewBean receitaDespesaSelecionado)
			throws ClassNotFoundException, SQLException;

	public void excluir(ReceitaDespesaViewBean receitaDespesaSelecionado)
			throws ClassNotFoundException, SQLException;

}
