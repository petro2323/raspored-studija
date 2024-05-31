package rest;

import java.time.LocalTime;

import org.eclipse.microprofile.openapi.annotations.Operation;

import jakarta.inject.Inject;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import service.ModelsService;

@Path("/patch")
public class PatchRest {
	
	@Inject
	private ModelsService m;
	
	@PATCH
	@Operation(summary = "Update subject data", description = "It lets the user update the data of a subject."
			+ "<br>The function <b>requires</b> the name of the subject where changes are being made; otherwise it will return <i>false</i> which indicates that the request has been interrupted."
			+ "<br>The user can choose the variables that need to be changed which means that other inputs (<b>EXCEPT FOR old_subject_title</b>) can be left <u>blank</u>; in other words the function creates a query based on the user's conditions."
			+ "<br><br><b>Warning</b>: The function supports case-insensitive matching, but it requires for the old subject title to be in the correct order; otherwise, it may fail to send the request.")
	@Path("/subject")
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
	@Path("/lecture")
	public boolean updateLecture(@QueryParam("time") LocalTime time, @QueryParam("classroom") String classroom,
			@QueryParam("day") String day, @QueryParam("subject") String subject) {

		return m.updateLecture(time, classroom, day, subject);
	}

	@PATCH
	@Path("/student")
	public boolean updateStudent(@QueryParam("first_name") String first_name, @QueryParam("last_name") String last_name,
			@QueryParam("year_of_study") String year_of_study, @QueryParam("index_number") String index_number) {
		
		return m.updateStudent(first_name, last_name, year_of_study, index_number);
	}
}