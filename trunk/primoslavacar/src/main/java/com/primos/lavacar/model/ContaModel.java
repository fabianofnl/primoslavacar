package com.primos.lavacar.model;

import java.sql.SQLException;
import java.util.List;

import com.primos.lavacar.view.ContaViewBean;

public interface ContaModel {

	public List<ContaViewBean> listarContas() throws ClassNotFoundException,
			SQLException;

	public void cadastrarConta(ContaViewBean contaNova)
			throws ClassNotFoundException, SQLException;

	public void editarConta(ContaViewBean contaSelecionada)
			throws ClassNotFoundException, SQLException;

	public void inativarConta(ContaViewBean contaSelecionada)
			throws ClassNotFoundException, SQLException;

}
