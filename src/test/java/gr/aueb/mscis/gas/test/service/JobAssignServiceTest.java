package gr.aueb.mscis.gas.test.service;

import gr.aueb.mscis.gas.model.Address;
import gr.aueb.mscis.gas.model.Crew;
import gr.aueb.mscis.gas.model.Job;
import gr.aueb.mscis.gas.model.Request;
import gr.aueb.mscis.gas.model.SimpleUser;
import gr.aueb.mscis.gas.model.Supervisor;
import gr.aueb.mscis.gas.model.Tool;
import gr.aueb.mscis.gas.model.User;
import gr.aueb.mscis.gas.persistence.Initializer;
import gr.aueb.mscis.gas.persistence.JPAUtil;
import gr.aueb.mscis.gas.service.JobAssignService;
import gr.aueb.mscis.gas.service.JobCompleteService;
import gr.aueb.mscis.gas.service.SupervisorService;


import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JobAssignServiceTest {

    //private Initializer initializer;
	protected EntityManager em;

    @Before
    public void setUpJpa() {
    	/*initializer = new Initializer();
        initializer.prepareData();*/
    	em = JPAUtil.getCurrentEntityManager();
		Initializer dataHelper = new Initializer();
		dataHelper.prepareData();
    }
    
    @After
	public void tearDown(){
		em.close();
	}
	
	@Test
	public void findRequest() {
		//EntityManager em = JPAUtil.getCurrentEntityManager();
		JobAssignService jobassignservice = new JobAssignService(em);
		

		
        Address addressKWSTA = new Address();
        addressKWSTA.setStreet("Evelpidon");
        addressKWSTA.setNumber("47a");
        addressKWSTA.setCity("Athens");
        addressKWSTA.setAreaCode("11362");
        addressKWSTA.setCountry("Greece");
        


		Request request1=new Request("kwstas", "nikolaou", addressKWSTA);
		Request request2=new Request("kwstass", "nnikolaou", addressKWSTA);
		Job jobSavvato=new Job("episkeuh a1");
		Job jobKyriakh=new Job("episkeuh a2");
		request1.setJob(jobSavvato);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(request1);
        em.persist(request2);
        em.persist(jobSavvato);
        em.persist(jobKyriakh);
        tx.commit();
		
        Assert.assertFalse(jobassignservice.findAllRequest().contains(request1));
        Assert.assertTrue(jobassignservice.findAllRequest().contains(request2));
        
        Assert.assertFalse(jobassignservice.assignRequestToJob(request1, jobSavvato));
        Assert.assertTrue(jobassignservice.assignRequestToJob(request2, jobKyriakh));
        Assert.assertEquals(request1.getJob(), jobSavvato);
        
        
        
        Crew plhrwma1 = new Crew("plhrwma", addressKWSTA);
        Crew plhrwma2 = new Crew("plhrwma2", addressKWSTA);
        Tool ergaleio1=new Tool("trupani2");
        Tool ergaleio2=new Tool("katsavidi2");
        Tool dia8esimoergaleio=new Tool("katsavidi3");
        
        Job jobDeuteras=new Job("episkeuh a3");
        Job jobDeuteras2=new Job("episkeuh a4");
        Job jobDeuteras3=new Job("episkeuh a5");
        
        jobDeuteras.setCrew(plhrwma1);
        jobDeuteras.addTool(ergaleio1);
        EntityTransaction tx1 = em.getTransaction();
        tx1.begin();
        em.persist(plhrwma1);
        em.persist(plhrwma2);
        em.persist(ergaleio1);
        em.persist(ergaleio2);
        em.persist(jobDeuteras);
        em.persist(jobDeuteras2);
        em.persist(jobDeuteras3);
        em.persist(dia8esimoergaleio);
        tx1.commit();
        Assert.assertFalse(jobassignservice.assignCrewToJob(plhrwma1, jobDeuteras));
        Assert.assertFalse(jobassignservice.assignCrewToJob(plhrwma2, jobDeuteras));
        Assert.assertFalse(jobassignservice.assignCrewToJob(plhrwma1, jobDeuteras2));
        Assert.assertTrue(jobassignservice.assignCrewToJob(plhrwma2, jobDeuteras2));
        Assert.assertFalse(jobassignservice.assignToolToJob(ergaleio1, jobDeuteras));
        Assert.assertTrue(jobassignservice.assignToolToJob(ergaleio2, jobDeuteras2));
        Assert.assertTrue(jobDeuteras2.getTools().contains(ergaleio2));
        jobassignservice.assignToolToJob(ergaleio2, jobDeuteras3);
        Assert.assertFalse(jobassignservice.findAllTools().contains(ergaleio2));
        Assert.assertTrue(jobassignservice.findAllTools().contains(dia8esimoergaleio));
		//em.close();	
	}
}
	