package rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Associate;
import service.AssociateService;

@Path("/associate")
public class AssociateRest {
	
	@Inject
	private AssociateService as;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response createAssociate(Associate a) {
		return Response.ok(as.createAssociate(a)).build();
	}
}
