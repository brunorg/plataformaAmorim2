package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AlunoService;
import br.com.muranodesign.business.FichaInscricaoService;
import br.com.muranodesign.business.PendenciasProducaoAlunoService;
import br.com.muranodesign.business.RoteiroService;
import br.com.muranodesign.model.PendenciasProducaoAluno;

/**
 * 
 * @author Kevyn
 *
 */
@Path("PendenciasProducaoAluno")
public class PendenciasProducaoAlunoResource {
	
	private Logger logger = Logger.getLogger(PendenciasProducaoAlunoResource.class.getName());
	
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("idaluno") int idaluno,
			@FormParam("idroteiro") int idroteiro){
		
		PendenciasProducaoAluno resultado = new PendenciasProducaoAluno();
		
		if (action.equals("delete"))
		{
			resultado = new PendenciasProducaoAlunoService().deletarPendenciasProducaoAluno(new PendenciasProducaoAlunoService().listarkey(id).get(0));
		}
		else if (action.equals("create"))
		{
			resultado.setAluno(new AlunoService().listarkey(idaluno).get(0));
			resultado.setRoteiro(new RoteiroService().listarkey(idroteiro).get(0));
			resultado.setPortfolioCompleto(0);
			//Verifica se o roteiro tem ficha de finaliza��o ou n�o
			//Se tiver o valor fica 0 (size = 1 logo size - 1 = 0), indicando que ela n�o foi entregue
			//Caso contr�rio o valor fica -1, indicando que ela n�o existe.
			resultado.setFichaFinalizacaoCompleta(new FichaInscricaoService().listarkey(idroteiro).size()- 1);
			resultado.setRoteiroCompleto(0);
			resultado = new PendenciasProducaoAlunoService().criarPendenciasProducaoAluno(resultado);
		}	 
		
		return Integer.toString(resultado.getIdPendenciasProducaoAluno());
	}
	
	@GET
	@Produces("application/json")
	public List<PendenciasProducaoAluno> getPendencias(){
		logger.debug("Listando Pendencias Producao Aluno...");
		List <PendenciasProducaoAluno> resultado = new PendenciasProducaoAlunoService().listarall();
		logger.debug("QTD Pendencias Producao Aluno: " + resultado.size());
		return resultado;
	}
	
	@Path("{id}")
	@GET
	@Produces("application/json")
	public List<PendenciasProducaoAluno> getPendencias(@PathParam("id") int key){
		logger.debug("Listando Pendencias Producao Aluno...");
		List <PendenciasProducaoAluno> resultado = new PendenciasProducaoAlunoService().listarkey(key);
		logger.debug("QTD Pendencias Producao Aluno: " + resultado.size());
		return resultado;
	}
	
	@Path("EntregarPortfolio/{idaluno}/{idroteiro}")
	@GET
	@Produces("text/plain")
	public String entregaPortfolio(
			@PathParam("idaluno") int idaluno,
			@PathParam("idroteiro") int idroteiro
			){
		PendenciasProducaoAluno resultado = new PendenciasProducaoAlunoService().listarAlunoRoteiro(idaluno, idroteiro).get(0);
		
		resultado.setPortfolioCompleto(1);
		
		if (resultado.getFichaFinalizacaoCompleta() != 0)
			resultado.setRoteiroCompleto(1);
		
		resultado = new PendenciasProducaoAlunoService().atualizarPendenciasProducaoAluno(resultado);
		
		return Integer.toString(resultado.getIdPendenciasProducaoAluno());
	}
	
	@Path("EntregarFichaFinalizacao/{idaluno}/{idroteiro}")
	@GET
	@Produces("text/plain")
	public String entregaFichaFinalizacao(
			@PathParam("idaluno") int idaluno,
			@PathParam("idroteiro") int idroteiro
			){
		PendenciasProducaoAluno resultado = new PendenciasProducaoAlunoService().listarAlunoRoteiro(idaluno, idroteiro).get(0);
		
		resultado.setFichaFinalizacaoCompleta(1);
		
		if (resultado.getPortfolioCompleto() != 0)
			resultado.setRoteiroCompleto(1);
		
		resultado = new PendenciasProducaoAlunoService().atualizarPendenciasProducaoAluno(resultado);
		
		return Integer.toString(resultado.getIdPendenciasProducaoAluno());
	}
	
	@Path("PendenciasAluno/{id}")
	@GET
	@Produces("application/json")
	public List<PendenciasProducaoAluno> getPendenciasAluno(@PathParam("id") int id){
		logger.debug("Listando Pendencias Producao Aluno...");
		logger.debug("Aluno: " + id);
		List <PendenciasProducaoAluno> resultado = new PendenciasProducaoAlunoService().listarAluno(id);
		logger.debug("QTD Pendencias Producao Aluno: " + resultado.size());
		return resultado;
	}
	
	@Path("ExistePendencia/{idaluno}/{idroteiro}")
	@GET
	@Produces("text/plain")
	public String getPendenciasAlunoRoteiro(@PathParam("idaluno") int idaluno, @PathParam("idroteiro") int idroteiro)
	{
		logger.debug("Listando Pendencias Producao Aluno...");
		logger.debug("Aluno: " + idaluno + " Roteiro: " + idroteiro);
		List <PendenciasProducaoAluno> resultado = new PendenciasProducaoAlunoService().listarAlunoRoteiro(idaluno, idroteiro);
		logger.debug("Existe Pendencias Producao Aluno: " + (resultado.size() > 0));
		return Integer.toString(resultado.size());
	}
	
}
