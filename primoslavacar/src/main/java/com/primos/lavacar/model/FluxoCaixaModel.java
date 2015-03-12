/**
 * 
 */
package com.primos.lavacar.model;

import java.sql.SQLException;
import java.util.List;

import com.primos.lavacar.view.FluxoCaixaViewBean;

/**
 * @author Claudemir
 * 
 */
public interface FluxoCaixaModel {

	public List<FluxoCaixaViewBean> listarFluxoCaixa(String tipo,
			Integer anoBase) throws ClassNotFoundException, SQLException;

	public List<Integer> listarAnoBaso() throws ClassNotFoundException,
			SQLException;

}
