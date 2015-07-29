/**
 *   Este codigo é software livre você e pode resdistribuir e/ou modificar ele seguindo os termos da
 *   Creative Commons Attribution 4.0 International Pare visualizar uma copia desta 
 *   licensa em ingles visite http://creativecommons.org/licenses/by/4.0/.
 *   
 *   This code is free software; you can redistribute it and/or modify it
 *   under the terms of Creative Commons Attribution 4.0 International License. 
 *   To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */

package br.com.muranodesign.dao.impl;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.CategoriaProducaoAlunoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.CategoriaProducaoAluno;


/**
 * Abstração do dao e implementação do GRUD
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
public class CategoriaProducaoAlunoDAOImpl extends AbstractHibernateDAO implements CategoriaProducaoAlunoDAO {

	/**
	 * Instantiates a new categoria producao aluno dao impl.
	 *
	 * @param persistenceContext the persistence context
	 */
	public CategoriaProducaoAlunoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.CategoriaProducaoAlunoDAO#listAll()
	 */
	public List<CategoriaProducaoAluno> listAll() {
		
		Criteria criteria = getSession().createCriteria(CategoriaProducaoAluno.class);
		List<CategoriaProducaoAluno> result = criteria.list();
		
		
		return result;
	} 
	

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.CategoriaProducaoAlunoDAO#criar(br.com.muranodesign.model.CategoriaProducaoAluno)
	 */
	public void criar(CategoriaProducaoAluno c) {
		synchronized (CategoriaProducaoAlunoDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.CategoriaProducaoAlunoDAO#deletar(br.com.muranodesign.model.CategoriaProducaoAluno)
	 */
	public void deletar(CategoriaProducaoAluno c) {
		getSession().delete(c);
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.CategoriaProducaoAlunoDAO#atualizar(br.com.muranodesign.model.CategoriaProducaoAluno)
	 */
	public void atualizar(CategoriaProducaoAluno p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.CategoriaProducaoAlunoDAO#listarKey(int)
	 */
	public List<CategoriaProducaoAluno> listarKey(int key){
		Criteria criteria = getSession().createCriteria(CategoriaProducaoAluno.class);
		criteria.add(Restrictions.eq("idcategoriaProducaoAluno", key));
		List<CategoriaProducaoAluno> result = criteria.list();
		return result;
	}


	





	

}