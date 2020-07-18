package gr.aueb.mscis.gas.resource;

import gr.aueb.mscis.gas.model.Tool;
import gr.aueb.mscis.gas.service.ToolService;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@Path("tool")
public class ToolResource extends AbstractResource {
	@Context
	UriInfo uriInfo;

	@DELETE
	@Path("{toolId:[0-9]*}")
	// path = tool/delete
	public Response deleteTool(@PathParam("toolId") int toolId){
		
		EntityManager em = getEntityManager();
		ToolService service = new ToolService(em);
		boolean result = service.deleteTool(toolId);
		
		if (!result) {
			em.close();
			return Response.status(Status.NOT_FOUND).build();
		}

		em.close();
		return Response.ok().build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTool(ToolInfo toolInfo) {

		EntityManager em = getEntityManager();
		
		Tool newtool = toolInfo.getTool(em);
		ToolService service = new ToolService(em);
		newtool=service.save(newtool);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		URI newToolUri = ub.path(Integer.toString(newtool.getId())).build();
		em.close();

		return Response.created(newToolUri).build();
	}
	
	@PUT
	@Path("{toolId:[0-9]*}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTool(ToolInfo toolInfo) {

		EntityManager em = getEntityManager();

		Tool tool = toolInfo.getTool(em);
		ToolService service = new ToolService(em);
		tool = service.save(tool);

		em.close();
		return Response.ok().build();
	}
	
}