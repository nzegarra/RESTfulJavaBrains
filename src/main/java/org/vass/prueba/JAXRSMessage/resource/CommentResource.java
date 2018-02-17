package org.vass.prueba.JAXRSMessage.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.vass.prueba.JAXRSMessage.model.Comment;
import org.vass.prueba.JAXRSMessage.service.CommentService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentService commentService = new CommentService();
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") int messageId){
		return commentService.getAllComments(messageId);
	}
	
	@POST
	public Comment addMessge(@PathParam("messageId") int messageId, Comment comment){
		return commentService.addComment(messageId, comment); 
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateMessage(@PathParam("messageId") int messageId, @PathParam("commentId") int commentId,
								Comment comment){
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public Comment deleteComment(@PathParam("messageId") int messageId, @PathParam("commentId") int commentId){
		return commentService.removeComment(messageId, commentId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getMessage(@PathParam("messageId") int messageId, @PathParam("commentId") int commentId){
		return commentService.getComment(messageId, commentId);
	}
	
}
