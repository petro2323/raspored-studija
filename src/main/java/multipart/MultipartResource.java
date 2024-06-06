package multipart;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("/multipart")
public class MultipartResource {

	private static final String UPLOAD_DIR = "C:\\Users\\Win\\eclipse-workspace\\raspored-studija\\files_uploaded";

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String uploadFile(MultipartBody request) {
		try {
            saveFile(request.getFile(), request.getFileName());
        } catch (IOException e) {
            return "Error saving file: " + e.getMessage();
        }
        return "File uploaded successfully: " + request.getFileName();
	}

	@Schema(type = SchemaType.STRING, format = "binary")
	public static class UploadSchema {
	};
	
	private void saveFile(FileUpload fileUpload, String fileName) throws IOException {
		java.nio.file.Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        java.nio.file.Path filePath = uploadPath.resolve(fileName);
        Files.copy(fileUpload.uploadedFile(), filePath);
	}
}
