package rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.ModelsService;

@Path("/remove")
public class DeleteRest {
	
	@Inject
	private ModelsService m;
	
	@DELETE
	@Path("/student-subject")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteStudentSubject(@QueryParam("index_number") String index_number,
			@QueryParam("subject_title") String title) {
		m.removeStudentFromSubject(index_number, title);
		return Response.status(Response.Status.OK).entity("Student removed from subject successfully").build();
	}
}