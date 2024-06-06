package multipart;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import jakarta.ws.rs.core.MediaType;
import multipart.MultipartResource.UploadSchema;

public class MultipartBody {

	@RestForm("file")
	@PartType(MediaType.APPLICATION_OCTET_STREAM)
	@Schema(implementation = UploadSchema.class)
	private FileUpload file;

	@RestForm("fileName")
	@PartType(MediaType.TEXT_PLAIN)
	public String fileName;

	public FileUpload getFile() {
		return file;
	}

	public void setFile(FileUpload file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}