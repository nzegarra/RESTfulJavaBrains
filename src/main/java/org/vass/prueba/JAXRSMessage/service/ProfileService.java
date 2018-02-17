package org.vass.prueba.JAXRSMessage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.vass.prueba.JAXRSMessage.database.DatabaseClass;
import org.vass.prueba.JAXRSMessage.model.Profile;

public class ProfileService {

	private Map<Long, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService(){
		Profile profile1 = new Profile(1L,"Perfil 1", "Natanael", "Zegarra");
		Profile profile2 = new Profile(2L, "Perfil 2", "Natanael", "Zegarra");
		profiles.put(profile1.getId(), profile1);
		profiles.put(profile2.getId(), profile2);
		
	}
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(long id){
		if(id < 0){
			return null;
		}
		return profiles.get(id);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getId(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getId() < 0){
			return null;
		}
		profiles.put(profile.getId(), profile);
		return profile;
	}
	
	public Profile deletProfile(long id){
		
		return profiles.remove(id);
	}
}