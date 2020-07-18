package gr.aueb.mscis.gas.test.resources;

import org.glassfish.jersey.test.JerseyTest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;
import gr.aueb.mscis.gas.model.Tool;
import gr.aueb.mscis.gas.resource.ToolInfo;
import gr.aueb.mscis.gas.resource.ToolResource;


public class ToolResourceTest extends JerseyTest {
	
	@Override
	protected Application configure() {
		return new ResourceConfig(ToolResource.class);
	}
	protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCreateNewTool() {
		List<Tool> results = null;
		
		/*
		Query query = em.createQuery("select b from Tool b");
		//results = query.getResultList(); 	
		System.out.println(results.size());
		*/
		
		ToolInfo toolinfo = new ToolInfo("atesttool");
		Response response = target("tool").request().post(Entity.entity(toolinfo, MediaType.APPLICATION_JSON));
		//System.out.println(response.getStatus());
		Assert.assertEquals(201, response.getStatus());
		
		/*
		List<Tool> results2 = null;
		Query query2 = em.createQuery("select b from Tool b");
		results2 = query2.getResultList(); 	
		//System.out.println(results2.size());
		Assert.assertFalse(results2.size()==results.size());
		*/
	}
	
}