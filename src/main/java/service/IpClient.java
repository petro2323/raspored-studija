package service;


import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import models.IPLog;

@RegisterRestClient(baseUri = "https://api-bdc.net")
@Path("/data")
public interface IpClient {
	
	@GET
	@Path("/client-ip")
	public IPLog GetIpAddress();
}
