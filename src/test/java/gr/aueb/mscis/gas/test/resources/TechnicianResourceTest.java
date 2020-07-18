package gr.aueb.mscis.gas.test.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Assert;
import org.junit.Test;


import gr.aueb.mscis.gas.model.Technician;
import gr.aueb.mscis.gas.resource.TechnicianInfo;
import gr.aueb.mscis.gas.resource.TechnicianResource;

public class TechnicianResourceTest extends JerseyTest {
	
	/*public TechnicianResourceTest() {
		super();
	}*/

	/*public TechnicianResourceTest(TestContainerFactory testContainerFactory) {
		super(testContainerFactory);
	}*/
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		//dataHelper = new Initializer();
		//dataHelper.prepareData();
	}
	
	@Override
	protected Application configure() {
		/*
		 * 
		 */
		return new ResourceConfig(TechnicianResource.class);
	}
	
	Client client = ClientBuilder.newClient();
	WebTarget target = client.target("http://localhost:9998/technician/fetch/");
	WebTarget target2 = client.target("http://localhost:9998/technician/fetchall/");
	WebTarget target3 = client.target("http://localhost:9998/technician/something/");
	
	@Test
	//@Consumes(MediaType.APPLICATION_JSON)
	public void testTechnician() {
				
		Builder builder = target.request().accept(MediaType.APPLICATION_JSON);
		TechnicianInfo responseTechnician = builder.get(TechnicianInfo.class);
		Assert.assertEquals("Mario", responseTechnician.getName());
	}
	
	@Test
	//@Consumes(MediaType.APPLICATION_XML)
	public void testAllTechnicians() {
				
		Builder builder = target2.request().accept(MediaType.APPLICATION_XML);
		List<TechnicianInfo> responseTechnicians = builder.get(new GenericType<List<TechnicianInfo>>() {});
		Assert.assertEquals(2, responseTechnicians.size());
	}
	
	@Test
	public void testToolService() {
				
		Builder builder = target3.request();
		String responseString = builder.get(new GenericType<String>() {});
		Assert.assertEquals("test", responseString);
	}

}
