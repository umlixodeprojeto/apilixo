package br.com.restful.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.restful.database.MySQLDatabase;

public class TrashDAO extends DAO<TrashModel>{

	public TrashDAO(MySQLDatabase database) {
		super(database);
		this.database.connect();
	}

	@Override
	public boolean insert(TrashModel object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TrashModel> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Busca a lixeira pelo nome
	 * @param trash
	 * @return
	 * @throws SQLException
	 */
	public TrashModel getByName (TrashModel trash) throws SQLException {
		String query = "SELECT * FROM Trash WHERE name = \'?\'";
		
		query = query.replaceFirst("\\?", trash.getName());
		
		ResultSet result = this.database.query(query);
		
		if (result.next()) {
			trash.setId(result.getLong("id"));
			trash.setName(result.getString("name"));
		} else {
			trash = null;
		}
		
		return trash;
	}
	
	/**
	 * Atualiza o status da lixeira
	 * @author Caio de Freitas
	 * @param trash
	 * @return
	 */
	public boolean update (TrashModel trash) {
		String query = "UPDATE Trash SET full = ? WHERE id = ?";
		query = query.replaceFirst("\\?", Boolean.toString(trash.isFull()));
		query = query.replaceFirst("\\?", Long.toString(trash.getId()));
		
		return this.database.update(query);
	}


	public TrashModel getById(long id) throws SQLException {
		TrashModel trash = null;
		String query = "SELECT * FROM Trash WHERE id = ?";
		
		query = query.replaceFirst("\\?", Long.toString(id));
		
		ResultSet result = this.database.query(query);
		if (result.next()) {
			trash = new TrashModel();
			
			trash.setId(result.getLong("id"));
			trash.setName(result.getString("name"));
			trash.setFull(result.getBoolean("full"));
		}

		return trash;
	}

}
