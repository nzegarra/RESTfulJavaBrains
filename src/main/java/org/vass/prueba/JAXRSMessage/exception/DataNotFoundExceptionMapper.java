package org.vass.prueba.JAXRSMessage.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.vass.prueba.JAXRSMessage.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		//reemplaza el mensaje de 500 internal error por 404 not Found
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404,"http://localhost:8080/jaxRs/rest/message/id");
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}

}
