package org.vass.prueba.JAXRSMessage.database;

import java.util.HashMap;
import java.util.Map;

import org.vass.prueba.JAXRSMessage.model.Message;
import org.vass.prueba.JAXRSMessage.model.Profile;

public class DatabaseClass {

	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<Long, Profile> profiles = new HashMap<>();
	
	public static Map<Long, Message> getMessages(){
		return messages;
	}
	public static Map<Long, Profile> getProfiles(){
		return profiles;
	}
}
