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
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.CalendarioDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Calendario;
import br.com.muranodesign.util.StringUtil;



/**
 * Abstração do dao e implementação do GRUD
 *
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
public class CalendarioDAOImpl extends AbstractHibernateDAO implements CalendarioDAO {

	/**
	 * Instantiates a new calendario dao impl.
	 *
	 * @param persistenceContext the persistence context
	 */
	public CalendarioDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.CalendarioDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Calendario> listAll() {
		
		Criteria criteria = getSession().createCriteria(Calendario.class);
		criteria.addOrder(Order.asc("dataInicio"));
		List<Calendario> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.CalendarioDAO#listVisivel()
	 */
	@SuppressWarnings("unchecked")
	public List<Calendario> listVisivel(){
		Criteria criteria = getSession().createCriteria(Calendario.class);
		criteria.add(Restrictions.ge("visivel",1));
		criteria.addOrder(Order.asc("dataInicio"));
		List<Calendario> result = criteria.list();
		
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.CalendarioDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Calendario> listFiltroData(Date dataInicio, Date dataFim) {
		
		Criteria criteria = getSession().createCriteria(Calendario.class);
		criteria.add(Restrictions.ge("dataInicio",dataInicio));
		criteria.add(Restrictions.le("dataFim",dataFim));
		criteria.addOrder(Order.asc("dataInicio"));
		
		List<Calendario> result = criteria.list();
		
		
		return result;
	} 
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.CalendarioDAO#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Calendario> listFeriados(int ano) {
		
		Criteria criteria = getSession().createCriteria(Calendario.class);

		
		  criteria.add(Restrictions.or(Restrictions.ge("feriado",1),Restrictions.eq("aula",0)));
		  criteria.add(Restrictions.eq("ano", ano));
		  criteria.addOrder(Order.asc("dataInicio"));
	              
		
		List<Calendario> result = criteria.list();
		
		
		return result;
	} 
	
	

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.CalendarioDAO#criar(br.com.muranodesign.model.Calendario)
	 */
	public void criar(Calendario c) {
		synchronized (CalendarioDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.CalendarioDAO#deletar(br.com.muranodesign.model.Calendario)
	 */
	public void deletar(Calendario c) {
		getSession().delete(c);
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.CalendarioDAO#atualizar(br.com.muranodesign.model.Calendario)
	 */
	public void atualizar(Calendario p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	
	/* (non-Javadoc)
	 * @see br.com.muranodesign.dao.CalendarioDAO#listarKey(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Calendario> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Calendario.class);
		criteria.add(Restrictions.eq("idcalendario", key));
		criteria.addOrder(Order.asc("dataInicio"));
		List<Calendario> result = criteria.list();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.CalendarioDAO#listarEvento(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Calendario> listarEvento(int id) throws ParseException{
		
		
		Date data = Calendar.getInstance().getTime();

		Criteria criteria = getSession().createCriteria(Calendario.class);
		criteria.createAlias("tipoEvento", "tipoEvento");
		criteria.add(Restrictions.eq("tipoEvento.idtipoEvento", id));
		criteria.add(Restrictions.gt("dataInicio", data));
		criteria.addOrder(Order.asc("dataInicio"));
		List<Calendario> result = criteria.list();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.CalendarioDAO#listarRange()
	 */
	@SuppressWarnings("unchecked")
	public List<Calendario> listarRange(){
		Criteria criteria = getSession().createCriteria(Calendario.class);
		
		StringUtil stringUtil = new StringUtil();
		
		Calendar cal = Calendar.getInstance();
		
		int anomenos = cal.get(Calendar.YEAR) - 1;
		int anomais = cal.get(Calendar.YEAR) + 1;
		
		String ini = Integer.toString(anomenos - 2000) + "-01-01";
		String fim = Integer.toString(anomais - 2000) + "-01-01";
		
		criteria.add(Restrictions.ge("dataFim", stringUtil.converteStringData(ini)));
		criteria.add(Restrictions.le("dataFim", stringUtil.converteStringData(fim)));
		List<Calendario> result = criteria.list();
		return result;
	}

}