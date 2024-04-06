package rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.YearOfStudy;
import service.YearOfStudyService;

@Path("/year-of-study")
public class YearOfStudyRest {
	
	@Inject
	private YearOfStudyService yoss;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response createYearOfStudy(YearOfStudy yos) {
		return Response.ok(yoss.createYearOfStudy(yos)).build();
	}
}
