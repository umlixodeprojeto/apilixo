package br.com.restful.controller;

import java.sql.SQLException;

import br.com.restful.database.MySQLDatabase;
import br.com.restful.model.TrashDAO;
import br.com.restful.model.TrashModel;

public class TrashController {
	
	private MySQLDatabase db;
	
	public TrashController() {
		this.db = new MySQLDatabase("root", "", "lixo");
	}
	
	
	public boolean update (TrashModel trash) {
		boolean result = true;
		TrashDAO dao = new TrashDAO(db);
		
		try {
			trash = dao.getByName(trash);
			dao.update(trash);
			
			dao.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}



	public TrashModel getById(long id) {
		TrashModel trash = new TrashModel();
		
		try {
			TrashDAO dao = new TrashDAO(db);
			trash = dao.getById(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return trash; 
		
	}

}

