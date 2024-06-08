package rest;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;

import dto.AcademicTitleDTO;
import dto.LectureDTO;
import dto.ProfessorDTO;
import dto.ShiftDTO;
import dto.StudentsWithCount;
import dto.SubjectDTO;
import dto.YearOfStudyDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import service.ModelsService;

@Path("/view")
public class GetRest {
	
	@Inject
	private ModelsService m;
	
	@GET
	@Operation(summary = "View all academic titles", description = "It lets the user see all the academic titles the institution contains.")
	@Path("/all-academic-titles")
	public List<AcademicTitleDTO> getAllAcademicTitles() {
		return m.getAllAcademicTitles();
	}

	@GET
	@Operation(summary = "View all subjects", description = "It lets the user see all subjects the institution contains.")
	@Path("/all-subjects")
	public List<SubjectDTO> getAllSubjects() {
		return m.getAllSubjects();
	}

	@GET
	@Operation(summary = "View all study years", description = "It lets the user see all study years the institution contains.")
	@Path("/all-years-of-study")
	public List<YearOfStudyDTO> getAllStudyYears() {
		return m.getAllStudyYears();
	}

	@GET
	@Operation(summary = "Subject schedule for the semester", description = "It lets the user see the courses schedule for the required <u>semester</u>. <br>It also lets the user see which courses are being held in the required <u>classroom</u>."
			+ "<br><br>If the room_number and semester inputs are <u>blank</u>, the function will by default <u>return</u> all courses for every semester."
			+ "<br>If only <u>one</u> of the inputs is specified, the function will only <u>return</u> information based on that input."
			+ "<br>If <u>both</u> inputs are specified, the function will <u>return</u> information based on the inputs."
			+ "<br><br><b>Warning</b>: The function supports case-insensitive matching, but it requires for the semester to be a roman number and for the room number to be valid; otherwise, it may fail to find the desired result.")
	@Path("/subject-schedule")
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
	@Path("/professor-by-academic-title")
	public List<ProfessorDTO> getProfessorsByAcademicTitle(@QueryParam("title_name") String title_name) {
		return m.getProfessorsByAcademicTitle((title_name == null) ? null : title_name.toLowerCase());
	}

	@GET
	@Operation(summary = "Students enrolled in the course", description = "It lets the user see the <u>number</u> and <u>list</u> of students that are enrolled in the course that is lecturing the required subject."
			+ "<br><br>The user can search for all subjects by using the <u>/view/all-subjects</u> function."
			+ "<br><br><b>Warning</b>: The function requires the title of the subject to be written in the correct order with matching the case of the letters; otherwise, it may fail to find the desired result.")
	@Path("/view/students-on-subject")
	public StudentsWithCount getStudentsBySubject(@QueryParam("subject") String subject) {
		return m.getStudentsBySubject(subject);
	}

	@GET
	@Operation(summary = "Time lecture of a subject", description = "It lets the user see a list of subjects that are being lectured before or after a specific time."
			+ "<br><br>The condition parameter only accepts values <u>Before</u> and <u>After</u>. The time parameter only accepts integer values."
			+ "<br><i>Examples</i>: Before 12 or After 15"
			+ "<br><br><u>The condition parameter supports case-insensitive matching.</u>")
	@Path("/subject-by-time")
	public List<ShiftDTO> getSubjectsByShift(@QueryParam("condition") String condition, @QueryParam("time") int time) {
		return m.getSubjectsByShift(condition, time);
	}

	@GET
	@Operation(summary = "Students by study year", description = "It lets the user see the <u>number</u> and <u>list</u> of students by the required study year."
			+ "<br><br>The user can search for all study years by using the <u>/view/all-years-of-study</u> function."
			+ "<br><br><b>Warning</b>: The function supports case-insensitive matching, but it requires the study year to be in the correct order; otherwise, it may fail to find the desired result.")
	@Path("/student-by-study-year")
	public StudentsWithCount getStudentsByYearOfStudy(@QueryParam("year_of_study") String year_of_study) {
		return m.getStudentsByYearOfStudy(year_of_study);
	}
}