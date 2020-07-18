package gr.aueb.mscis.gas.test.service;

import gr.aueb.mscis.gas.model.Job;
import gr.aueb.mscis.gas.model.SimpleUser;
import gr.aueb.mscis.gas.model.Supervisor;
import gr.aueb.mscis.gas.model.User;
import gr.aueb.mscis.gas.persistence.Initializer;
import gr.aueb.mscis.gas.persistence.JPAUtil;
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

public class JobCompleteServiceTest {

    //private Initializer initializer;
	protected EntityManager em;

    @Before
    public void setup(){
    em = JPAUtil.getCurrentEntityManager();
	Initializer dataHelper = new Initializer();
	dataHelper.prepareData();
    }
    
    
    @After
	public void tearDown(){
		em.close();
	}
	
	@Test
	public void findSupervisorJobsTest() {
		//EntityManager em = JPAUtil.getCurrentEntityManager();
		JobCompleteService jobcompleteservice = new JobCompleteService(em);
		
		Supervisor tasos=new Supervisor("Tasos", "Tsakiris", "ttt@.gr");
		
		Job kalorifer = new Job("Επισκευή καλοριφέρ");
		kalorifer.setSupervisor(tasos);

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(kalorifer);
        em.persist(tasos);
        
        
        tx.commit();
		
        
		kalorifer.setSupervisor(tasos);
	       Assert.assertEquals(kalorifer.getSupervisor(), tasos);
	       Assert.assertTrue(tasos.getJobs().contains(kalorifer));
	       Assert.assertTrue(jobcompleteservice.findAllPendingJobs(tasos).contains(kalorifer));
	       GregorianCalendar telikh_hmeromhnia=new GregorianCalendar(2018,03,07);
	       
	       kalorifer.setFinalDate(telikh_hmeromhnia);
	       EntityTransaction tx1 = em.getTransaction();
	        tx1.begin();

	        em.persist(kalorifer);
	        
	        tx1.commit();
	       Assert.assertFalse(jobcompleteservice.findAllPendingJobs(tasos).contains(kalorifer));
	       
	       kalorifer.setAntilogismenh();
	       EntityTransaction tx2 = em.getTransaction();
	        tx2.begin();

	        em.persist(kalorifer);
	        
	        
	        tx2.commit();
	        
	       //Έλεγχος αν ένα completed Job εμφανίζεται στα Pending Job του Supervisor
	       Assert.assertFalse(jobcompleteservice.findAllPendingJobs(tasos).contains(kalorifer));
           
	}
	
	
	@Test
	public void findSupervisorJobs() {
		//EntityManager em = JPAUtil.getCurrentEntityManager();
		JobCompleteService jobcompleteservice = new JobCompleteService(em);
		
		Supervisor tasos=new Supervisor("Tasos", "Tsakiris", "ttt@.gr");
		
		Job kalorifer = new Job("Επισκευή καλοριφέρ");
		kalorifer.setSupervisor(tasos);

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(kalorifer);
        em.persist(tasos);
        
        
        tx.commit();
		
        
		kalorifer.setSupervisor(tasos);
	       Assert.assertEquals(kalorifer.getSupervisor(), tasos);
	       Assert.assertTrue(tasos.getJobs().contains(kalorifer));
	       Assert.assertTrue(jobcompleteservice.findAllPendingJobs(tasos).contains(kalorifer));
	       GregorianCalendar telikh_hmeromhnia=new GregorianCalendar(2018,03,07);
	       
	       kalorifer.setFinalDate(telikh_hmeromhnia);
	       EntityTransaction tx1 = em.getTransaction();
	        tx1.begin();

	        em.persist(kalorifer);
	        
	        tx1.commit();
	       Assert.assertFalse(jobcompleteservice.findAllPendingJobs(tasos).contains(kalorifer));
	       
	       kalorifer.setAntilogismenh();
	       EntityTransaction tx2 = em.getTransaction();
	        tx2.begin();

	        em.persist(kalorifer);
	        
	        
	        tx2.commit();
	        
	       //Έλεγχος αν το Completed Job kalorifer εμφανίζεται στα Pending Job του Supervisor
	       Assert.assertFalse(jobcompleteservice.findAllPendingJobs(tasos).contains(kalorifer));

	       //Έλεγχος αν το Completed Job kalorifer εμφανίζεται στα Completed Job του Supervisor
	       Assert.assertTrue(jobcompleteservice.findAllCompletedJobs(tasos).contains(kalorifer));
	       
           
	}
	
	@Test
	public void completeJob() {
		//EntityManager em = JPAUtil.getCurrentEntityManager();
		
		GregorianCalendar finaldate=new GregorianCalendar();
		finaldate.set(GregorianCalendar.YEAR, 2018);
		finaldate.set(GregorianCalendar.MONTH, 3);
		finaldate.set(GregorianCalendar.DATE, 5);
		
		Supervisor kostas1=new Supervisor("KTasos", "Ksakiris", "ttst@.gr");
		Supervisor kostas2=new Supervisor("KTasos2", "Ksakiris2", "ttst2@.gr");
		
		Job levhtas1 = new Job("Επισκευή Λέβητα1");
		levhtas1.setSupervisor(kostas1);
		levhtas1.setFinalStart(finaldate);
	
		Job levhtas2 = new Job("Επισκευή Λέβητα2");
		levhtas2.setSupervisor(kostas2);
		
		Job douleia1 = new Job("douleia1");
		Job douleia2 = new Job("douleia2");
		
		 EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        em.persist(kostas1);
	        em.persist(kostas2);
	        em.persist(levhtas1);
	        em.persist(levhtas2);	  
	        em.persist(douleia1);
	        em.persist(douleia2);
	        tx.commit();
	        
		
		JobCompleteService jobcompleteservice = new JobCompleteService(em);
		Assert.assertTrue(jobcompleteservice.completeJob(levhtas1, finaldate));
		Assert.assertFalse(jobcompleteservice.completeJob(levhtas2, finaldate));

		Assert.assertEquals(levhtas2.getFinalStart(), null);
		
		//8etoume final start wste na oloklhrw8ei trexei kai to job levhtas2
		jobcompleteservice.setFinalStart(levhtas2, finaldate);
		Assert.assertEquals(levhtas2.getFinalStart(), finaldate);
		Assert.assertNotEquals(levhtas2.getFinalDate(), finaldate);
		Assert.assertTrue(jobcompleteservice.completeJob(levhtas2, finaldate));	
		
		levhtas1.setJobCost(98.99);
		jobcompleteservice.setJobCost(levhtas1, 99.99);
		Assert.assertTrue(levhtas1.getJobCost()==99.99);
		

		
		jobcompleteservice.setJobnameCost("douleia1", 85.55);
		Assert.assertTrue(douleia1.getJobCost()==85.55);
		
		jobcompleteservice.setJobCost(jobcompleteservice.getJobFromName("douleia2"), 81.00);
		Assert.assertTrue(douleia2.getJobCost()==81.00);
		
		
	}
	            

	
}
	