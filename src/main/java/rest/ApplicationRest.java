package rest;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import dto.*;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.*;
import service.IpClient;
import service.ModelsService;

@Path("/schedule")
public class ApplicationRest {

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
	@Path("/create/academic-title")
	public Response createAcademicTitle(@Valid AcademicTitle at) {
		at.setIplog(getClientsIP("created academic title " + at.getTitle_name()));
		return Response.ok(m.createAcademicTitle(at)).build();
	}

	@POST
	@Operation(summary = "Create an associate", description = "Inserts the first name, last name and date of birth of an associate into the database.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/associate")
	public Response createAssociate(@Valid Associate a) {
		a.setIplog(getClientsIP("created associate " + a.getFirst_name() + a.getLast_name()));
		return Response.ok(m.createAssociate(a)).build();
	}

	@POST
	@Operation(summary = "Create a professor", description = "Inserts the first name, last name, date of birth and academic title of a professor into the database.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/professor")
	public Response createProfessor(@Valid Professor p) {
		p.setIplog(getClientsIP("created professor " + p.getFirst_name() + " " + p.getLast_name()));
		return Response.ok(m.createProfessor(p)).build();
	}

	@POST
	@Operation(summary = "Create a student", description = "Inserts the first name, last name, date of birth, year of study and index number of a student into the database.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/student")
	public Response createStudent(@Valid Student s) {
		s.setIplog(getClientsIP(
				"created student " + s.getFirst_name() + " " + s.getLast_name() + " " + s.getIndex_number()));
		return Response.ok(m.createStudent(s)).build();
	}

	@POST
	@Operation(summary = "Create a study year", description = "Inserts the type of a study year in the database for later usage.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/year-of-study")
	public Response createYearOfStudy(@Valid YearOfStudy yos) {
		yos.setIplog(getClientsIP("created year of study " + yos.getType()));
		return Response.ok(m.createYearOfStudy(yos)).build();
	}

	@POST
	@Operation(summary = "Create a semester", description = "Inserts the roman number of a semester in the database for later usage.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/semester")
	public Response createSemester(@Valid Semester s) {
		s.setIplog(getClientsIP("created semester " + s.getRoman_number()));
		return Response.ok(m.createSemester(s)).build();
	}

	@POST
	@Operation(summary = "Create a subject", description = "Inserts the title, ects number, professor, associate (if the subject contains one) and semester of a subject in the database.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/subject")
	public Response createSubject(@Valid Subject s) {
		s.setIplog(getClientsIP("created subject " + s.getTitle()));
		return Response.ok(m.createSubject(s)).build();
	}

	@POST
	@Operation(summary = "Create a classroom", description = "Inserts the room number of a classroom in the database for later usage.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/classroom")
	public Response createClassRoom(@Valid Classroom c) {
		c.setIplog(getClientsIP("created classroom " + c.getRoom_number()));
		return Response.ok(m.createClassRoom(c)).build();
	}

	@POST
	@Operation(summary = "Create a day of the week", description = "Inserts a day of the week in the database for later usage.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/days-of-the-week")
	public Response createDaysOfTheWeek(@Valid DaysOfTheWeek d) {
		d.setIplog(getClientsIP("created day " + d.getDay_name()));
		return Response.ok(m.createDaysOfTheWeek(d)).build();
	}

	@POST
	@Operation(summary = "Create a lecture", description = "Inserts the subject, classroom, day and time in the database to create a lecture.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create/lecture-hours")
	public Response createLectureHours(@Valid LectureHours l) {
		l.setIplog(getClientsIP("created lecture " + l.getSubject().getTitle() + " on "
				+ l.getLecture_day().getDay_name() + " in classroom " + l.getClassroom().getRoom_number()));
		return Response.ok(m.createLectureHours(l)).build();
	}

	@POST
	@Path("/create/student-subject")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createStudentSubject(@QueryParam("student_id") Long student_id,
			@QueryParam("subject_id") Long subject_id) {
		m.addStudentToSubject(student_id, subject_id);
		return Response.status(Response.Status.OK).entity("Student added to subject successfully").build();
	}

