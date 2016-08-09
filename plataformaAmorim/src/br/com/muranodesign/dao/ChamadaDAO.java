/**
 *   Este codigo é software livre você e pode resdistribuir e/ou modificar ele seguindo os termos da
 *   Creative Commons Attribution 4.0 International Pare visualizar uma copia desta 
 *   licensa em ingles visite http://creativecommons.org/licenses/by/4.0/.
 *   
 *   This code is free software; you can redistribute it and/or modify it
 *   under the terms of Creative Commons Attribution 4.0 International License. 
 *   To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */
package br.com.muranodesign.dao;

import br.com.muranodesign.model.Aluno;
import br.com.muranodesign.model.Chamada;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The Interface ChamadaDAO.
 */
public interface ChamadaDAO {

		/**
		 * List all.
		 *
		 * @return the list
		 */
		public List<Chamada> listAll();
		
		/**
		 * Criar.
		 *
		 * @param p the p
		 */
		public void criar(Chamada p);
		
		/**
		 * Deletar.
		 *
		 * @param p the p
		 */
		public void deletar(Chamada p);
		
		/**
		 * Atualizar.
		 *
		 * @param p the p
		 */
		public void atualizar(Chamada p);
		
		/**
		 * Listar key.
		 *
		 * @param key the key
		 * @return the list
		 */
		public List<Chamada> listarKey(int key);
		
		
		/**
		 * Lista precenca.
		 *
		 * @param aluno the aluno
		 * @param precenca the precenca
		 * @return the list
		 */
		public List<Chamada> listaPrecenca(Aluno aluno , int precenca);
		
	
		/**
		 * Listar num de faltas
		 * @param id
		 * @return
		 */
		public long countFaltas(int id);
	
		/**
		 * Verifica se existe precenca na data indicada
		 * @return
		 */
		public List<Chamada> dataPresenca(int id, Date data);
		
		/**
		 * Verificar data atual
		 * @param id
		 * @param data
		 * @return
		 */
		public List<Chamada> dataPresencaAtual(int id, Date data);

		public List<Chamada> getFaltasSemana(int idAluno, int dia, int mes);

		public List<Chamada> dataPresenca(int id, Calendar cal);

		public List<Chamada> listBetween(int idAluno, Date startDate, Date endDate);

		public long countFaltasAno(int idAluno, int ano);
	

}
