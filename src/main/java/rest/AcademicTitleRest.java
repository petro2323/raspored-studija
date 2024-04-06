package rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.AcademicTitle;
import service.AcademicTitleService;

@Path("/academic-title")
public class AcademicTitleRest {
	
	@Inject
	private AcademicTitleService ats;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response createAcademicTitle(AcademicTitle at) {
		return Response.ok(ats.createAcademicTitle(at)).build();
	}
}