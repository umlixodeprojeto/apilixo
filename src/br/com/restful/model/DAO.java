package br.com.restful.model;

import java.util.List;

import br.com.restful.database.MySQLDatabase;


public abstract class DAO<E> {

	protected MySQLDatabase database;
	
	public DAO(MySQLDatabase database) {
		this.database = database;
	}
	
	public abstract boolean insert (E object);
	
	public abstract List<E> getAll();
}

