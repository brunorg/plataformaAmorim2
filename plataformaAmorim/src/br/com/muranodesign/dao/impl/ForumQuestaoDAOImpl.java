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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.ForumQuestaoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.ForumQuestao;

/**
 * Abstração do dao e implementação do GRUD
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
public class ForumQuestaoDAOImpl extends AbstractHibernateDAO implements ForumQuestaoDAO {

	/**
	 * Instantiates a new forum questao dao impl.
	 *
	 * @param persistenceContext the persistence context
	 */
	public ForumQuestaoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumQuestaoDAO#listAll()
	 */
	public List<ForumQuestao> listAll() {
		
		Criteria criteria = getSession().createCriteria(ForumQuestao.class);
		List<ForumQuestao> result = criteria.list();
		
		
		return result;
	} 
	

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumQuestaoDAO#criar(br.com.muranodesign.model.ForumQuestao)
	 */
	public void criar(ForumQuestao c) {
		synchronized (ForumQuestaoDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumQuestaoDAO#deletar(br.com.muranodesign.model.ForumQuestao)
	 */
	public void deletar(ForumQuestao c) {
		getSession().delete(c);
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumQuestaoDAO#atualizar(br.com.muranodesign.model.ForumQuestao)
	 */
	public void atualizar(ForumQuestao p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumQuestaoDAO#listarKey(int)
	 */
	public List<ForumQuestao> listarKey(int key){
		Criteria criteria = getSession().createCriteria(ForumQuestao.class);
		criteria.add(Restrictions.eq("idforumQuestao", key));
		List<ForumQuestao> result = criteria.list();
		return result;
	}
	
/*
 * (non-Javadoc)
 * @see br.com.muranodesign.dao.ForumQuestaoDAO#topN(int)
 */
	public List<ForumQuestao> topN(int qtd){
		Criteria criteria = getSession().createCriteria(ForumQuestao.class);
		criteria.addOrder( Order.desc( "idforumQuestao" ) ); 
		criteria.setMaxResults(qtd);
		List<ForumQuestao> result = criteria.list();
		
		return result;
	}

	

}