/**
 *   Este codigo é software livre você e pode resdistribuir e/ou modificar ele seguindo os termos da
 *   Creative Commons Attribution 4.0 International Pare visualizar uma copia desta 
 *   licensa em ingles visite http://creativecommons.org/licenses/by/4.0/.
 *   
 *   This code is free software; you can redistribute it and/or modify it
 *   under the terms of Creative Commons Attribution 4.0 International License. 
 *   To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */
package br.com.muranodesign.business;

import java.util.List;

import br.com.muranodesign.dao.AlunoVariavelDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.AnoEstudo;
import br.com.muranodesign.model.Periodo;



// TODO: Auto-generated Javadoc
/**
 * The Class AlunoVariavelService.
 */
public class AlunoVariavelService {
	
	/**
	 * Listar todos.
	 *
	 * @return the list
	 */
	public List<AlunoVariavel> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		List<AlunoVariavel> result = dao.listAll();
				
		pc.commitAndClose();
		return result;
	}
	
	
	/**
	 * Listar todos.
	 *
	 * @return the list
	 */
	public List<AlunoVariavel> listarTodos(int status) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		List<AlunoVariavel> result = dao.listAll(status);
				
		pc.commitAndClose();
		return result;
	}
	
	
	/**
	 * Listarkey.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<AlunoVariavel> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		List<AlunoVariavel> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar Aluno Variavel
	 * @param idAluno
	 * @return list
	 */
	public List<AlunoVariavel> listaAluno(int idAluno) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		List<AlunoVariavel> result = dao.listaAluno(idAluno);
		pc.commitAndClose();
		return result;
	}
	/**
	 * Listar grupo
	 * @param idGrupo
	 * @return list
	 */
	public List<AlunoVariavel> listaGrupo(int idGrupo) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		List<AlunoVariavel> result = dao.listaGrupo(idGrupo);
		pc.commitAndClose();
		return result;
	}
	/**
	 * Listar Ano Estudo
	 * @param ano
	 * @return list
	 */
	public List<AlunoVariavel> listaAnoEstudo(AnoEstudo ano){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		List<AlunoVariavel> result = dao.listaAnoEstudo(ano);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar por ano de estudo e periodo
	 * @param ano
	 * @param periodo
	 * @return list
	 */
	public List<AlunoVariavel> listaAnoEstudoPeriodo(AnoEstudo ano, Periodo periodo){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		List<AlunoVariavel> result = dao.listaAnoEstudoPeriodo(ano, periodo);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listar por ano e periodo e que tenha grupo
	 * @param ano
	 * @param periodo
	 * @return list
	 */
	public List<AlunoVariavel> listaAnoEstudoPeriodoComgrupo(AnoEstudo ano, Periodo periodo){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		List<AlunoVariavel> result = dao.listaAnoEstudoPeriodoComgrupo(ano, periodo);
		pc.commitAndClose();
		return result;
	}
	
	/**
	  * Listar por periodo
	 * @param periodo
	 * @return
	 */
	public List<AlunoVariavel> listaPeriodo(Periodo periodo){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		List<AlunoVariavel> result = dao.listaPeriodo(periodo);
		pc.commitAndClose();
		return result;
	}
	/**
	 * Criar aluno variavel.
	 *
	 * @param p the p
	 * @return the aluno variavel
	 */
	public AlunoVariavel criarAlunoVariavel(AlunoVariavel p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	
	/**
	 * Deletar aluno variavel.
	 *
	 * @param p the p
	 * @return the aluno variavel
	 */
	public AlunoVariavel deletarAlunoVariavel(AlunoVariavel p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	
	/**
	 * Atualizar aluno variavel.
	 *
	 * @param p the p
	 * @return the aluno variavel
	 */
	public AlunoVariavel atualizarAlunoVariavel(AlunoVariavel p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}

	/**
	 * Lista aluno variavel 
	 * @param aluno
	 * @return list
	 */
	public AlunoVariavel getAluno(int aluno) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		AlunoVariavel result = dao.getAluno(aluno);
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Lista aluno varivel por named query
	 * @return list
	 */
	public List<AlunoVariavel> listarNamed(){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		List<AlunoVariavel> result = dao.listarNamed();
				
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Atualizar grupo
	 * @param id
	 * @param grupo
	 * @return id
	 */
	public  int update(int id, int grupo){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		dao.update(id, grupo);
		pc.commitAndClose();
		return id;
	}
	
	public List<AlunoVariavel> ListarCicloAno(List<Integer> i,int primeiro,int ultimo){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		List<AlunoVariavel> result = dao.ListarCicloAno(i, primeiro, ultimo);
		pc.commitAndClose();
		return result;
	}
	
	public List<AlunoVariavel> ListarCicloAnoPeriodo(List<Integer> i,int periodo,int primeiro,int ultimo){
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		AlunoVariavelDAO dao = DAOFactory.getAlunoVariavelDAO(pc);
		List<AlunoVariavel> result = dao.ListarCicloAnoPeriodo(i, periodo, primeiro, ultimo);
		pc.commitAndClose();
		return result;
		
	}
	
	
	
	//getAluno
	
}
