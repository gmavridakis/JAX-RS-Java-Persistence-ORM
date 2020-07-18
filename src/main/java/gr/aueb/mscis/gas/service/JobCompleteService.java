package gr.aueb.mscis.gas.service;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import gr.aueb.mscis.gas.model.Job;
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

public class JobCompleteService {

	

		private EntityManager em;  // δημιουργία μεταβλητής Entity Manager

		public JobCompleteService(EntityManager em) {
			this.em = em;
		}

		@SuppressWarnings("unchecked")
		public List<Job> findAllPendingJobs(Supervisor supervisor) {
			
			List<Job> results = null;

			Query query = em.createQuery("select b from Job b where supervisor= :supervisor AND finaldate=null");
			query.setParameter("supervisor", supervisor);
			results = query.getResultList(); 	
			return results;
		}

		@SuppressWarnings("unchecked")
		public List<Job> findAllCompletedJobs(Supervisor supervisor) {
			List<Job> results = null;

			Query query = em.createQuery("select b from Job b where supervisor= :supervisor AND finaldate!=null");
			query.setParameter("supervisor", supervisor);
			results = query.getResultList(); 	
			return results;
		}
		
		@SuppressWarnings("unchecked")
		public boolean completeJob(Job job, GregorianCalendar finaldate) {
			Job jobvashs;
			List<Job> results = null;

			Query query = em.createQuery("select b from Job b where jobid= :jobid");
			query.setParameter("jobid", job.getJobid());
			results = query.getResultList(); 	
			jobvashs=results.get(0);
			
			if ((jobvashs.getFinalDate()!=null)||(jobvashs.getFinalStart()==null)) {
				return false;
			}
			else {
				jobvashs.setFinalDate(finaldate);
				EntityTransaction tx = em.getTransaction();
				tx.begin();
				em.persist(jobvashs);
				tx.commit();
				return true;	
			}
		}
		
		@SuppressWarnings("unchecked")
		public boolean setFinalStart(Job job, GregorianCalendar finalstart) {
			Job jobvashs2;
			List<Job> results = null;

			Query query = em.createQuery("select b from Job b where jobid= :jobid");
			query.setParameter("jobid", job.getJobid());
			results = query.getResultList(); 	
			jobvashs2=results.get(0);
			
			if (jobvashs2.getFinalStart()!=null) {
				return false;
			}
			else {
				jobvashs2.setFinalStart(finalstart);
				EntityTransaction tx = em.getTransaction();
				tx.begin();
				em.persist(jobvashs2);
				tx.commit();
				return true;	
			}
		}
		
		@SuppressWarnings("unchecked")
		public void setJobCost(Job job, double amount) {
			Job jobvashs3;
			List<Job> results = null;

			Query query = em.createQuery("select b from Job b where jobid= :jobid");
			query.setParameter("jobid", job.getJobid());
			results = query.getResultList(); 	
			jobvashs3=results.get(0);
			
			jobvashs3.setJobCost(amount);

			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(jobvashs3);
			tx.commit();

		}
		
		@SuppressWarnings("unchecked")
		public void setJobnameCost(String jobname, double amount) {
			Job jobvashs3;
			List<Job> results = null;

			Query query = em.createQuery("select b from Job b where jobname= :jobname");
			query.setParameter("jobname", jobname);
			results = query.getResultList(); 	
			jobvashs3=results.get(0);
			
			jobvashs3.setJobCost(amount);

			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(jobvashs3);
			tx.commit();

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
		

}