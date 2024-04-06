package rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.AcademicTitle;
import models.Associate;
import models.Professor;
import models.Student;
import models.YearOfStudy;
import service.ModelsService;

@Path("/schedule")
public class AppRest {
	
	@Inject
	private ModelsService m;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/academic-title")
	public Response createAcademicTitle(AcademicTitle at) {
		return Response.ok(m.createAcademicTitle(at)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/associate")
	public Response createAssociate(Associate a) {
		return Response.ok(m.createAssociate(a)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/professor")
	public Response createProfessor(Professor p) {
		return Response.ok(m.createProfessor(p)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/student")
	public Response createStudent(Student s) {
		return Response.ok(m.createStudent(s)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/year-of-study")
	public Response createYearOfStudy(YearOfStudy yos) {
		return Response.ok(m.createYearOfStudy(yos)).build();
	}
}
