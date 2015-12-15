package br.com.muranodesign.resources;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;


public class Usuario extends JerseyTest  {
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
	
	
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Mural");
		webResource.post("action=update&mensagem=testeMudanca&id=36");
	}
	
}
