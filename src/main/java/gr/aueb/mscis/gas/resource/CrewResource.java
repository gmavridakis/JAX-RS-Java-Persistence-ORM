package gr.aueb.mscis.gas.resource;

import javax.persistence.*;
import gr.aueb.mscis.gas.model.Address;
import gr.aueb.mscis.gas.model.Crew;
import gr.aueb.mscis.gas.model.Technician;
import gr.aueb.mscis.gas.model.Tool;
import gr.aueb.mscis.gas.service.CrewService;
import gr.aueb.mscis.gas.service.ToolService;
import gr.aueb.mscis.gas.resource.CrewInfo;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;


@Path("crew")
public class CrewResource extends AbstractResource {
	
		@Context
		UriInfo uriInfo;
		
		@GET
		@Path("search/technician")
		public List<Technician> listAllTechnicians() {
			EntityManager em = getEntityManager();
			CrewService service = new CrewService(em);
			List<Technician> technicians = service.findAllTechnicians();
			em.close();
			return technicians;

		}
		
		@POST
		@Path("create/technician")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response createTechnician(CrewInfo crewinfo) {

			EntityManager em = getEntityManager();

			CrewService service = new CrewService(em);
			Address crewAddress = new Address("Evelpidwn","29","Athens","11362","Greece");
			Address technicianAddress = new Address("Evelpidwn","46","Athens","11362","Greece");
			Crew ghostbusters = new Crew("ghostbusters", crewAddress);
			
			Technician technician = service.createTechnician("Super","Mario",technicianAddress,"2108888888",ghostbusters);
			
			//dhmiourgia apokrishs 
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI newTechnicianUri = ub.path(Integer.toString(technician.getId())).build();
			
			em.close();
			
			return Response.created(newTechnicianUri).build();
		}

		
		
		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/createCrew")
		public Response createCrew(CrewInfo crewinfo) {

			EntityManager em = getEntityManager();

			Crew newcrew=crewinfo.getCrew(em);
			CrewService service = new CrewService(em);
			newcrew=service.save(newcrew);
			
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI newCrewUri = ub.path(Integer.toString(newcrew.getId())).build();
			
			em.close();
			
			return Response.created(newCrewUri).build();
		}

		@DELETE
		@Path("delete/crew")
		// path = crew/delete/crew
		public Response deleteCrew(){
			
			EntityManager em = getEntityManager();
			CrewService service = new CrewService(em);
			
			Address crewAddress = new Address("Evelpidwn","29","Athens","11362","Greece");
			Crew ghostbusters = new Crew("ghostbusters", crewAddress);
			
			service.deleteCrew(ghostbusters,"ghostbusters");
			
			em.close();
			return Response.ok().build();
		}
		
		@DELETE
		@Path("delete/technician")
		// path = crew/delete/crew
		public Response deleteTechnician(){
			
			EntityManager em = getEntityManager();
			
			CrewService service = new CrewService(em);
			Address technicianAddress = new Address("Evelpidwn","46","Athens","11362","Greece");
			Technician superMario = new Technician("Super", "Mario", technicianAddress, "2108888888", null);
			
			service.deleteTechnician(superMario, "2108888888");
			
			em.close();
			return Response.ok().build();
		}
		
		@POST
		@Path("modify/technician")
		public Response modifyTechnician(){

			EntityManager em = getEntityManager();
			CrewService service = new CrewService(em);
			
			//public void modifyTechnician(Technician technician, String name, String surname, Address address, Crew crew) {
			
			Address techAddress = new Address("Evelpidwn","46","Athens","11362","Greece");
			Technician superMario = new Technician("Super", "Mario", techAddress, "2108888888", null);
			Address theNewCrewAddress = new Address("Evelpidwn","15","Athens","11362","Greece");
			Crew ghostbusters = new Crew("ghostbusters", theNewCrewAddress);
			
			service.modifyTechnician(superMario, "Super" , "Mario", theNewCrewAddress , ghostbusters);
			em.close();
			return Response.ok().build();
		}
		
		
		@POST
		@Path("modify/crew")
		public Response modifyCrew(){

			EntityManager em = getEntityManager();
			CrewService service = new CrewService(em);
			
			Address crewAddress = new Address("Evelpidwn","29","Athens","11362","Greece");
			Address newcrewAddress = new Address("Evelpidwn","45","Athens","11362","Greece");
			Crew ghostbusters = new Crew("ghostbusters", crewAddress);
			
			service.modifyCrew(ghostbusters,newcrewAddress);
			em.close();
			return Response.ok().build();
		}
		
		
}
