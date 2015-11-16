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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.muranodesign.dao.ForumQuestaoDAO;
import br.com.muranodesign.hibernate.AbstractHibernateDAO;
import br.com.muranodesign.hibernate.HibernatePersistenceContext;
import br.com.muranodesign.model.ForumQuestao;
import br.com.muranodesign.util.StringUtil;

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
	@SuppressWarnings("unchecked")
	public List<ForumQuestao> listAll() {
		
		Criteria criteria = getSession().createCriteria(ForumQuestao.class);
		List<ForumQuestao> result = criteria.list();
		
		
		return result;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumQuestaoDAO#listAllOrder()
	 */
	@SuppressWarnings("unchecked")
	public List<ForumQuestao> listAllOrder(){
		Criteria criteria = getSession().createCriteria(ForumQuestao.class);
		criteria.addOrder(Order.desc("data"));
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
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings("unchecked")
	public List<ForumQuestao> topN(int qtd){
		Criteria criteria = getSession().createCriteria(ForumQuestao.class);
		criteria.addOrder( Order.desc( "idforumQuestao" ) ); 
		criteria.setMaxResults(qtd);
		List<ForumQuestao> result = criteria.list();
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ForumQuestao> Range(String data2, String data){
		
		StringUtil stringUtil = new StringUtil();
		/*
		Date dataHoje = new Date();
		SimpleDateFormat formataData = new SimpleDateFormat("yy-MM-dd");
		String data = formataData.format(dataHoje);
		StringUtil stringUtil = new StringUtil();
		
		Calendar c = Calendar.getInstance();
		int dia = c.get(Calendar.DAY_OF_MONTH);
		String data2;
		if(dia > 7){
			dia = dia - 7;
			String quebra[] = data.split("-");
			
			if(dia > 10){
				//data2 = Integer.toString(dia)+"-"+quebra[1]+"-"+quebra[2];
				data2 = quebra[0]+"-"+quebra[1]+"-"+Integer.toString(dia);
			}else{
				data2 = quebra[0]+"-"+quebra[1]+"-"+"0"+Integer.toString(dia);
			}
			
		}else{
			int aux = 30;
			dia = dia - 7;
			aux = aux + dia;
			
			String quebra[] = data.split("/");
			int mes = Integer.parseInt(quebra[1]);
			mes = mes + 1;
			
			//data2 = Integer.toString(aux)+"/"+Integer.toString(mes)+"/"+quebra[2];
			data2 = quebra[0]+"-"+quebra[1]+"-"+Integer.toString(aux);
			
		}
		
		System.out.print("inicio "+stringUtil.converteStringData(data2));
		System.out.print("fim "+stringUtil.converteStringData(data));*/
		
		
		Criteria criteria = getSession().createCriteria(ForumQuestao.class);
		criteria.add(Restrictions.ge("data", stringUtil.converteStringData(data2)));
		criteria.add(Restrictions.lt("data", stringUtil.converteStringData(data)));
		List<ForumQuestao> result = criteria.list();
		
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumQuestaoDAO#listaRoteiro(int)
	 */
	@SuppressWarnings("unchecked")
	public List<ForumQuestao> listaRoteiro(int roteiro){
		Criteria criteria = getSession().createCriteria(ForumQuestao.class);
		criteria.createAlias("roteiro", "roteiro");
		criteria.add(Restrictions.eq("roteiro.idroteiro", roteiro));
		List<ForumQuestao> result = criteria.list();
		
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumQuestaoDAO#listaUser(int)
	 */
	@SuppressWarnings("unchecked")
	public List<ForumQuestao> listaUser(int idUser){
		Criteria criteria = getSession().createCriteria(ForumQuestao.class);
		criteria.createAlias("usuario", "usuario");
		criteria.add(Restrictions.eq("usuario.idusuario", idUser));
		List<ForumQuestao> result = criteria.list();
		
		return result;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.muranodesign.dao.ForumQuestaoDAO#ListaLikeRoteiro(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ForumQuestao> ListaLikeRoteiro(String letra){
		Criteria criteria = getSession().createCriteria(ForumQuestao.class);
		criteria.createAlias("roteiro", "roteiro");
		criteria.add(Restrictions.like("roteiro.nome", letra, MatchMode.START));
		criteria.addOrder(Order.desc("data"));
		List<ForumQuestao> result = criteria.list();
		
		return result;
	}


}