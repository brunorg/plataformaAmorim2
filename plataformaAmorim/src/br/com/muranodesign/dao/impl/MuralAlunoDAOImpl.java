package br.com.muranodesign.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.MuralAlunoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.MuralAluno;

public class MuralAlunoDAOImpl extends AbstractHibernateDAO implements MuralAlunoDAO {

	
	public MuralAlunoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
	}
	
	@SuppressWarnings("unchecked")
	public List<MuralAluno> listarAll() {
		Criteria criteria = getSession().createCriteria(MuralAluno.class);
		List<MuralAluno> resultado = criteria.list();
		return resultado;
	}

	@SuppressWarnings("unchecked")
	public List<MuralAluno> listarKey(int id) {
		Criteria criteria = getSession().createCriteria(MuralAluno.class);
		criteria.add(Restrictions.eq("IdMuralAluno", id));
		List<MuralAluno> resultado = criteria.list();
		return resultado;
	}

	@SuppressWarnings("unchecked")
	public void deletar(MuralAluno muralAluno) {
		synchronized (MuralAluno.class){
			getSession().delete(muralAluno);
			getSession().flush();
		}
	}

	@SuppressWarnings("unchecked")
	public void criar(MuralAluno muralAluno) {
		synchronized (MuralAluno.class){
			getSession().persist(muralAluno);
			getSession().flush();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MuralAluno> listarAluno(int idaluno) {
		Criteria criteria = getSession().createCriteria(MuralAluno.class);
		criteria.createAlias("alunoVariavel", "alunoVariavel");
		criteria.add(Restrictions.eq("alunoVariavel.idalunoVariavel", idaluno));
		List<MuralAluno> resultado = criteria.list();
		return resultado;
	}

}
