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

import org.vass.prueba.JAXRSMessage.model.Profile;
import org.vass.prueba.JAXRSMessage.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

	private ProfileService profiles = new ProfileService();
	
	@GET
	public List<Profile> getAllProfiles(){
		return profiles.getAllProfiles();
	}
	
	@GET
	@Path("/{profileId}")
	public Profile getProfile(@PathParam("profileId") long id){
		return profiles.getProfile(id);
	}
	
	@POST
	public Profile addProfile(Profile profile){
		return profiles.addProfile(profile);
	}
	
	@PUT
	@Path("/{profileId}")
	public Profile updateProfile(@PathParam("profileId") long id, Profile profile){
		if(id < 0){
			return null;
		}
		profile.setId(id);
		return profiles.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileId}")
	public Profile deleteProfile(@PathParam("profielId") long id){
		if(id < 0){
			return null;
		}
		return profiles.deletProfile(id);
	}
}
