package gr.aueb.mscis.gas.test.service;

import gr.aueb.mscis.gas.model.SimpleUser;
import gr.aueb.mscis.gas.model.Supervisor;
import gr.aueb.mscis.gas.model.User;
import gr.aueb.mscis.gas.persistence.Initializer;
import gr.aueb.mscis.gas.persistence.JPAUtil;
import gr.aueb.mscis.gas.service.SupervisorService;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SupervisorServiceTest {

    //private Initializer initializer;
	protected EntityManager em;

    @Before
    /*public void setUpJpa() {
    	initializer = new Initializer();
        initializer.prepareData();
    }*/
    public void setup(){
		// prepare database for each test
		em = JPAUtil.getCurrentEntityManager();
		
	}
    
    @After
	public void tearDown(){
		em.close();
	}
	
	@Test
	public void findSupervisor() {
		//EntityManager em = JPAUtil.getCurrentEntityManager();
		SupervisorService supervisorservice = new SupervisorService(em);
		Assert.assertEquals(supervisorservice.findSupervisorByLastName("Tsakiris"), "Tsakiris");
	}
	
	
	@Test
	public void insertSupervisorTest() {
		//EntityManager em = JPAUtil.getCurrentEntityManager();
		SupervisorService supervisorservice = new SupervisorService(em);
		Supervisor katerina=new Supervisor("Katerina", "Katerina", "katerina@katerina.gr");
		supervisorservice.createSupervisor(katerina);
		int x=katerina.getId();
		Assert.assertEquals(supervisorservice.findSupervisorById(x), katerina);
		Assert.assertEquals(supervisorservice.saveOrUpdateSupervisor(katerina), true);
		supervisorservice.deleteSupervisor(katerina);
		Assert.assertFalse(supervisorservice.findAllSupervisors().contains(katerina));

	}
	@Test
	public void checkSimpleUser() {
		//EntityManager em = JPAUtil.getCurrentEntityManager();
		SupervisorService supervisorservice = new SupervisorService(em);
		
		User simpleuser1 = new SimpleUser("Dimitriou", "Dimitriou", "Dimitriou@Petrou.gr");
		Assert.assertFalse(supervisorservice.findAllSupervisors().contains(simpleuser1));
	}
	
}
