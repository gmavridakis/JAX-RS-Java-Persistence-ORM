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
import gr.aueb.mscis.gas.service.JobAssignService;
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

@Path("assignjob")
public class JobAssignResource {
	private EntityManager em;  // δημιουργία μεταβλητής Entity Manager

	public JobAssignResource(EntityManager em) {
		this.em = em;
	}
	
	@Path("supervisor")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response assignjobtoSupervisor(@FormParam("jobname") String jobname, @FormParam("supervisorname") String supervisorname){

		
		if ((jobname == null)||(supervisorname==null)){
			return Response.status(Response.Status.CONFLICT).build();
		}
		
		JobAssignService jobassignservice=new JobAssignService(em);
		
		
		jobassignservice.assignSupervisorToJob(jobassignservice.getSupervisorFromName(supervisorname), jobassignservice.getJobFromName(jobname));
		return Response.ok().build();
	}
	
	@PUT
	@Path("crew")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response assignjobtoCrew(@FormParam("jobname") String jobname, @FormParam("crewname") String crewname){

		
		if ((jobname == null)||(crewname==null)){
			return Response.status(Response.Status.CONFLICT).build();
		}
		
		JobAssignService jobassignservice=new JobAssignService(em);
		
		
		jobassignservice.assignCrewToJob(jobassignservice.getCrewFromName(crewname), jobassignservice.getJobFromName(jobname));
		return Response.ok().build();
	}
	
	@PUT
	@Path("tool")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response assigntooltojob(@FormParam("jobname") String jobname, @FormParam("toolname") String toolname){

		
		if ((jobname == null)||(toolname==null)){
			return Response.status(Response.Status.CONFLICT).build();
		}
		
		JobAssignService jobassignservice=new JobAssignService(em);
		
		
		jobassignservice.assignToolToJob(jobassignservice.getToolFromName(toolname), jobassignservice.getJobFromName(jobname));
		return Response.ok().build();
	}
	
}