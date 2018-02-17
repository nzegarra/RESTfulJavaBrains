package org.vass.prueba.JAXRSMessage.service;

import java.util.ArrayList;
import java.util.List;

import org.vass.prueba.JAXRSMessage.model.Message;

public class MessageService {
	
	
	public List<Message> getAllMessages(){
		
		Message msj1 = new Message(1L, "primer mensaje", "Natanael");
		Message msj2 = new Message(2L, "segundo mensaje", "natanael");
		List<Message> list = new ArrayList<>();
		list.add(msj1);
		list.add(msj2);
		System.out.println("entra aqui hola");
		return list;
	}
}
