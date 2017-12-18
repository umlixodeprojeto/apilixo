package br.com.restful.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.restful.controller.TrashController;
import br.com.restful.model.TrashModel;

@Path("/lixeira")
public class Trash {
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/cheia")
	public Response isFull (TrashModel trash) {
		TrashController controller = new TrashController();
		
		int statusCode = (controller.update(trash)) ? 200 : 500;
		System.out.println(trash.toString());
		
		return Response.status(statusCode)
				.entity(trash)
				.build();
	}

}
