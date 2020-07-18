package gr.aueb.mscis.gas.test.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.mscis.gas.model.Address;
import gr.aueb.mscis.gas.model.Crew;
import gr.aueb.mscis.gas.model.Technician;
import gr.aueb.mscis.gas.persistence.Initializer;
import gr.aueb.mscis.gas.persistence.JPAUtil;
import gr.aueb.mscis.gas.service.CrewService;

public class CrewServiceTest {
	
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
		em.close();
	}
	
	@Test
	public void testPersistAValidTechnician(){
		
		CrewService service = new CrewService(em);
		Address technicianAddress = new Address();
        technicianAddress.setStreet("Evelpidon");
		technicianAddress.setNumber("47a");
		technicianAddress.setCity("Athens");
		technicianAddress.setAreaCode("11362");
		technicianAddress.setCountry("Greece");
		Technician newTechnician = service.createTechnician("Genikos", "Mastrochalastis", technicianAddress, "2109999999", null);
		// EntityManager.persist() updates the ID of the persisted object
		Assert.assertNotEquals(0, newTechnician.getId());
		em.close(); // close session
		
		// new session, data will be retrieved from database
		em = JPAUtil.getCurrentEntityManager();	

		Technician savedTechnician = em.find(Technician.class, newTechnician.getId()); 
		Assert.assertNotNull(savedTechnician);
		Assert.assertEquals("Mastrochalastis", savedTechnician.getSurname());
		em.close(); // close session
		
		// new session, data will be retrieved from database
		em = JPAUtil.getCurrentEntityManager();	

		Technician nullTechnician = service.createTechnician(null, "Nullidis", null, "2100000000", null); 
		Assert.assertNull(nullTechnician);
		
	}
	
	@Test
	public void testTechnicianAlreadyExists(){
		
		CrewService service = new CrewService(em);
		Address technicianAddress = new Address();
        technicianAddress.setStreet("Evelpidon");
		technicianAddress.setNumber("47a");
		technicianAddress.setCity("Athens");
		technicianAddress.setAreaCode("11362");
		technicianAddress.setCountry("Greece");
		//superMario already exists as a technician (from initializer)
		Technician superMarioDuplicate = service.createTechnician("Super", "Mario", technicianAddress, "2108888888", null);
		Assert.assertNull(superMarioDuplicate);
	}
	
	@Test
	public void testPersistAValidCrew(){
		
		CrewService service = new CrewService(em);
		Address crewAddress = new Address();
		crewAddress.setStreet("Evelpidon");
		crewAddress.setNumber("25");
		crewAddress.setCity("Athens");
		crewAddress.setAreaCode("11362");
		crewAddress.setCountry("Greece");
		Crew newCrew = service.createCrew("newCrew", crewAddress);
		// EntityManager.persist() updates the ID of the persisted object
		//Assert.assertNotEquals(0, newCrew.getId());
		
		em.close(); // close session
		
		// new session, data will be retrieved from database
		em = JPAUtil.getCurrentEntityManager();	

		Crew nullCrew = service.createCrew(null, crewAddress); 
		Assert.assertNull(nullCrew);
		
		em.close(); // close session
		
		// new session, data will be retrieved from database
		em = JPAUtil.getCurrentEntityManager();
		
		Crew savedCrew = em.find(Crew.class, newCrew.getId()); 
		Assert.assertNotNull(savedCrew);
		
		Assert.assertEquals("newCrew", savedCrew.getCrewName());
	}
	
	@Test
	public void testCrewAlreadyExists(){
		
		CrewService service = new CrewService(em);
		Address crewAddress = new Address();
		crewAddress.setStreet("Evelpidon");
		crewAddress.setNumber("47a");
		crewAddress.setCity("Athens");
		crewAddress.setAreaCode("11362");
		crewAddress.setCountry("Greece");
		//ghostbusters already exists as a crew (from initializer)
		Crew ghostbustersDuplicate = service.createCrew("ghostbusters", crewAddress);
		Assert.assertNull(ghostbustersDuplicate);
	}

	@Test
	public void testAssignTechnicianToCrew(){
		
		CrewService service = new CrewService(em);
		
		// retrieve technician
		List<Technician> resultTech = null;
		resultTech = em.createQuery("select t from Technician t where name ='Super'", Technician.class).getResultList();
		//Query queryTech = em.createQuery("select t from Technician t where name =:name");
		//queryTech.setParameter("name", "Super");
		//resultTech = queryTech.getResultList();
		Technician savedTechnician = resultTech.get(0);
		Assert.assertEquals("Super", savedTechnician.getName());
		
		// retrieve crew
		List<Crew> resultCrew = null;
		resultCrew = em.createQuery("select c from Crew c where crewName ='ghostbusters'", Crew.class).getResultList();
		Crew savedCrew = resultCrew.get(0);
		Assert.assertEquals("ghostbusters", savedCrew.getCrewName());
		// assign technician to crew
		service.assignTechnicianToCrew(savedTechnician, savedCrew);
		Assert.assertEquals("ghostbusters", savedTechnician.getCrew().getCrewName());
		
	}
}
