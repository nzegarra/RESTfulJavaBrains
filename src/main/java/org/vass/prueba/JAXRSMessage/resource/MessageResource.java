package org.vass.prueba.JAXRSMessage.resource;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.vass.prueba.JAXRSMessage.bean.resources.MessageFilterBean;
import org.vass.prueba.JAXRSMessage.model.Message;
import org.vass.prueba.JAXRSMessage.service.MessageService;
import org.vass.prueba.JAXRSMessage.service.MessageServiceDatabase;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messages = new MessageService();
	MessageServiceDatabase messagesdb = new MessageServiceDatabase();
	
//	@GET
//	//@Produces(MediaType.APPLICATION_JSON)
//	public List<Message> getMessages(@QueryParam("year") int year,
//									@QueryParam("start") int start,
//									@QueryParam("size") int size){
//		if(year > 0){
//			messagesdb.getAllMessageForYear(year);
//		}
//		if(start > 0 && size > 0){
//			messagesdb.getAllMessagePaginated(start, size);
//		}
//		return messagesdb.getAllMessages();
//	}
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean){
		if(messageFilterBean.getYear() > 0){
		messagesdb.getAllMessageForYear(messageFilterBean.getYear());
		}
		if(messageFilterBean.getStart() > 0 && messageFilterBean.getSize() > 0){
		messagesdb.getAllMessagePaginated(messageFilterBean.getStart(), messageFilterBean.getSize());
		}
		return messagesdb.getAllMessages();
		}
			
	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message){
		return messagesdb.addMessage(message);
		
	}
	
	@PUT
	@Path("/{messageId}")
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id, Message message){
		message.setId(id);
		return messagesdb.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long id){
		messagesdb.removeMessage(id);
	}
	
	@GET
	@Path("/{messageId}")
	//@Produces(MediaType.APPLICATION_XML)
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo){
		for(Message message : messagesdb.getAllMessages()){
			if(message.getId() == id){
				String uri = getUriForSelf(uriInfo, message);
				String uri2 = getUriForProfile(uriInfo, message);
				message.addLink(uri,"self");
				message.addLink(uri2,"profile");
				return messagesdb.getMessage(id);
			}
		}
		return messagesdb.getMessage(id);
		//return new Message(1000, "No existe este message", "message REST");
		
	}

	/**
	 * @param uriInfo
	 * @param message
	 * @return
	 */
	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder().path(MessageResource.class).path(String.valueOf(message.getId())) .build().toString();
		return uri;
	}
	
	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(String.valueOf(message.getAuthor())) .build().toString();
		return uri;
	}
	
	//sub resource... no se declara con la anotacion @GET
	@Path("/{messageId}/comments")
	public  CommentResource getMessagesubResource(){
		return new CommentResource();
	}
	
	/*@GET
	@Path("/saludo")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMessage(){
		return "hola";
	}*/
	
	
}
