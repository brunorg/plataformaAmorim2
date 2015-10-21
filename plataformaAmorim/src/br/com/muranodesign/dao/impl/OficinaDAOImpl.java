package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.OficinaDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.Oficina;

public class OficinaDAOImpl extends AbstractHibernateDAO implements OficinaDAO{
	
	public OficinaDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaDAO#listAll()
	 */
	public List<Oficina> listAll() {
		
		Criteria criteria = getSession().createCriteria(Oficina.class);
		List<Oficina> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaDAO#criar(br.com.muranodesign.model.Oficina)
	 */
	public void criar(Oficina c) {
		synchronized (OficinaDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();

		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaDAO#deletar(br.com.muranodesign.model.Oficina)
	 */
	public void deletar(Oficina c) {
		getSession().delete(c);
		getSession().flush();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaDAO#atualizar(br.com.muranodesign.model.Oficina)
	 */
	public void atualizar(Oficina p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaDAO#listarKey(int)
	 */
	public List<Oficina> listarKey(int key){
		Criteria criteria = getSession().createCriteria(Oficina.class);
		criteria.add(Restrictions.eq("Idoficina", key));
		List<Oficina> result = criteria.list();
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaDAO#listarIdCiclo(int, int)
	 */
	public List<Oficina> listarIdCiclo(int id, int ciclo){
		Criteria criteria = getSession().createCriteria(Oficina.class);
		criteria.add(Restrictions.eq("Idoficina", id));
		criteria.createAlias("ciclo", "ciclo");
		criteria.add(Restrictions.eq("ciclo.Idciclos", ciclo));
		List<Oficina> result = criteria.list();
		return result;
		
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.OficinaDAO#listarIdOficina(int)
	 */
	public List<Oficina> listarIdOficina(int id){
		Criteria criteria = getSession().createCriteria(Oficina.class);
		criteria.add(Restrictions.eq("Idoficina", id));
		List<Oficina> result = criteria.list();
		return result;
	}

	public long listarNomeOficina(String nome){
		Criteria criteria = getSession().createCriteria(Oficina.class);
		criteria.add(Restrictions.like("nome", nome, MatchMode.START));
		criteria.setProjection(Projections.count("nome"));
		long result = (Long) criteria.list().get(0);
		return result;
	}
	
	public List<Oficina> listarNomeOficinaid(String nome){
		Criteria criteria = getSession().createCriteria(Oficina.class);
		criteria.add(Restrictions.like("nome", nome, MatchMode.START));
		List<Oficina> result = criteria.list();
		return result;
	}
}
