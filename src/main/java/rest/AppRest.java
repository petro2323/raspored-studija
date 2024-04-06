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
import service.AcademicTitleService;
import service.AssociateService;
import service.ProfessorService;
import service.StudentService;
import service.YearOfStudyService;

@Path("/schedule")
public class AppRest {
	
	@Inject
	private AcademicTitleService ats;
	private AssociateService as;
	private ProfessorService ps;
	private StudentService ss;
	private YearOfStudyService yoss;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/academic-title")
	public Response createAcademicTitle(AcademicTitle at) {
		return Response.ok(ats.createAcademicTitle(at)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/associate")
	public Response createAssociate(Associate a) {
		return Response.ok(as.createAssociate(a)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/professor")
	public Response createProfessor(Professor p) {
		return Response.ok(ps.createProfessor(p)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/student")
	public Response createStudent(Student s) {
		return Response.ok(ss.createStudent(s)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/year-of-study")
	public Response createYearOfStudy(YearOfStudy yos) {
		return Response.ok(yoss.createYearOfStudy(yos)).build();
	}
}
