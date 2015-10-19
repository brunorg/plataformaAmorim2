package br.com.muranodesign.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.PlanoAulaDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.PlanoAula;

public class PlanoAulaDAOImpl extends AbstractHibernateDAO implements PlanoAulaDAO{

	public PlanoAulaDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
		
	}
	
	public List<PlanoAula> listAll() {
		
		Criteria criteria = getSession().createCriteria(PlanoAula.class);
		List<PlanoAula> result = criteria.list();
		
		
		return result;
	} 

	public void criar(PlanoAula c) {
		synchronized (PlanoAulaDAOImpl.class) {
			getSession().persist(c);
			getSession().flush();
	
		}
	}
	
	public void deletar(PlanoAula c) {
		getSession().delete(c);
		getSession().flush();
	}
	
	public void atualizar(PlanoAula p) {
		getSession().merge(p);
		getSession().flush();
	}
	
	public List<PlanoAula> listarKey(int key){
		Criteria criteria = getSession().createCriteria(PlanoAula.class);
		criteria.add(Restrictions.eq("Idplano_aula", key));
		List<PlanoAula> result = criteria.list();
		return result;
	}
	
	public List<PlanoAula> range(Date data){
		Criteria criteria = getSession().createCriteria(PlanoAula.class);
		criteria.add(Restrictions.gt("data_fim",data));
		List<PlanoAula> result = criteria.list();
		return result;
	}
	
	public List<PlanoAula> listarUltimo(int idProfessor){
		Criteria criteria = getSession().createCriteria(PlanoAula.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", idProfessor));
		
		criteria.addOrder(Order.desc( "data_ini" ) ); 
		criteria.setMaxResults(1);
		List<PlanoAula> result = criteria.list();
		return result;
	}
	
	public List<PlanoAula> listarProfessor(int idProfessor, Date data){
		Criteria criteria = getSession().createCriteria(PlanoAula.class);
		criteria.createAlias("professor", "professor");
		criteria.add(Restrictions.eq("professor.idprofessorFuncionario", idProfessor));
		criteria.add(Restrictions.gt("data_ini",data));
		List<PlanoAula> result = criteria.list();
		return result;
	}
}
