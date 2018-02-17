package org.vass.prueba.JAXRSMessage.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("annotations")
	public String getAnnotationUsingParam(@MatrixParam("matrixParam") String matrix,
											@HeaderParam("headerParam") String headerParam,
											@CookieParam("cookieParam") String cookieParam){
		return "Matrizparam : "+matrix + "HeaderParam: "+headerParam+ "CookieParam" + cookieParam;
	}
	
	@GET
	@Path("context")
	public String getContextAnnotation(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders){
		String uri = uriInfo.getAbsolutePath().toString();
		String header = httpHeaders.getCookies().toString();
		return "uriInfo path: "+uri + "-- header Cookies: "+header;
	}
}
