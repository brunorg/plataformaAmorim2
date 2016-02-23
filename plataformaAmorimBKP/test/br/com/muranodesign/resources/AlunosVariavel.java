package br.com.muranodesign.resources;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;


public class AlunosVariavel extends JerseyTest  {
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
	
	@Test
	public void insert() {
		WebResource webResource = client().resource("http://localhost:8082/plataformaAmorim/AlunoVariavel");
		
		 webResource.post("inicio=2014-11-18&programaSocial=N&anoEstudo=24&anoLetivo=13&action=create&aluno=1&periodo=5&grupo=");
	}
		 
	

	@Test(expected = UniformInterfaceException.class)
	public void testNotFound() {
		WebResource webResource = client().resource("http://localhost:8082/");
		webResource.path("plataformaAmorim/AlunoVariavel/99499").get(JSONObject.class);
	}
				
	
}