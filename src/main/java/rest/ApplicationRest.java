package rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.*;
import service.ModelsService;

@Path("/schedule")
public class ApplicationRest {
	
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
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/semester")
	public Response createSemester(Semester s) {
		return Response.ok(m.createSemester(s)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/subject")
	public Response createSubject(Subject s) {
		return Response.ok(m.createSubject(s)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/classroom")
	public Response createClassRoom(Classroom c) {
		return Response.ok(m.createClassRoom(c)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/days-of-the-week")
	public Response createDaysOfTheWeek(DaysOfTheWeek d) {
		return Response.ok(m.createDaysOfTheWeek(d)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/lecture-hours")
	public Response createLectureHours(LectureHours l) {
		return Response.ok(m.createLectureHours(l)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/student-subject")
	public Response createStudentSubject(StudentSubject ss) {
		return Response.ok(m.createStudentSubject(ss)).build();
	}
}
