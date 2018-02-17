package org.vass.prueba.JAXRSMessage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.vass.prueba.JAXRSMessage.database.DatabaseClass;
import org.vass.prueba.JAXRSMessage.model.Comment;
import org.vass.prueba.JAXRSMessage.model.ErrorMessage;
import org.vass.prueba.JAXRSMessage.model.Message;

public class CommentService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId){
		Message message = messages.get(messageId);
		if(message == null){
			throw new WebApplicationException(Response.status(Status.NOT_FOUND).
					entity(new ErrorMessage("Not Found Message with WebApplication", 404, "http://localhost:8080/jaxrsMessage")).build());
		}
		Map<Long, Comment> comments =  messages.get(messageId).getComments();
		if(comments.get(commentId) == null){
			throw new WebApplicationException(Response.status(Status.NOT_FOUND).
					entity(new ErrorMessage("Not Found Comment with WebApplication", 404, "http://localhost:8080/jaxrsMessage/rest/messages")).build());
		}
		return comments.get(commentId);
	}
	
	public Comment addComment(long messageId, Comment comment){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(comment.getId() <= 0){
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}
