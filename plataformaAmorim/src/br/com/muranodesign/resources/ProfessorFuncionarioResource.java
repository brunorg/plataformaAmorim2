/**
 *   Este codigo é software livre você e pode resdistribuir e/ou modificar ele seguindo os termos da
 *   Creative Commons Attribution 4.0 International Pare visualizar uma copia desta 
 *   licensa em ingles visite http://creativecommons.org/licenses/by/4.0/.
 *   
 *   This code is free software; you can redistribute it and/or modify it
 *   under the terms of Creative Commons Attribution 4.0 International License. 
 *   To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */
package br.com.muranodesign.resources;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AlunoService;
import br.com.muranodesign.business.AlunoVariavelService;
import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.business.AtribuicaoRoteiroExtraService;
import br.com.muranodesign.business.GrupoService;
import br.com.muranodesign.business.ObjetivoService;
import br.com.muranodesign.business.PlanejamentoRoteiroService;
import br.com.muranodesign.business.PresencaProfessorService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.business.ProfessorFuncionarioVariavelService;
import br.com.muranodesign.business.RoteiroService;
import br.com.muranodesign.business.TutoriaService;
import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.AtribuicaoRoteiroExtra;
import br.com.muranodesign.model.Grupo;
import br.com.muranodesign.model.PlanejamentoRoteiro;
import br.com.muranodesign.model.ProfessorFuncionario;
import br.com.muranodesign.model.ProfessorFuncionarioVariavel;
import br.com.muranodesign.model.Roteiro;
import br.com.muranodesign.model.Tutoria;
import br.com.muranodesign.util.StringUtil;
import br.com.muranodesign.util.Upload;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * Classe tem como objetivo disponibilizar os serviços relacionandos professor
 * funcionario
 * 
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */

