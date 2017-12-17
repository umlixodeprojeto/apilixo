package br.com.restful.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import br.com.restful.database.MySQLDatabase;
import br.com.restful.lib.MyHash;
import br.com.restful.model.UserDAO;
import br.com.restful.model.UserModel;

public class UserController {
	
	private MySQLDatabase db;
	
	public UserController() {
		this.db = new MySQLDatabase("root", "", "lixo");
	}
	
	
	public ArrayList<UserModel> getAll () {
		
		MySQLDatabase db = new MySQLDatabase("root", "", "lixo");
		UserDAO dao = new UserDAO(db);
		return (ArrayList<UserModel>) dao.getAll();
	}
	
	/**
	 * @author Caio de Freitas
	 * @param user - Usuário que está tentando acessar o sistema
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public UserModel login (UserModel user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MyHash hash = new MyHash();
		UserDAO dao = new UserDAO(db);
		
		// armazena a senha do usuário que está logando no sistema
		String password = user.getPassword();
		
		user = dao.getByUsername(user);
		
		
		if (user != null) {
			password = user.getId() + password;
			password = hash.sha512(password);
			
			if (!user.getPassword().equals(password)) user = null;
		}
		
		
		return user;
	}

}
