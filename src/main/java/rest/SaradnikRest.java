package rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Saradnik;
import service.SaradnikService;

@Path("/saradnik")
public class SaradnikRest {
	
	@Inject
	private SaradnikService saradnikservice;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/napravi")
	public Response createStudent(Saradnik saradnik) {
		return Response.ok(saradnikservice.createAssociate(saradnik)).build();
	}
}
