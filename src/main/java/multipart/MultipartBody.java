package multipart;

import java.io.InputStream;

import org.jboss.resteasy.reactive.PartType;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;

public class MultipartBody {
	
	 	@FormParam("file")
	    @PartType(MediaType.APPLICATION_OCTET_STREAM)
	    public InputStream file;

	    @FormParam("fileName")
	    @PartType(MediaType.TEXT_PLAIN)
	    public String fileName;
}