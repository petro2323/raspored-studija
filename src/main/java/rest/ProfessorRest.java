package rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Professor;
import service.ProfessorService;

@Path("/professor")
public class ProfessorRest {
	
	@Inject
	private ProfessorService ps;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response createProfessor(Professor p) {
		return Response.ok(ps.createProfessor(p)).build();
	}
}
