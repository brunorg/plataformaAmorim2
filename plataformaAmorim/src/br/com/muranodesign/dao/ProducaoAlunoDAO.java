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

import java.util.List;

import br.com.muranodesign.model.ProducaoAluno;


// TODO: Auto-generated Javadoc
/**
 * The Interface ProducaoAlunoDAO.
 */
public interface ProducaoAlunoDAO {

		/**
		 * List all.
		 *
		 * @return the list
		 */
		public List<ProducaoAluno> listAll();
		
		/**
		 * Criar.
		 *
		 * @param p the p
		 */
		public void criar(ProducaoAluno p);
		
		/**
		 * Deletar.
		 *
		 * @param p the p
		 */
		public void deletar(ProducaoAluno p);
		
		/**
		 * Atualizar.
		 *
		 * @param p the p
		 */
		public void atualizar(ProducaoAluno p);
		
		/**
		 * Listar key.
		 *
		 * @param key the key
		 * @return the list
		 */
		public List<ProducaoAluno> listarKey(int key);
		
		/**
		 * 
		 * @param id
		 * @param tipo
		 * @param roteiro
		 * @return
		 */
		public List<ProducaoAluno> listarFiltro(int id, int tipo, int roteiro);
		
		/**
		 * Lista por id aluno
		 * @param id
		 * @return
		 */
		public List<ProducaoAluno> listarAluno(int id);
		
		/**
		 * Listar produ��o aluno por portifolio
		 * @param idTipo
		 * @return
		 */
		public List<ProducaoAluno> listarPortifolio(int id);
	
		/**
		 * Listar por aluno, roteiro e tipo
		 * @param idAluno
		 * @param idRoteiro
		 * @param idTipo
		 * @return
		 */
		public List<ProducaoAluno> listaAlunoRoteiroTipo(int idAluno, int idRoteiro, int idTipo);

		/**
		 * Lista capas n�o nulas
		 * @param id
		 * @return
		 */
		public List<ProducaoAluno> NCapa(int id);

		public List<ProducaoAluno> listaOficinaAluno(int idOficina, int idAluno);
}
