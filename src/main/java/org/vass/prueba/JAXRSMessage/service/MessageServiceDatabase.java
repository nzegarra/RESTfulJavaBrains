package org.vass.prueba.JAXRSMessage.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.vass.prueba.JAXRSMessage.database.DatabaseClass;
import org.vass.prueba.JAXRSMessage.exception.DataNotFoundException;
import org.vass.prueba.JAXRSMessage.model.Message;

public class MessageServiceDatabase {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageServiceDatabase(){
		Message msj1 = new Message(1, "primer mensaje", "Natanael");
		Message msj2 = new Message(2, "segundo mensaje", "natanael");
		messages.put(1L, msj1);
		messages.put(2L, msj2);
		
	}
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessageForYear(int year){
		List<Message> listMessageForYear = new ArrayList<Message>();
		Calendar calendar = Calendar.getInstance();
		for(Message message : messages.values()){
			calendar.setTime(message.getDate());
			if(calendar.get(Calendar.YEAR) == year){
				listMessageForYear.add(message);
			}
		}
		return listMessageForYear;
	}
	
	public List<Message> getAllMessagePaginated(int start, int size){
		ArrayList<Message> listPaginated = new ArrayList<Message>(messages.values());
		if((start + size) > listPaginated.size())
			return listPaginated;
		return listPaginated.subList(start, start + size);
	}
	
	public Message getMessage(long id){
		Message message = messages.get(id);
		if(message == null){
			throw new DataNotFoundException("Message with Id " + id + "Not found");
		}
		return message;
	}
	public Message addMessage(Message message){
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId() <= 0){
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(Long id){
		return messages.remove(id);
	}
	
}
