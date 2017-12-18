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
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

}
