package rest;

import java.util.Date;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.*;
import service.IpClient;
import service.ModelsService;

@Path("/create")
public class CreateRest {

	@Inject
	private ModelsService m;

	@Inject
	@RestClient
	private IpClient ipclient;

	private IPLog getClientsIP(String action) {
		IPLog iplog = ipclient.GetIpAddress();
		iplog.setCreatedDate(new Date());
		iplog.setAction(action);

		return iplog;
	}

	@POST
	@Operation(summary = "Create an academic title", description = "Inserts the name of an academic title in the database for later usage.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/academic-title")
	public Response createAcademicTitle(@Valid AcademicTitle at) {
		at.setIplog(getClientsIP("created academic title " + at.getTitle_name()));
		return Response.ok(m.createAcademicTitle(at)).build();
	}

	@POST
	@Operation(summary = "Create an associate", description = "Inserts the first name, last name and date of birth of an associate into the database.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/associate")
	public Response createAssociate(@Valid Associate a) {
		a.setIplog(getClientsIP("created associate " + a.getFirst_name() + a.getLast_name()));
		return Response.ok(m.createAssociate(a)).build();
	}

	@POST
	@Operation(summary = "Create a professor", description = "Inserts the first name, last name, date of birth and academic title of a professor into the database.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/professor")
	public Response createProfessor(@Valid Professor p) {
		p.setIplog(getClientsIP("created professor " + p.getFirst_name() + " " + p.getLast_name()));
		return Response.ok(m.createProfessor(p)).build();
	}

	@POST
	@Operation(summary = "Create a student", description = "Inserts the first name, last name, date of birth, year of study and index number of a student into the database.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/student")
	public Response createStudent(@Valid Student s) {
		s.setIplog(getClientsIP(
				"created student " + s.getFirst_name() + " " + s.getLast_name() + " " + s.getIndex_number()));
		return Response.ok(m.createStudent(s)).build();
	}

	@POST
	@Operation(summary = "Create a study year", description = "Inserts the type of a study year in the database for later usage.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/year-of-study")
	public Response createYearOfStudy(@Valid YearOfStudy yos) {
		yos.setIplog(getClientsIP("created year of study " + yos.getType()));
		return Response.ok(m.createYearOfStudy(yos)).build();
	}

	@POST
	@Operation(summary = "Create a semester", description = "Inserts the roman number of a semester in the database for later usage.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/semester")
	public Response createSemester(@Valid Semester s) {
		s.setIplog(getClientsIP("created semester " + s.getRoman_number()));
		return Response.ok(m.createSemester(s)).build();
	}

	@POST
	@Operation(summary = "Create a subject", description = "Inserts the title, ects number, professor, associate (if the subject contains one) and semester of a subject in the database.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/subject")
	public Response createSubject(@Valid Subject s) {
		s.setIplog(getClientsIP("created subject " + s.getTitle()));
		return Response.ok(m.createSubject(s)).build();
	}

	@POST
	@Operation(summary = "Create a classroom", description = "Inserts the room number of a classroom in the database for later usage.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/classroom")
	public Response createClassRoom(@Valid Classroom c) {
		c.setIplog(getClientsIP("created classroom " + c.getRoom_number()));
		return Response.ok(m.createClassRoom(c)).build();
	}

	@POST
	@Operation(summary = "Create a day of the week", description = "Inserts a day of the week in the database for later usage.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/days-of-the-week")
	public Response createDaysOfTheWeek(@Valid DaysOfTheWeek d) {
		d.setIplog(getClientsIP("created day " + d.getDay_name()));
		return Response.ok(m.createDaysOfTheWeek(d)).build();
	}

	@POST
	@Operation(summary = "Create a lecture", description = "Inserts the subject, classroom, day and time in the database to create a lecture.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/lecture-hours")
	public Response createLectureHours(@Valid LectureHours l) {
		l.setIplog(getClientsIP("created lecture " + l.getSubject().getTitle() + " on "
				+ l.getLecture_day().getDay_name() + " in classroom " + l.getClassroom().getRoom_number()));
		return Response.ok(m.createLectureHours(l)).build();
	}

	@POST
	@Path("/student-subject")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createStudentSubject(@QueryParam("index_number") String index_number,
			@QueryParam("subject_title") String title) {
		m.addStudentToSubject(index_number, title);
		return Response.status(Response.Status.OK).entity("Student added to subject successfully").build();
	}
}