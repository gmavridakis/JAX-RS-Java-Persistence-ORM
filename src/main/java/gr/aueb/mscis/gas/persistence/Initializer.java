package gr.aueb.mscis.gas.persistence;
import gr.aueb.mscis.gas.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


public class Initializer  {
    /**
     * Remove all data from database.
     * The functionality must be executed within the bounds of a transaction
     */
    public void  eraseData() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
       
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query queryDeleteCrews = null;
        Query queryDeleteJobs = null;
        Query queryDeleteRequest = null;
        Query queryDeleteTechnicians = null;
        Query queryDeleteTool = null;
        Query queryDeleteUsers = null;

        queryDeleteTechnicians = em.createNativeQuery("delete from Technician");
        queryDeleteTechnicians.executeUpdate();
        

        queryDeleteRequest = em.createNativeQuery("delete from Request");
        queryDeleteRequest.executeUpdate();
        
        queryDeleteJobs = em.createNativeQuery("delete from Job");
        queryDeleteJobs.executeUpdate();
 
        queryDeleteUsers = em.createNativeQuery("delete from User");
        queryDeleteUsers.executeUpdate();
        
        queryDeleteCrews = em.createNativeQuery("delete from Crew");
        queryDeleteCrews.executeUpdate();
        


        

        
        queryDeleteTool = em.createNativeQuery("delete from tools");
        queryDeleteTool.executeUpdate();
        

        
        tx.commit(); 
    }
    

    public void prepareData() {

        eraseData();                      
        
        Address technicianAddress = new Address();
        technicianAddress.setStreet("Troias");
		technicianAddress.setNumber("2");
		technicianAddress.setCity("Athens");
		technicianAddress.setAreaCode("11362");
		technicianAddress.setCountry("Greece");
		
		Address crewAddress = new Address();
		crewAddress.setStreet("Evelpidon");
		crewAddress.setNumber("29");
		crewAddress.setCity("Athens");
		crewAddress.setAreaCode("11362");
		crewAddress.setCountry("Greece");
		
		Address clientAddress = new Address();
		clientAddress.setStreet("Androy");
		clientAddress.setNumber("9");
		clientAddress.setCity("Athens");
		clientAddress.setAreaCode("12462");
		clientAddress.setCountry("Greece");
		
        Crew ghostbusters = new Crew("ghostbusters", crewAddress);
        
        Job job1 = new Job("Επισκευή καλοριφέρ");
        Job job12 = new Job("Επισκευή καλοριφέρ12");
        Job job123 = new Job("episkevi_kalorifer123");
        
        Request a_request = new Request("King" , "Luther" , clientAddress);
        Request another_request = new Request("Greg" , "Mavridakis" , clientAddress);
        
        SimpleUser simpleuser = new SimpleUser("Petrou", "Nikos", "nikosPetrou@Petrou.gr");
        SimpleUser suser = new SimpleUser("Gore", "Martin", "gore.m@dm.com");
        
        Supervisor terminator = new Supervisor("Gahan", "Dave", "gahan.d@dm.com");
        Supervisor supervisor = new Supervisor("Tsakiris", "Anastasios", "tastsak@gmail.com");
        Supervisor supervisor1 = new Supervisor("Nikolakhs", "kis", "stastsak@gmail.com");

        job1.setSupervisor(supervisor);
        Technician superMario = new Technician("Super", "Mario", technicianAddress, "2108888888", null);
        
        Tool atool = new Tool("katsavidi leme!");
        
        EntityManager em = JPAUtil.getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        em.persist(supervisor);
        em.persist(simpleuser);
        em.persist(supervisor1);
        em.persist(job1);
        em.persist(job12);
        em.persist(job123);
        em.persist(terminator);
        em.persist(suser);
        em.persist(superMario);
        em.persist(ghostbusters);
        em.persist(atool);
        em.persist(a_request);
        em.persist(another_request);
        
        tx.commit();

    
    }

    
}



