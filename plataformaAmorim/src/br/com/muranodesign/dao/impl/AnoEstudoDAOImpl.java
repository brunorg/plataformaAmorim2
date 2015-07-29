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

import br.com.muranodesign.dao.AnoEstudoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.AnoEstudo;


/**
 * Abstração do dao e implementação do GRUD
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
public class AnoEstudoDAOImpl extends AbstractHibernateDAO implements AnoEstudoDAO {

	/**
	 * Instantiates a new ano estudo dao impl.
	 *
	 * @param persistenceContext the persistence context
	 */
	public AnoEstudoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AnoEstudoDAO#listAll()
	 */
	public List<AnoEstudo> listAll() {
		
		Criteria criteria = getSession().createCriteria(AnoEstudo.class);
		criteria.addOrder(Order.asc("ano"));
		List<AnoEstudo> result = criteria.list();
		
		
		return result;
	} 
	

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AnoEstudoDAO#criar(br.com.muranodesign.model.AnoEstudo)
	 */
	public void criar(AnoEstudo c) {
		synchronized (AnoEstudoDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AnoEstudoDAO#deletar(br.com.muranodesign.model.AnoEstudo)
	 */
	public void deletar(AnoEstudo c) {
		getSession().delete(c);
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AnoEstudoDAO#atualizar(br.com.muranodesign.model.AnoEstudo)
	 */
	public void atualizar(AnoEstudo p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AnoEstudoDAO#listarKey(int)
	 */
	public List<AnoEstudo> listarKey(int key){
		Criteria criteria = getSession().createCriteria(AnoEstudo.class);
		criteria.add(Restrictions.eq("idanoEstudo", key));
		List<AnoEstudo> result = criteria.list();
		return result;
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.AnoEstudoDAO#idAno(String)
	 */
	public Integer idAno(String a){
		Criteria criteria = getSession().createCriteria(AnoEstudo.class);
		criteria.add(Restrictions.eq("ano", a));
		Integer id;
		List<AnoEstudo> result = criteria.list();
		id = result.get(0).getIdanoEstudo();
		return id;
	}


}