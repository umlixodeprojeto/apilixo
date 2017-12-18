package br.com.restful.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import br.com.restful.controller.TrashController;
import br.com.restful.model.TrashModel;

@Path("/lixeira")
public class Trash {
	
	@PUT
	@Consumes("application/json")
	@Path("/cheia")
	public Response isFull (TrashModel trash) {
		TrashController controller = new TrashController();
		
		int statusCode = (controller.update(trash)) ? 200 : 500;
		System.out.println(trash.toString());
		
		return Response.status(statusCode)
				.entity(trash)
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getTrash (@PathParam("id") long id) {
		TrashController controller = new TrashController();
		TrashModel trash = controller.getById(id);
		
		return Response.status(Status.OK)
				.entity(new Gson().toJson(trash))
				.build();
	}

}
