package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import multipart.MultipartBody;

@Path("/multipart")
public class FileResource {
	 
	private static final String UPLOAD_DIR = "C:\\Users\\Win\\eclipse-workspace\\raspored-studija\\files_uploaded";

	    @POST
	    @Consumes(MediaType.MULTIPART_FORM_DATA)
	    @Produces(MediaType.TEXT_PLAIN)
	    public String receiveMultipartData(MultipartBody data) {
	        String fileName = data.fileName;
	        InputStream fileInputStream = data.file;

	        try {
	            saveFile(fileInputStream, fileName);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Failed to upload file: " + e.getMessage();
	        }

	        return "Uploaded file with name: " + fileName;
	    }

	    private void saveFile(InputStream uploadedInputStream, String fileName) throws Exception {
	        File targetFile = new File(UPLOAD_DIR + File.separator + fileName);
	        OutputStream out = null;
	        try {
	            out = new FileOutputStream(targetFile);
	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = uploadedInputStream.read(buffer)) != -1) {
	                out.write(buffer, 0, bytesRead);
	            }
	        } finally {
	            if (out != null) {
	                out.close();
	            }
	        }
	    }
}
