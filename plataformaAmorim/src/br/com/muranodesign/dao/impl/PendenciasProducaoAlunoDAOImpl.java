package br.com.muranodesign.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.PendenciasProducaoAlunoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.PendenciasProducaoAluno;

public class PendenciasProducaoAlunoDAOImpl extends AbstractHibernateDAO implements PendenciasProducaoAlunoDAO {
	
	public PendenciasProducaoAlunoDAOImpl(HibernatePersistenceContext persistenceContext) {
		super(persistenceContext);
	}

	@SuppressWarnings("unchecked")
	public List<PendenciasProducaoAluno> listAll() {
		Criteria criteria = getSession().createCriteria(PendenciasProducaoAluno.class);
		List<PendenciasProducaoAluno> resultado = criteria.list();
		return resultado;
	}

	@SuppressWarnings("unchecked")
	public List<PendenciasProducaoAluno> listarKey(int key) {
		Criteria criteria = getSession().createCriteria(PendenciasProducaoAluno.class);
		criteria.add(Restrictions.eq("IdPendenciasProducaoAluno", key));
		List<PendenciasProducaoAluno> resultado = criteria.list();
		return resultado;
	}

	
	public void criar(PendenciasProducaoAluno p) {
		synchronized (PendenciasProducaoAluno.class){
			getSession().persist(p);
			getSession().flush();
		}	
	}

	
	public void deletar(PendenciasProducaoAluno p) {
		synchronized (PendenciasProducaoAluno.class){
			getSession().delete(p);
			getSession().flush();
		}	
	}

	
	public void atualizar(PendenciasProducaoAluno p) {
		synchronized (PendenciasProducaoAluno.class){
			getSession().merge(p);
			getSession().flush();
		}	
	}

	@SuppressWarnings("unchecked")
	public List<PendenciasProducaoAluno> listarAluno(int id) {
		Criteria criteria = getSession().createCriteria(PendenciasProducaoAluno.class);

		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		criteria.createAlias("anoLetivo", "anoLetivo");
		criteria.add(Restrictions.eq("anoLetivo.ano", ano));
		
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", id));
		List<PendenciasProducaoAluno> resultado = criteria.list();
		return resultado;
	}
	
	public List<PendenciasProducaoAluno> listarAlunoAnoAnterior(int id) {
		Criteria criteria = getSession().createCriteria(PendenciasProducaoAluno.class);

		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		criteria.createAlias("anoLetivo", "anoLetivo");
		criteria.add(Restrictions.ne("anoLetivo.ano", ano));
		
		criteria.createAlias("aluno", "aluno");
		criteria.add(Restrictions.eq("aluno.idAluno", id));
		criteria.add(Restrictions.eq("roteiroCompleto", 0));
		List<PendenciasProducaoAluno> resultado = criteria.list();
		return resultado;
	}

	@SuppressWarnings("unchecked")
	public List<PendenciasProducaoAluno> listarAlunoRoteiro(int idaluno, int idroteiro) {
		Criteria criteria = getSession().createCriteria(PendenciasProducaoAluno.class);
		
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		criteria.createAlias("anoLetivo", "anoLetivo");
		criteria.add(Restrictions.eq("anoLetivo.ano", ano));
		
		criteria.createAlias("aluno", "aluno");
		criteria.createAlias("roteiro", "roteiro");
		criteria.add(Restrictions.eq("aluno.idAluno", idaluno));
		criteria.add(Restrictions.eq("roteiro.idroteiro", idroteiro));
		List<PendenciasProducaoAluno> resultado = criteria.list();
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public List<PendenciasProducaoAluno> listarAlunoRoteiroAno(int idAluno, int idRoteiro, String ano){
		Criteria criteria = getSession().createCriteria(PendenciasProducaoAluno.class);
		
		criteria.createAlias("anoLetivo", "anoLetivo");
		criteria.add(Restrictions.eq("anoLetivo.ano", ano));
		
		criteria.createAlias("aluno", "aluno");
		criteria.createAlias("roteiro", "roteiro");
		criteria.add(Restrictions.eq("aluno.idAluno", idAluno));
		criteria.add(Restrictions.eq("roteiro.idroteiro", idRoteiro));
		List<PendenciasProducaoAluno> resultado = criteria.list();
		return resultado;
	}
}