	@DELETE
	@Path("/create/student-subject")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteStudentSubject(@QueryParam("student_id") Long student_id,
			@QueryParam("subject_id") Long subject_id) {
		m.removeStudentFromSubject(student_id, subject_id);
		return Response.status(Response.Status.OK).entity("Student removed from subject successfully").build();
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
	@Operation(summary = "View all study years", description = "It lets the user see all study years the institution contains.")
	@Path("/view/all-years-of-study")
	public List<YearOfStudyDTO> getAllStudyYears() {
		return m.getAllStudyYears();
	}

	@GET
	@Operation(summary = "Subject schedule for the semester", description = "It lets the user see the courses schedule for the required <u>semester</u>. <br>It also lets the user see which courses are being held in the required <u>classroom</u>."
			+ "<br><br>If the room_number and semester inputs are <u>blank</u>, the function will by default <u>return</u> all courses for every semester."
			+ "<br>If only <u>one</u> of the inputs is specified, the function will only <u>return</u> information based on that input."
			+ "<br>If <u>both</u> inputs are specified, the function will <u>return</u> information based on the inputs."
			+ "<br><br><b>Warning</b>: The function supports case-insensitive matching, but it requires for the semester to be a roman number and for the room number to be valid; otherwise, it may fail to find the desired result.")
	@Path("/view/subject-schedule")
	public List<LectureDTO> getLecturesFromSemester(@QueryParam("semester") String semester,
			@QueryParam("room_number") String room_number) {
		return m.getLecturesFromSemester((semester == null) ? null : semester.toUpperCase(),
				(room_number == null) ? null : room_number.toUpperCase());
	}

	@GET
	@Operation(summary = "Professors by academic title", description = "It lets the user see which professors contain the required academic title."
			+ "<br><br>The user can search for all academic titles by using the <u>/view/all-academic-titles</u> function."
			+ "<br><br>If the title_name is <u>blank</u>, the function will by default <u>return</u> all professors from the institution."
			+ "<br><br><b>Warning</b>: The function supports case-insensitive matching, but it requires the academic title to be in the correct order; otherwise, it may fail to find the desired result.")
	@Path("/view/professor-by-academic-title")
	public List<ProfessorDTO> getProfessorsByAcademicTitle(@QueryParam("title_name") String title_name) {
		return m.getProfessorsByAcademicTitle((title_name == null) ? null : title_name.toLowerCase());
	}

	@GET
	@Operation(summary = "Students enrolled in the course", description = "It lets the user see the <u>number</u> and <u>list</u> of students that are enrolled in the course that is lecturing the required subject."
			+ "<br><br>The user can search for all subjects by using the <u>/view/all-subjects</u> function."
			+ "<br><br><b>Warning</b>: The function requires the title of the subject to be written in the correct order with matching the case of the letters; otherwise, it may fail to find the desired result.")
	@Path("/view/students-on-subject")
	public StudentsWithCount getStudentsBySubject(@QueryParam("subject") String subject) {
		List<StudentDTO> students = m.getStudentsBySubject(subject);
		int numberOfStudents = students.size();

		return new StudentsWithCount(students, numberOfStudents);
	}

	@GET
	@Operation(summary = "Time lecture of a subject", description = "It lets the user see a list of subjects that are being lectured before or after a specific time."
			+ "<br><br>The condition parameter only accepts values <u>Before</u> and <u>After</u>. The time parameter only accepts integer values."
			+ "<br><i>Examples</i>: Before 12 or After 15"
			+ "<br><br><u>The condition parameter supports case-insensitive matching.</u>")
	@Path("/view/subject-by-time")
	public List<ShiftDTO> getSubjectsByShift(@QueryParam("condition") String condition, @QueryParam("time") int time) {
		return m.getSubjectsByShift(condition, time);
	}

	@GET
	@Operation(summary = "Students by study year", description = "It lets the user see the <u>number</u> and <u>list</u> of students by the required study year."
			+ "<br><br>The user can search for all study years by using the <u>/view/all-years-of-study</u> function."
			+ "<br><br><b>Warning</b>: The function supports case-insensitive matching, but it requires the study year to be in the correct order; otherwise, it may fail to find the desired result.")
	@Path("/view/student-by-study-year")
	public StudentsWithCount getStudentsByYearOfStudy(@QueryParam("year_of_study") String year_of_study) {
		List<StudentDTO> students = m.getStudentsByYearOfStudy(year_of_study);
		int numberOfStudents = students.size();

		return new StudentsWithCount(students, numberOfStudents);
	}

	@PATCH
	@Operation(summary = "Update subject data", description = "It lets the user update the data of a subject."
			+ "<br>The function <b>requires</b> the name of the subject where changes are being made; otherwise it will return <i>false</i> which indicates that the request has been interrupted."
			+ "<br>The user can choose the variables that need to be changed which means that other inputs (<b>EXCEPT FOR old_subject_title</b>) can be left <u>blank</u>; in other words the function creates a query based on the user's conditions."
			+ "<br><br><b>Warning</b>: The function supports case-insensitive matching, but it requires for the old subject title to be in the correct order; otherwise, it may fail to send the request.")
	@Path("/patch/subject")
	public String updateSubject(@QueryParam("ects_number") int ects, @QueryParam("associate") String associate,
			@QueryParam("professor") String professor, @QueryParam("semester") String semester,
			@QueryParam("new_subject_title") String newTitle, @QueryParam("old_subject_title") String oldTitle) {

		return m.updateSubject(ects, associate, professor, semester, newTitle, oldTitle);
	}

	@PATCH
	@Operation(summary = "Update lecture data", description = "It lets the user update the data of a lecture."
			+ "<br>The function <b>requires</b> the name of the subject where changes are being made; otherwise it will return <i>false</i> which indicates that the request has been interrupted."
			+ "<br>The user can choose the variables that need to be changed which means that other inputs (<b>EXCEPT FOR subject</b>) can be left <u>blank</u>; in other words the function creates a query based on the user's conditions."
			+ "<br><br><b>Warning</b>: The function supports case-insensitive matching, but it requires for the subject title to be in the correct order; otherwise it may fail to send the request.")
	@Path("/patch/lecture")
	public boolean updateLecture(@QueryParam("time") LocalTime time, @QueryParam("classroom") String classroom,
			@QueryParam("day") String day, @QueryParam("subject") String subject) {

		return m.updateLecture(time, classroom, day, subject);
	}

	@PATCH
	@Path("/patch/student")
	public boolean updateStudent(@QueryParam("first_name") String first_name, @QueryParam("last_name") String last_name,
			@QueryParam("year_of_study") String year_of_study, @QueryParam("index_number") String index_number) {
		
		return m.updateStudent(first_name, last_name, year_of_study, index_number);
	}
}
