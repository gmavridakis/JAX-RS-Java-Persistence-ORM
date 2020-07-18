package gr.aueb.mscis.gas.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import gr.aueb.mscis.gas.model.Address;
import gr.aueb.mscis.gas.model.Technician;
import gr.aueb.mscis.gas.service.CrewService;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@Path("technician")
public class TechnicianResource extends AbstractResource{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("fetch")
	public TechnicianInfo aTechnician() {
		Address technicianAddress = new Address();
        technicianAddress.setStreet("Troias");
		technicianAddress.setNumber("2");
		technicianAddress.setCity("Athens");
		technicianAddress.setAreaCode("11362");
		technicianAddress.setCountry("Greece");
		TechnicianInfo superMario = new TechnicianInfo("Super", "Mario", technicianAddress, "2108888888", null);
		return superMario;

	}
	
	@GET
	@Path("fetchall")
	@Produces(MediaType.APPLICATION_XML)
	public List<TechnicianInfo> listAllTechnicianss() {

		Address technicianAddress = new Address();
        technicianAddress.setStreet("Troias");
		technicianAddress.setNumber("2");
		technicianAddress.setCity("Athens");
		technicianAddress.setAreaCode("11362");
		technicianAddress.setCountry("Greece");
		TechnicianInfo technician1 = new TechnicianInfo("Super", "Mario", technicianAddress, "2108888888", null);
		TechnicianInfo technician2 = new TechnicianInfo("SuperM", "Bros", technicianAddress, "2107777777", null);
		List<TechnicianInfo> allTechnicians = new ArrayList<TechnicianInfo>();
		allTechnicians.add(technician1);
		allTechnicians.add(technician2);
		
		return allTechnicians;
		
	}
	
	@GET
	@Path("something")
	@Produces("application/text")
	public String something() {

		return "test";
		
	}
	
	
}
