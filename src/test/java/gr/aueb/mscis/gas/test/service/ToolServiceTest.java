package gr.aueb.mscis.gas.test.service;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import gr.aueb.mscis.gas.model.Job;
import gr.aueb.mscis.gas.model.Tool;
import gr.aueb.mscis.gas.persistence.Initializer;
import gr.aueb.mscis.gas.persistence.JPAUtil;
import gr.aueb.mscis.gas.service.JobCompleteService;
import gr.aueb.mscis.gas.service.ToolService;

public class ToolServiceTest {
	
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
	public void testPersistAValidTool(){	
		ToolService service = new ToolService(em);
		
		Tool newTool = service.createTool("Επισκευή καλοριφέρ");
		
		Assert.assertEquals(newTool.getName(), "Επισκευή καλοριφέρ");
		Assert.assertNotSame(newTool.getId(), 0);
		
		Tool nullTool = service.createTool(null);
		Assert.assertEquals(nullTool, null);
		
		service.modifyTool(newTool, "NewtoolNameExample");
		Assert.assertEquals(newTool.getName(), "NewtoolNameExample");
		
		Assert.assertTrue(service.deleteTool(newTool));
	}
	
}
