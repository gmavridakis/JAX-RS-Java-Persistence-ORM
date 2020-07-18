package gr.aueb.mscis.gas.service;
import java.util.List;

import javax.persistence.EntityManager;
import gr.aueb.mscis.gas.model.Supervisor;

import gr.aueb.mscis.gas.model.Supervisor;
import gr.aueb.mscis.gas.persistence.Initializer;
import gr.aueb.mscis.gas.persistence.JPAUtil;
import gr.aueb.mscis.gas.service.SupervisorService;


import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;

public class SupervisorService {

	

		private EntityManager em;  // δημιουργία μεταβλητής Entity Manager

		public SupervisorService(EntityManager em) {
			this.em = em;
		}

		@SuppressWarnings("unchecked")  // λέμε στον compiler Να μην μας εμφανίζει warning μηνύματα
		
		public String findSupervisorByLastName(String eponumo) {
			
			String sur="";
			List<Supervisor> results = null;
					
			Query query = em.createQuery("select b from User b where type='S' AND surname like :titleCrit");
			query.setParameter("titleCrit", eponumo);
			results = query.getResultList(); 	
			for (Supervisor supervisor: results) {
				sur=supervisor.getSurname();
			}
			return sur;
		}
		
		
		
		
		
		
	
		
		
		
		
		
	
		public Supervisor findSupervisorById(int id) {
			return em.find(Supervisor.class, id);
		}

		public boolean saveOrUpdateSupervisor(Supervisor b) {

			if (b != null) {
				em.merge(b);
				return true;
			}

			return false;
		}

		/**
		 * Creates a new supervisor instance in the database
		 * @param b
		 * @return
		 */
		public boolean createSupervisor(Supervisor b) {

			if (b != null) {
				em.persist(b);
				return true;
			}

			return false;
		}
		
		public boolean deleteSupervisor(Supervisor b) {

			if (b != null) {
				em.remove(b);
				return true;
			}

			return false;
		}

		public List<Supervisor> findAllSupervisors() {
			List<Supervisor> results = null;

			results = em.createQuery("select b from User b where type='S'")
					.getResultList();

			return results;
		}

	
}
