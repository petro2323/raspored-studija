package rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Student;
import service.StudentService;

@Path("/student")
public class StudentRest {
	
	@Inject
	private StudentService ss;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response createStudent(Student s) {
		return Response.ok(ss.createStudent(s)).build();
	}
}
