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

import br.com.muranodesign.dao.CategoriaProducaoAlunoDAO;
import br.com.muranodesign.dao.DAOFactory;
import br.com.muranodesign.hibernate.impl.PersistenceContext;
import br.com.muranodesign.model.CategoriaProducaoAluno;



// TODO: Auto-generated Javadoc
/**
 * The Class CategoriaProducaoAlunoService.
 */
public class CategoriaProducaoAlunoService {
	
	/**
	 * Listar todos.
	 *
	 * @return the list
	 */
	public List<CategoriaProducaoAluno> listarTodos() {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CategoriaProducaoAlunoDAO dao = DAOFactory.getCategoriaProducaoAlunoDAO(pc);
		List<CategoriaProducaoAluno> result = dao.listAll();
				
		pc.commitAndClose();
		return result;
	}
	
	/**
	 * Listarkey.
	 *
	 * @param key the key
	 * @return the list
	 */
	public List<CategoriaProducaoAluno> listarkey(int key) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CategoriaProducaoAlunoDAO dao = DAOFactory.getCategoriaProducaoAlunoDAO(pc);
		List<CategoriaProducaoAluno> result = dao.listarKey(key);
		pc.commitAndClose();
		return result;
	}
	
	
	/**
	 * Criar categoria producao aluno.
	 *
	 * @param p the p
	 * @return the categoria producao aluno
	 */
	public CategoriaProducaoAluno criarCategoriaProducaoAluno(CategoriaProducaoAluno p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CategoriaProducaoAlunoDAO dao = DAOFactory.getCategoriaProducaoAlunoDAO(pc);
		dao.criar(p);
		pc.commitAndClose();
		return p;
	}
	
	
	/**
	 * Deletar categoria producao aluno.
	 *
	 * @param p the p
	 * @return the categoria producao aluno
	 */
	public CategoriaProducaoAluno deletarCategoriaProducaoAluno(CategoriaProducaoAluno p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CategoriaProducaoAlunoDAO dao = DAOFactory.getCategoriaProducaoAlunoDAO(pc);
		dao.deletar(p);
		pc.commitAndClose();
		return p;
	}
	
	
	/**
	 * Atualizar categoria producao aluno.
	 *
	 * @param p the p
	 * @return the categoria producao aluno
	 */
	public CategoriaProducaoAluno atualizarCategoriaProducaoAluno(CategoriaProducaoAluno p) {
		PersistenceContext pc = DAOFactory.createPersistenceContext();
		CategoriaProducaoAlunoDAO dao = DAOFactory.getCategoriaProducaoAlunoDAO(pc);
		dao.atualizar(p);
		pc.commitAndClose();
		return p;
	}

	
}
