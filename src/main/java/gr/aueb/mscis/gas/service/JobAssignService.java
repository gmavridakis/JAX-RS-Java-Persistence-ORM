package gr.aueb.mscis.gas.service;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import gr.aueb.mscis.gas.model.Crew;
import gr.aueb.mscis.gas.model.Job;
import gr.aueb.mscis.gas.model.Request;
import gr.aueb.mscis.gas.model.Supervisor;
import gr.aueb.mscis.gas.model.Tool;
import gr.aueb.mscis.gas.model.Supervisor;
import gr.aueb.mscis.gas.persistence.Initializer;
import gr.aueb.mscis.gas.persistence.JPAUtil;
import gr.aueb.mscis.gas.service.SupervisorService;


import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public class JobAssignService {

	

		private EntityManager em;  // δημιουργία μεταβλητής Entity Manager

		public JobAssignService(EntityManager em) {
			this.em = em;
		}
		//fere ola ta etoimata
		@SuppressWarnings({ "unchecked", "null" })
		public List<Request> findAllRequest() {
			
			List<Request> requests = null;
			List<Request> requestsEpistrofi = null;
			Query query = em.createQuery("select b from Request b where job=null");
			requests = query.getResultList();
			int requestsSize=requests.size();
			
			List<Job> jobs = null;


			return requests;
		}
		
		
		
		
		@SuppressWarnings({ "unchecked", "null" })
		public List<Tool> findAllTools() {
			
			List<Job> olatajobs = null;
			List<Tool> olatatools = null;
			List<Tool> dia8esimaTools = null;
			Query query = em.createQuery("select b from Job b where finaldate=null");
			olatajobs = query.getResultList();
			
			Query query1 = em.createQuery("select t from Tool t");
			olatatools = query1.getResultList();
			dia8esimaTools = query1.getResultList();
			for (Tool tool : olatatools){
				int metr=1;
				for (Job job: olatajobs){
						if((job.getTools().contains(tool))&&metr==1){
							dia8esimaTools.remove(tool);
							metr=0;
						}
				}
			
			}
			return dia8esimaTools;
		}
		
		
		
		
		
		public boolean assignRequestToJob(Request request, Job job){
			if(request.getJob()!=null)
				return false;
			else {
				request.setJob(job);
				EntityTransaction tx = em.getTransaction();
				tx.begin();
				em.persist(request);
				tx.commit();
				return true;
			}
		}

		@SuppressWarnings("unchecked")
		public boolean assignCrewToJob(Crew crew, Job job){
			if(job.getCrew()!=null)
				return false;
			else {
				List<Job> jobs = null;
				Query query = em.createQuery("select b from Job b where finaldate=null");
				jobs=query.getResultList();
				int elegxos=0;
				for (Job joberwthmatos: jobs){
				if(joberwthmatos.getCrew()==crew)
						elegxos=1;
				}
				if (elegxos==1){
					return false;
				}
				else{
				job.setCrew(crew);
				EntityTransaction tx = em.getTransaction();
				tx.begin();
				em.persist(job);
				tx.commit();
				return true;
			}
		}
	}
		
		
		
		@SuppressWarnings("unchecked")
		public boolean assignToolToJob(Tool tool, Job job){

			List<Job> jobs = null;
			Query query = em.createQuery("select b from Job b where finaldate=null");
			jobs=query.getResultList();
			int elegxos=0;
			for (Job joberwthmatos: jobs){
				if(joberwthmatos.getTools().contains(tool))
					elegxos=1;
			}
			if (elegxos==1){
					return false;
			}
			else{
				job.addTool(tool);
				EntityTransaction tx = em.getTransaction();
				tx.begin();
				em.persist(job);
				tx.commit();
				
				if(job.getTools().contains(tool)){
				return true;}
				else return false;
			}
		}


		@SuppressWarnings("unchecked")
		public boolean assignSupervisorToJob(Supervisor supervisor, Job job){

			List<Job> jobs = null;
			Query query = em.createQuery("select b from Job b where finaldate=null");
			jobs=query.getResultList();
			int elegxos=0;
			for (Job joberwthmatos: jobs){
				if(joberwthmatos.getSupervisor()!=null)
					return false;
			
				else{
					job.setSupervisor(supervisor);
					EntityTransaction tx = em.getTransaction();
					tx.begin();
					em.persist(job);
					tx.commit();
					return true;
				}
			}
			return false;
			
		}
		
		@SuppressWarnings("unchecked")
		public Job getJobFromName(String jobname) {
			Job job;
			List<Job> results = null;

			Query query = em.createQuery("select b from Job b where jobname= :jobname");
			query.setParameter("jobname", jobname);
			results = query.getResultList(); 	
			job=results.get(0);

			return job;

		}

		@SuppressWarnings("unchecked")
		public Supervisor getSupervisorFromName(String surname) {
			Supervisor supervisor;
			List<Supervisor> results = null;

			Query query = em.createQuery("select b from User b where surname= :surname");
			query.setParameter("surname", surname);
			results = query.getResultList(); 	
			supervisor=results.get(0);

			return supervisor;

		}	
		
		@SuppressWarnings("unchecked")
		public Crew getCrewFromName(String crewname) {
			Crew crew;
			List<Crew> results = null;

			Query query = em.createQuery("select b from Crew b where crewName= :crewname");
			query.setParameter("crewname", crewname);
			results = query.getResultList(); 	
			crew=results.get(0);

			return crew;

		}	
		
		@SuppressWarnings("unchecked")
		public Tool getToolFromName(String toolname) {
			Tool tool;
			List<Tool> results = null;

			Query query = em.createQuery("select b from Tool b where name= :toolname");
			query.setParameter("toolname", toolname);
			results = query.getResultList(); 	
			tool=results.get(0);

			return tool;

		}

}
