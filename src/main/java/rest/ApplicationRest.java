package rest;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;

import dto.*;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.*;
import service.ModelsService;

@Path("/schedule")
public class ApplicationRest {

	@Inject
	private ModelsService m;

	@POST
	@Operation(summary = "Create an academic title", description = "Inserts the name of an academic title in the database for later usage.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/academic-title")
	public Response createAcademicTitle(@Valid AcademicTitle at) {
		return Response.ok(m.createAcademicTitle(at)).build();
	}

	@POST
	@Operation(summary = "Create an associate", description = "Inserts the first name, last name and date of birth of an associate into the database.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/associate")
	public Response createAssociate(@Valid Associate a) {
		return Response.ok(m.createAssociate(a)).build();
	}

	@POST
	@Operation(summary = "Create a professor", description = "Inserts the first name, last name, date of birth and academic title of a professor into the database.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/professor")
	public Response createProfessor(@Valid Professor p) {
		return Response.ok(m.createProfessor(p)).build();
	}

	@POST
	@Operation(summary = "Create a student", description = "Inserts the first name, last name, date of birth, year of study and index number of a student into the database.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/student")
	public Response createStudent(@Valid Student s) {
		return Response.ok(m.createStudent(s)).build();
	}

	@POST
	@Operation(summary = "Create a study year", description = "Inserts the type of a study year in the database for later usage.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/year-of-study")
	public Response createYearOfStudy(@Valid YearOfStudy yos) {
		return Response.ok(m.createYearOfStudy(yos)).build();
	}

	@POST
	@Operation(summary = "Create a semester", description = "Inserts the roman number of a semester in the database for later usage.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/semester")
	public Response createSemester(@Valid Semester s) {
		return Response.ok(m.createSemester(s)).build();
	}

	@POST
	@Operation(summary = "Create a subject", description = "Inserts the title, ects number, professor, associate (if the subject contains one) and semester of a subject in the database.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/subject")
	public Response createSubject(@Valid Subject s) {
		return Response.ok(m.createSubject(s)).build();
	}

	@POST
	@Operation(summary = "Create a classroom", description = "Inserts the room number of a classroom in the database for later usage.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/classroom")
	public Response createClassRoom(@Valid Classroom c) {
		return Response.ok(m.createClassRoom(c)).build();
	}

	@POST
	@Operation(summary = "Create a day of the week", description = "Inserts a day of the week in the database for later usage.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/days-of-the-week")
	public Response createDaysOfTheWeek(@Valid DaysOfTheWeek d) {
		return Response.ok(m.createDaysOfTheWeek(d)).build();
	}

	@POST
	@Operation(summary = "Create a lecture", description = "Inserts the subject, classroom, day and time in the database to create a lecture.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/lecture-hours")
	public Response createLectureHours(@Valid LectureHours l) {
		return Response.ok(m.createLectureHours(l)).build();
	}

	@POST
	@Operation(summary = "Create a student-subject relationship", description = "Inserts the student and subject in the database to create the MANY-TO-MANY relationship.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/student-subject")
	public Response createStudentSubject(@Valid StudentSubject ss) {
		return Response.ok(m.createStudentSubject(ss)).build();
	}
	
	@GET
	@Operation(summary = "View all academic titles", description = "It lets the user see all the academic titles the institution contains.")
	@Path("/view/all-academic-titles")
	public List<AcademicTitleDTO> getAllAcademicTitles() {
		return m.getAllAcademicTitles();
	}
	
	@GET
	@Operation(summary = "View all subjects", description = "It lets the user see all subjects the institution contains.")
	@Path("/view/all-subjects")
	public List<SubjectDTO> getAllSubjects() {
		return m.getAllSubjects();
	}
	
	@GET
	@Operation(summary = "Subject schedule for the semester", description = "It lets the user insert the semester number to view the schedule made for the subjects."
			+ "<br><br><b>Warning</b>: The function supports case-insensitive matching, but it requires for the input to be a roman number. For example: I, II, IV, V, etc.")
	@Path("/view/subject-schedule")
	public List<LectureDTO> getLecturesFromSemester(@QueryParam("semester") String semester) {
		return m.getLecturesFromSemester(semester.toUpperCase());
	}
	
	@GET
	@Operation(summary = "Professors by academic title", description = "It lets the user see which professors contain the required academic title." +
	"<br><br>The user can search for all academic titles by using the <u>/view/all-academic-titles</u> function." +
			"<br><br><b>Warning</b>: The function supports case-insensitive matching, but it requires the academic title to be in the correct order; otherwise, it may fail to find the desired result.")
	@Path("/view/professor-by-academic-title")
	public List<ProfessorDTO> getProfessorsByAcademicTitle(@QueryParam("title_name") String title_name) {
		return m.getProfessorsByAcademicTitle(title_name.toLowerCase());
	}
	
	@GET
	@Operation(summary = "Students enrolled in the course", description = "It lets the user see a list of students that are enrolled in the course that is lecturing the required subject."
	+ "<br><br>The user can search for all subjects by using the <u>/view/all-subjects</u> function." +
			"<br><br><b>Warning</b>: The function requires the title of the subject to be written in the correct order; otherwise, it may fail to find the desired result.")
	@Path("/view/students-on-subject")
	public List<StudentDTO> getStudentsBySubject(@QueryParam("subject") String subject) {
		return m.getStudentsBySubject(subject);
	}
}
