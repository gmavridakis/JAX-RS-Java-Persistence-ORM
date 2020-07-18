package gr.aueb.mscis.gas.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import javax.persistence.Query;

import gr.aueb.mscis.gas.model.Address;
import gr.aueb.mscis.gas.model.Crew;
import gr.aueb.mscis.gas.model.Technician;
import gr.aueb.mscis.gas.model.Tool;
import gr.aueb.mscis.gas.model.User;
import gr.aueb.mscis.gas.persistence.JPAUtil;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
/**
 * CRUD operations and other functionality related to crews
 * 
 * @author AUEB.mscispt.team1
 *
 */
public class CrewService {

	private EntityManager em;  // δημιουργία μεταβλητής Entity Manager

	public CrewService (EntityManager em) {
		this.em = em;
	}
	
	public Technician createTechnician(String name, String surname, Address address, String telNo, Crew crew){
		
		if (name == null || surname == null || address == null || telNo == null){
			return null;
		}
		
		Technician newTechnician = new Technician(name, surname, address, telNo, crew);
		
		// if newTechnician already exists return null
		// theoroume pos kathe texnikos exei monadiko telNo
		List<Technician> results = findAllTechnicians();
		int resultSize = results.size();
		int resultCursor = 0;
		Boolean uniqueTechnician = true;
		while (uniqueTechnician && (resultCursor!=resultSize)) {
			if (newTechnician.getTelNo() == results.get(resultCursor).getTelNo()) {
				uniqueTechnician = false;
			}
			resultCursor++;
		}
		if (uniqueTechnician == false) {
			return null;
		}
				
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(newTechnician);
		tx.commit();
		
		return newTechnician;
		
	}
	@SuppressWarnings("unchecked")
	public List<Technician> findAllTechnicians() {
		List<Technician> results = null;

		results = em.createQuery("select t from Technician t", Technician.class)
				.getResultList();

		return results;
	}
	
	public Crew createCrew(String name, Address address){
		
		if (name == null || address == null){
			return null;
		}
		
		Crew newCrew = new Crew(name, address);
		
		// if newCrew already exists return null
		// theoroume pos kathe crew exei monadiko onoma
		List<Crew> results = findAllCrews();
		int resultSize = results.size();
		int resultCursor = 0;
		Boolean uniqueCrew = true;
		while (uniqueCrew && (resultCursor!=resultSize)) {
			if (newCrew.getCrewName() == results.get(resultCursor).getCrewName()) {
				uniqueCrew = false;
			}
			resultCursor++;
		}
		if (uniqueCrew == false) {
			return null;
		}
			
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(newCrew);
		tx.commit();
		
		return newCrew;
		
	}
	
	public void assignTechnicianToCrew(Technician technician, Crew crew) {
		
		technician.setCrew(crew);
	}
	
	public List<Crew> findAllCrews() {
		List<Crew> results = null;

		results = em.createQuery("select c from Crew c", Crew.class)
				.getResultList();

		return results;
	}
	
	public void modifyTechnician(Technician technician, String name, String surname, Address address, Crew crew) {
		
		technician.setName(name);
		technician.setSurname(surname);
		technician.setAddress(address);
		technician.setCrew(crew);
	}

	public void modifyCrew(Crew crew, Address address) {
		
		crew.setAddress(address);
	}
	
	public void removeTechnicianFromCrew(Technician technician, Crew crew) {
		
		technician.setCrew(null);
	}
	
	@SuppressWarnings("unchecked")
	public void deleteTechnician(Technician technician, String telNo) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Technician> resultTech = null;
		Query queryTech = em.createQuery("select t	from Technician t where telNo =:telNo");
		queryTech.setParameter("telNo", telNo);
		resultTech = queryTech.getResultList();
		technician = resultTech.get(0);
		em.remove(technician);
		tx.commit();
		em.close();
	}

	
	@SuppressWarnings("unchecked")
	public void deleteCrew(Crew crew, String crewName) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Crew> resultCrew = null;
		Query queryCrew = em.createQuery("select c	from Crew t where crewName =:crewName");
		queryCrew.setParameter("crewName", crewName);
		resultCrew = queryCrew.getResultList();
		crew = resultCrew.get(0);
		em.remove(crew);
		tx.commit();
		em.close();
	}
	public Crew save(Crew c) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if (c.getId() != null) {
			// beware, always use the result of merge
			c = em.merge(c);
		} else {
			em.persist(c);
		}
		tx.commit();
		return c;

	}

}
