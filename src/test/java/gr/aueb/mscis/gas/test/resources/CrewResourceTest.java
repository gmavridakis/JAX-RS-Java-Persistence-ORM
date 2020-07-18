package gr.aueb.mscis.gas.test.resources;

import org.glassfish.jersey.test.JerseyTest;
import javax.persistence.EntityManager;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.mscis.gas.model.Address;
import gr.aueb.mscis.gas.persistence.Initializer;
import gr.aueb.mscis.gas.persistence.JPAUtil;
import gr.aueb.mscis.gas.resource.CrewInfo;
import gr.aueb.mscis.gas.resource.ToolInfo;
import gr.aueb.mscis.gas.resource.ToolResource;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class CrewResourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(ToolResource.class);
	}
	protected EntityManager em;
	
	@Before
	public void setup(){
		// prepare database for each test
		em = JPAUtil.getCurrentEntityManager();
		Initializer dataHelper = new Initializer();
		// dataHelper.prepareData();
	}
	
	@After
	public void tearDown(){
		em.close();
	}
	
	@Test
	public void testCreateToolService() {
        Address address = new Address("Troias","2","Athens","11362","Greece");
		CrewInfo crewinfo = new CrewInfo("atestcrew",address);
		Response response = target("crew/createCrew").request().post(Entity.entity(crewinfo, MediaType.APPLICATION_JSON));
		//System.out.println(response.getStatus());
		//Assert.assertEquals(201, response.getStatus());
	}
	
}