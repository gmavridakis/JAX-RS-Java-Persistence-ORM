package gr.aueb.mscis.gas.resource;
import javax.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import gr.aueb.mscis.gas.model.Address;
import gr.aueb.mscis.gas.model.Crew;
import gr.aueb.mscis.gas.model.Job;
import gr.aueb.mscis.gas.model.Technician;
import gr.aueb.mscis.gas.model.Tool;


import gr.aueb.mscis.gas.persistence.JPAUtil;
import gr.aueb.mscis.gas.service.JobCompleteService;
import gr.aueb.mscis.gas.service.ToolService;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 * CRUD operations and other functionality related to crews
 * 
 * @author AUEB.mscispt.team1
 *
 */

@Path("jobcomplete")
public class JobCompleteResource {
	private EntityManager em;  // δημιουργία μεταβλητής Entity Manager

	public JobCompleteResource(EntityManager em) {
		this.em = em;
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response completeJob(@FormParam("jobname") String jobname, @FormParam("year") int year, @FormParam("month") int month, @FormParam("day") int day){

		
		if ((jobname == null)||(year<0)||(month>12)||(day>31)){
			return Response.status(Response.Status.CONFLICT).build();
		}
		
		JobCompleteService jobcompleteservice=new JobCompleteService(em);
		
		GregorianCalendar finaldate=new GregorianCalendar();

		finaldate.set(GregorianCalendar.YEAR, year);
		finaldate.set(GregorianCalendar.MONTH, month);
		finaldate.set(GregorianCalendar.DATE, day);
		
		boolean apotelesma=jobcompleteservice.completeJob(jobcompleteservice.getJobFromName(jobname), finaldate);
		if(apotelesma==true){
		return Response.ok().build();
		}
		else return Response.status(Response.Status.CONFLICT).build();
	}
	
	@GET
	@Path("done")
	@Produces("application/text")
	public String completeJobDone(String jobname, int year, int month, int day){

		
		if ((jobname == null)||(year<0)||(month>12)||(day>31)){
			return "error";
		}
		
		JobCompleteService jobcompleteservice=new JobCompleteService(em);
		
		GregorianCalendar finaldate=new GregorianCalendar();

		finaldate.set(GregorianCalendar.YEAR, year);
		finaldate.set(GregorianCalendar.MONTH, month);
		finaldate.set(GregorianCalendar.DATE, day);
		
		boolean apotelesma=jobcompleteservice.completeJob(jobcompleteservice.getJobFromName(jobname), finaldate);
		if(apotelesma==true){
			return "Job complete";
		}
			else return "error";
	}
	
}