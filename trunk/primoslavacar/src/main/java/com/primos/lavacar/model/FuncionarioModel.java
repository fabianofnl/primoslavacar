package com.primos.lavacar.model;

import java.sql.SQLException;
import java.util.List;

import com.primos.lavacar.view.FuncionarioViewBean;
import com.primos.lavacar.view.PerfilViewBean;

/**
 * Interface que possui os métodos de gerenciamento dos funcionários
 * 
 * @author Roseli
 * 
 */
public interface FuncionarioModel {

	/**
	 * Método que consulta a lista de todos os funcionário do sistema
	 * 
	 * @return List<FuncionarioDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<FuncionarioViewBean> listarFuncionarios() throws SQLException,
			ClassNotFoundException;

	/**
	 * Método que consulta a lista de perfis do sistema
	 * 
	 * @return List<PerfilDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<PerfilViewBean> listarPerfis() throws SQLException,
			ClassNotFoundException;

	/**
	 * Método que cadastra um funcionário no sistema
	 * 
	 * @param funcionario
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void gravar(FuncionarioViewBean funcionario) throws SQLException,
			ClassNotFoundException;

	/**
	 * Método que consulta dados do funcionario por matrícula
	 * 
	 * @param matricula
	 * @return FuncionarioDTO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public FuncionarioViewBean buscarFuncionarioPorMatricula(Integer matricula)
			throws SQLException, ClassNotFoundException;

	/**
	 * Método que inativa um funcionário
	 * 
	 * @param matricula
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void inativar(Long  matricula) throws SQLException,
			ClassNotFoundException;

	/**
	 * Método que altera os dados de um funcionário
	 * 
	 * @param funcionario
	 * @param matriculaAntiga
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void alterar(FuncionarioViewBean funcionario, Long matriculaAntiga)
			throws SQLException, ClassNotFoundException;

	/**
	 * Método que consulta a lista de gerente (apenas o perfil de gerentes)
	 * 
	 * @return List<FuncionadioDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<FuncionarioViewBean> listarGerentes() throws SQLException,
			ClassNotFoundException;

	/**
	 * Método que consulta a lista de colaboradores sem equipes associadas
	 * 
	 * @return List<FuncionarioDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<FuncionarioViewBean> listarColaboradoresSemEquipes()
			throws SQLException, ClassNotFoundException;

	/**
	 * Método que associa os colaboradores ao gerente, formando assim a equipe
	 * 
	 * @param matGerente
	 * @param matColaborador
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void associarEquipes(Integer matGerente, Integer[] matColaborador)
			throws SQLException, ClassNotFoundException;

	/**
	 * Método que consulta a lista d colaboradores peela matricula do gerente
	 * (equipe)
	 * 
	 * @param matricula
	 * @return List<FuncionarioDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<FuncionarioViewBean> listarColaboradorPorGerente(Integer matricula)
			throws SQLException, ClassNotFoundException;

	/**
	 * Método que remover o colaborador da equipe
	 * 
	 * @param matricula
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void removerColaborador(Long matricula) throws SQLException,
			ClassNotFoundException;

	/**
	 * Método que consulta a lista de colaboradores cadastrados no sistema
	 * (perfil colaborador)
	 * 
	 * @return List<FuncionarioDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<FuncionarioViewBean> listarColaboradores() throws SQLException,
			ClassNotFoundException;
}
