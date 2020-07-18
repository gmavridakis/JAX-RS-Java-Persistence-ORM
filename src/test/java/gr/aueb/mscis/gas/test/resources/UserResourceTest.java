package gr.aueb.mscis.gas.test.resources;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;

import org.glassfish.jersey.test.JerseyTest;

import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.mscis.gas.model.Job;
import gr.aueb.mscis.gas.model.Tool;
import gr.aueb.mscis.gas.persistence.Initializer;
import gr.aueb.mscis.gas.persistence.JPAUtil;
import gr.aueb.mscis.gas.resource.JobAssignResource;
import gr.aueb.mscis.gas.resource.JobCompleteResource;
import gr.aueb.mscis.gas.resource.ToolInfo;
import gr.aueb.mscis.gas.service.JobAssignService;
import gr.aueb.mscis.gas.service.JobCompleteService;
import gr.aueb.mscis.gas.service.ToolService;



public class UserResourceTest extends JerseyTest {
	@Override
	protected Application configure() {
		return new ResourceConfig(JobAssignResource.class);
	}
	protected EntityManager em;
	
	
	@Before
	public void setup(){
		// prepare database for each test
		em = JPAUtil.getCurrentEntityManager();
		Initializer dataHelper = new Initializer();
		 dataHelper.prepareData();
	}
	
	@After
	public void tearDown(){
		//em.close();
	}
	
	@Test
	public void Userlogin() {


		
		Builder response = target("login")
				 .resolveTemplate("username", "tastsak@hotmail.com")
		         .resolveTemplate("password", "123456")
		         .request(MediaType.APPLICATION_JSON);
		Assert.assertTrue(response.get()!=null);
	}
	
}