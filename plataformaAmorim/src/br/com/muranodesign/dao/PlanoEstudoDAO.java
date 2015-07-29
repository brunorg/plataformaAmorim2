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

import br.com.muranodesign.model.PlanoEstudo;


// TODO: Auto-generated Javadoc
/**
 * The Interface PlanoEstudoDAO.
 */
public interface PlanoEstudoDAO {

		/**
		 * List all.
		 *
		 * @return the list
		 */
		public List<PlanoEstudo> listAll();
		
		/**
		 * Criar.
		 *
		 * @param p the p
		 */
		public void criar(PlanoEstudo p);
		
		/**
		 * Deletar.
		 *
		 * @param p the p
		 */
		public void deletar(PlanoEstudo p);
		
		/**
		 * Atualizar.
		 *
		 * @param p the p
		 */
		public void atualizar(PlanoEstudo p);
		
		/**
		 * Listar key.
		 *
		 * @param key the key
		 * @return the list
		 */
		public List<PlanoEstudo> listarKey(int key);
		
		/**
		 * Listar o ultimo plano de estudo do aluno
		 * @param idAluno
		 * @return
		 */
		public List<PlanoEstudo> utimoPlanoEstudos(int idAluno);
		
		/**
		 * Listar todos os planos de estudo do aluno
		 * @param idAluno
		 * @return
		 */
		public List<PlanoEstudo> TodosPlanoEstudos(int idAluno);

}