@Path("ProfessorFuncionario")
public class ProfessorFuncionarioResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(ProfessorFuncionarioResource.class
			.getName());

	/**
	 * Gets the professor funcionario.
	 * 
	 * @return the professor funcionario
	 */
	@Path("html")
	@GET
	@Produces("text/plain")
	public /*List<ProfessorFuncionario>*/ String getProfessorFuncionariohtml() {
		logger.info("Listar ProfessorFuncionario ...");
		List<ProfessorFuncionario> resultado;
		resultado = new ProfessorFuncionarioService().listarTodos();
		logger.info("QTD ProfessorFuncionario : " + resultado.size());
		int tamanho = resultado.size();
		String HtmlContent = "";
		
		for(int i = 0; i < tamanho; i++){
			HtmlContent += "<option value="+resultado.get(i).getIdprofessorFuncionario()+">"+resultado.get(i).getNome()+"</option>";
		}
		return HtmlContent;
	}
	
	/**
	 * Listar todos os professores funcionarios
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<ProfessorFuncionario> getProfessorFuncionario() {
		logger.info("Listar ProfessorFuncionario ...");
		List<ProfessorFuncionario> resultado;
		resultado = new ProfessorFuncionarioService().listarTodos();
		logger.info("QTD ProfessorFuncionario : " + resultado.size());
		return resultado;
	}
	
	/**
	 * Gets the evento.
	 * 
	 * @param id
	 *            the id
	 * @return the evento
	 */
	@Path("{id}")
	@GET
	@Produces("application/json")
	public ProfessorFuncionario getEvento(@PathParam("id") int id) {
		logger.info("Lista ProfessorFuncionario  por id " + id);
		List<ProfessorFuncionario> resultado;
		resultado = new ProfessorFuncionarioService().listarkey(id);
		ProfessorFuncionario evento = resultado.get(0);

		return evento;

	}
	
	/**
	 * Listar Professor por id grupo
	 * @param id
	 * @return list
	 */
	@Path("ProfessorGrupo/{id}")
	@GET
	@Produces("application/json")
	public List<Grupo> getProfessorGrupo(@PathParam("id") int id) {
		List<Tutoria> tutor = new TutoriaService().listarProfessorId(id);
		List<Grupo> totalGrupo = new ArrayList<Grupo>();
		for(int i = 0; i < tutor.size(); i++){
			List<Grupo> grupo = new GrupoService().listarTutor(tutor.get(i).getIdtutoria());
			totalGrupo.addAll(grupo);
		}
		
		
		return totalGrupo;
		
	}
	
	@Path("Teste")
	@GET
	@Produces("application/json")
	public List<Tutoria> teste(){
		List<Tutoria> result = new ArrayList<Tutoria>();
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		List<Tutoria> tutorias = new TutoriaService().listarAno(ano);
		for (Tutoria tutoria : tutorias) {
			List<Grupo> grupos = new GrupoService().listarTutor(tutoria.getIdtutoria());
			if (grupos.isEmpty())
				result.add(tutoria);
		}
		return result;
	}
	
	@Path("DadosTutoriaProfessores/")
	@GET
	@Produces("application/json")
	public List<Object> tutoriaDados()
	{
		List<Object> resultado = new ArrayList<Object>();
		
		String ano = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));

		List<Tutoria> tutorias = new TutoriaService().listarAno(ano);
		for (Tutoria tutoria : tutorias) {
			Hashtable<String, Object> professorDados = new Hashtable<String, Object>();
			ProfessorFuncionarioVariavel professorVariavel = new ProfessorFuncionarioVariavelService().listarProfessor(tutoria.getTutor().getIdprofessorFuncionario()).get(0);
			professorDados.put("faltas", new PresencaProfessorService().listarFaltas(professorVariavel.getIdprofessorFuncionarioVariavel()).size());
			professorDados.put("presencas", new PresencaProfessorService().listarPresencas(professorVariavel.getIdprofessorFuncionarioVariavel()).size());
			professorDados.put("nome", tutoria.getTutor().getNome());
			professorDados.put("idProfessor", tutoria.getTutor().getIdprofessorFuncionario());
			professorDados.put("foto", tutoria.getTutor().getFotoProfessorFuncionario());
			float objetivosTotais = 0;
			float objetivosCompletos = 0;
			float objetivosCorrigidos = 0;
			List<Grupo> grupos = new GrupoService().listarTutor(tutoria.getIdtutoria());
			for (Grupo grupo : grupos) {
				List<AlunoVariavel> alunosGrupo = new AlunoVariavelService().listaGrupo(grupo.getIdgrupo());
				for (AlunoVariavel alunoVariavel : alunosGrupo) {
					List<Roteiro> roteirosAno = new RoteiroService().listarAno(alunoVariavel.getAnoEstudo().getIdanoEstudo());
					for (Roteiro roteiro : roteirosAno) {
						objetivosTotais += new ObjetivoService().listarRoteiroTotal(roteiro.getIdroteiro());
					}
					List<AtribuicaoRoteiroExtra> roteirosExtra = new AtribuicaoRoteiroExtraService().listarAluno(new AlunoService().listarkey(alunoVariavel.getAluno().getIdAluno()).get(0), new AnoLetivoService().listarkey(alunoVariavel.getAnoLetivo().getIdanoLetivo()).get(0));
					for (AtribuicaoRoteiroExtra atribuicaoRoteiroExtra : roteirosExtra) {
						objetivosTotais += new ObjetivoService().listarRoteiroTotal(atribuicaoRoteiroExtra.getRoteiro().getIdroteiro());
					}
					List<PlanejamentoRoteiro> planejamentosCompletos = new PlanejamentoRoteiroService().listarAlunoCompletosLista(alunoVariavel.getAluno().getIdAluno());
					List<PlanejamentoRoteiro> planejamentosCorrigidos = new PlanejamentoRoteiroService().listarAlunoCorrigidosLista(alunoVariavel.getAluno().getIdAluno());
					
					objetivosCompletos += planejamentosCompletos.size();
					objetivosCorrigidos += planejamentosCorrigidos.size();							
				}
			}
			if (objetivosTotais == 0)
				grupos.clear();
			Hashtable<String, Float> objetivos = new Hashtable<String, Float>();
			if (objetivosTotais > 0)
			{
				objetivos.put("completos", (objetivosCompletos + objetivosCorrigidos) / objetivosTotais);
				objetivos.put("corrigidos", objetivosCorrigidos / objetivosTotais);
			}
			else
			{
				objetivos.put("completos", 0.0f);
				objetivos.put("corrigidos", 0.0f);
			}
			
			professorDados.put("objetivos", objetivos);
			if (!grupos.isEmpty())
			{
				resultado.add(professorDados);
			}
		}
		return resultado;
	}
	
	
	@Path("ProfessorNomeId/")
	@GET
	@Produces("application/json")
	public List<ProfessorFuncionario> getProfessorNomeId(){
		return new ProfessorFuncionarioService().listarIdNome();
	}
	
	@Path("ProfessorUpdate/")
	@POST
	@Produces("text/plain")
	public String ProfessorUpdate(@FormParam("id") int id, @FormParam("nome") String nome){
		
		ProfessorFuncionario result = new ProfessorFuncionario();
		
		ProfessorFuncionario prof = new ProfessorFuncionarioService().listarkey(id).get(0);
		prof.setNome(nome);
		
		result = new ProfessorFuncionarioService().atualizarProfessorFuncionario(prof);
		
		return Integer.toString(result.getIdprofessorFuncionario());
	}
	

	/**
	 * Removes the professor funcionario.
	 * 
	 * @param action
	 *            the action
	 * @param id
	 *            the id
	 * @return the string
	 */
	@Path("{action}/{id}")
	@Produces("text/plain")
	public String removeProfessorFuncionario(
			@PathParam("action") String action, @PathParam("id") int id) {

		logger.info("ProfessorFuncionario  " + action);
		if (action.equals("delete")) {
			List<ProfessorFuncionario> resultado;
			resultado = new ProfessorFuncionarioService().listarkey(id);
			ProfessorFuncionario evento = resultado.get(0);
			new ProfessorFuncionarioService().deletarProfessorFuncionario(evento);
			return "true";
		} else {
			return "false";
		}

	}
	
	

	/**
	 * Criar e alterar professor funcionario
	 * @param action
	 * @param strid
	 * @param nome
	 * @param rua
	 * @param numero
	 * @param complemento
	 * @param cep
	 * @param bairro
	 * @param cidade
	 * @param estado
	 * @param naturalidadeEstado
	 * @param naturalidadePais
	 * @param dataEntradaPrefeitura
	 * @param ativo
	 * @param dataEntradaEscola
	 * @param observacao
	 * @return id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(

			@FormParam("action") String action, @FormParam("id") String strid,
			@FormParam("nome") String nome, @FormParam("rua") String rua,
	
			@FormParam("numero") String numero,
			@FormParam("complemento") String complemento,

			@FormParam("cep") String cep, @FormParam("bairro") String bairro,
			@FormParam("cidade") String cidade,

			@FormParam("estado") String estado,
			@FormParam("naturalidadeEstado") String naturalidadeEstado,
			@FormParam("naturalidadePais") String naturalidadePais,

			
			@FormParam("dataEntradaPrefeitura") String dataEntradaPrefeitura,
			@FormParam("ativo") String ativo,

			@FormParam("dataEntradaEscola") String dataEntradaEscola,
			/*@FormParam("perfil") int perfil,*/
			@FormParam("observacao") String observacao
			
			


	) {
		ProfessorFuncionario objProfessorFuncionario = new ProfessorFuncionario();
		logger.info("eventoAction ...");
		ProfessorFuncionario resultado;
		StringUtil stringUtil = new StringUtil();

		if (action.equals("create")) {

			if (!nome.isEmpty()) {
				objProfessorFuncionario.setNome(nome);
			}
			if (!rua.isEmpty()) {
				objProfessorFuncionario.setRua(rua);
			}
			if (!numero.isEmpty()) {
				objProfessorFuncionario.setNumero(numero);
			}
			if (!complemento.isEmpty()) {
				objProfessorFuncionario.setComplemento(complemento);
			}

			if (!cep.isEmpty()) {
				objProfessorFuncionario.setCep(cep);
			}
			if (!bairro.isEmpty()) {
				objProfessorFuncionario.setBairro(bairro);
			}
			if (!cidade.isEmpty()) {
				objProfessorFuncionario.setCidade(cidade);
			}

			if (!estado.isEmpty()) {
				objProfessorFuncionario.setEstado(estado);
			}
		//	if (!naturalidadeEstado.isEmpty()) {
				objProfessorFuncionario.setNaturalidadeEstado(naturalidadeEstado);
		//	}
			if (!naturalidadePais.isEmpty()) {
				objProfessorFuncionario.setNaturalidadePais(naturalidadePais);
			}

			
			if (!dataEntradaPrefeitura.isEmpty()) {
				objProfessorFuncionario.setDataEntradaPrefeitura(stringUtil.converteStringData(dataEntradaPrefeitura));
			}
			if (!ativo.isEmpty()) {
				objProfessorFuncionario.setAtivo(ativo);
			}

			if (!dataEntradaEscola.isEmpty()) {
				objProfessorFuncionario.setDataEntradaEscola(stringUtil.converteStringData(dataEntradaEscola));
			}
			if (!observacao.isEmpty()) {
				objProfessorFuncionario.setObservacao(observacao);
			}

			
			resultado = new ProfessorFuncionarioService().criarProfessorFuncionario(objProfessorFuncionario);

		} else if (action.equals("update")) {

			int id = Integer.parseInt(strid);
			List<ProfessorFuncionario> rsProfessorFuncionario;
			rsProfessorFuncionario = new ProfessorFuncionarioService().listarkey(id);
			objProfessorFuncionario = rsProfessorFuncionario.get(0);

			if (!nome.isEmpty()) {
				objProfessorFuncionario.setNome(nome);
			}
			if (!rua.isEmpty()) {
				objProfessorFuncionario.setRua(rua);
			}
			if (!numero.isEmpty()) {
				objProfessorFuncionario.setNumero(numero);
			}
			if (!complemento.isEmpty()) {
				objProfessorFuncionario.setComplemento(complemento);
			}

			if (!cep.isEmpty()) {
				objProfessorFuncionario.setCep(cep);
			}
			if (!bairro.isEmpty()) {
				objProfessorFuncionario.setBairro(bairro);
			}
			if (!cidade.isEmpty()) {
				objProfessorFuncionario.setCidade(cidade);
			}

			if (!estado.isEmpty()) {
				objProfessorFuncionario.setEstado(estado);
			}
			if (!naturalidadeEstado.isEmpty()) {
				objProfessorFuncionario.setNaturalidadeEstado(naturalidadeEstado);
			}
			if (!naturalidadePais.isEmpty()) {
				objProfessorFuncionario.setNaturalidadePais(naturalidadePais);
			}

			if (!dataEntradaPrefeitura.isEmpty()) {
				objProfessorFuncionario.setDataEntradaPrefeitura(stringUtil.converteStringData(dataEntradaPrefeitura));
			}
			if (!ativo.isEmpty()) {
				objProfessorFuncionario.setAtivo(ativo);
			}

			if (!dataEntradaEscola.isEmpty()) {
				objProfessorFuncionario.setDataEntradaEscola(stringUtil.converteStringData(dataEntradaEscola));
			}
			if (!observacao.isEmpty()) {
				objProfessorFuncionario.setObservacao(observacao);
			}
			
			resultado = new ProfessorFuncionarioService().atualizarProfessorFuncionario(objProfessorFuncionario);

		} else {
			return "0";
		}
		return Integer.toString(resultado.getIdprofessorFuncionario());

	}

	/**
	 * upload de imagem profesor funcionario
	 * @param strId
	 * @param uploadedInputStream
	 * @param fileDetail
	 * @return obj professorFuncionario
	 */
	@POST
	@Path("upload/ProfessorFuncionario/imagem/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ProfessorFuncionario eventoAction2(

			@PathParam("id") String strId,
			@FormDataParam("imagem") InputStream uploadedInputStream,
			@FormDataParam("imagem") FormDataContentDisposition fileDetail

	) {

		ProfessorFuncionario objProfessorFuncionario = new ProfessorFuncionario();
		ProfessorFuncionario resultado = new ProfessorFuncionario();

		int id = Integer.parseInt(strId);
		List<ProfessorFuncionario> rsProfessorFuncionario;
		rsProfessorFuncionario = new ProfessorFuncionarioService().listarkey(id);
		objProfessorFuncionario = rsProfessorFuncionario.get(0);

		StringUtil stringUtil = new StringUtil();
		String imagem = stringUtil.geraNomeAleatorio(fileDetail.getFileName(),50);
		String uploadedFileLocation = "/home/tomcat/webapps/files/" + imagem;
		String anexo = "http://177.55.99.90/files/" + imagem;

		Upload upload = new Upload();
		upload.writeToFile(uploadedInputStream, uploadedFileLocation);

		logger.info("anexo" + anexo);

		if (objProfessorFuncionario.getFotoProfessorFuncionario() != null)
			upload.deleteFile(objProfessorFuncionario.getFotoProfessorFuncionario());
		objProfessorFuncionario.setFotoProfessorFuncionario(anexo);

		resultado = new ProfessorFuncionarioService().atualizarProfessorFuncionario(objProfessorFuncionario);

		return resultado;

	}

}
