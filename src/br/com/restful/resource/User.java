package br.com.restful.resource;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.restful.controller.UserController;
import br.com.restful.model.UserModel;

@Path("/usuario")
public class User {
	
	@GET
	@Path("/todos")
	@Produces("application/json")
	public Response todos () {
		UserController controller = new UserController();
		ArrayList<UserModel> users = controller.getAll();
		return Response.status(200).entity(new Gson().toJson(users))
				.header("Access-Control-Allow-Origin", "*")
			    .header("Access-Control-Allow-Credentials", "true")
			    .header("Access-Control-Allow-Headers","origin, content-type, accept, authorization")
			    .header("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD")
			    .build();
	}
	
	@POST
	@Consumes("application/json")
	@Path("/login")
	public Response login (UserModel user) {
		UserController controller = new UserController();
		
		try {
			user = controller.login(user);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return Response.status(200).entity(new Gson().toJson(user)).build();
	}

}
