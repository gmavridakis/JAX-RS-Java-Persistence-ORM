package gr.aueb.mscis.gas.resource;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import gr.aueb.mscis.gas.model.Job;
import gr.aueb.mscis.gas.model.Tool;
import gr.aueb.mscis.gas.service.JobCompleteService;

@XmlRootElement
public class JobInfo {
	
		private String jobname;
		
		private GregorianCalendar finaldate=new GregorianCalendar();
		private EntityManager em;

		public JobInfo(EntityManager em) {

		}
		public Job completeJob(Job job, GregorianCalendar finaldate){
			
			
				JobCompleteService jobcompleteservice=new JobCompleteService(em);
				
				boolean apotelesma=jobcompleteservice.completeJob(job, finaldate);
				
		
		return job;

		}
}