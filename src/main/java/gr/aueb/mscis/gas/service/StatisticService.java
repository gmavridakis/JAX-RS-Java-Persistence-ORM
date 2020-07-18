package gr.aueb.mscis.gas.service;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import gr.aueb.mscis.gas.model.Job;
import gr.aueb.mscis.gas.model.Address;
import gr.aueb.mscis.gas.model.Crew;
import javax.persistence.Query;

import org.hibernate.Session;

import org.junit.Assert;


public class StatisticService {

		private EntityManager em;  // δημιουργία μεταβλητής Entity Manager
		
		public StatisticService(EntityManager em) {
			this.em = em;
		}
		
		@SuppressWarnings("unchecked")
		public List<Job> findAllCrewJobs(String crewname)
		{
			
			List<Job> results = null;
			
			
			if(crewname!=null)
			{
				results = em.createQuery("select b from Job b where finaldate=null").getResultList();
				/*results = em.
						createQuery(
								"select b from Job b where crew= :crewname ")
						.setParameter("crewname", crewname).getResultList();
				*/
			}
			return results;
		}
		
		public float AverageCostStatistic(Crew crew,List<Job> alljobs)
		{
			int count=0;
			float sum=0;
			
			if(!alljobs.isEmpty())
			{
				for (Job job: alljobs){
					count++;
					sum+=job.getJobCost();
				}
				return sum/count;
			}
			return 0;
		}
		
}