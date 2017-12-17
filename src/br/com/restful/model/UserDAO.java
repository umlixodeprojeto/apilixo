package br.com.restful.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.restful.database.MySQLDatabase;

public class UserDAO extends DAO<UserModel>{

	public UserDAO(MySQLDatabase database) {
		super(database);
		this.database.connect();
	}

	@Override
	public boolean insert(UserModel object) {
		// TODO Criar fun��o para inserir um novo usu�rio no banco de dados.
		return false;
	}

	/**
	 * Busca um usu�rio no banco de dados pelo username
	 * @author Caio de Freitas
	 * @param username
	 * @return Caso exista o usu�rio um objeto � retornado, caso contrario � retornado um NULL.
	 */
	public UserModel getByUsername (UserModel user) {
		String query = "SELECT * FROM User WHERE username = '"+user.getUsername()+"'";
		
		ResultSet result = this.database.query(query);
		
		try {
			if (result.next()) {
				user.setId(result.getLong("id"));
				user.setPassword(result.getString("password"));
				user.setUsername(result.getString("username"));
			} else {
				user = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			user = null;
		}
		
		
		return user;
	}
	
	@Override
	public List<UserModel> getAll() {
		
		List<UserModel> lista = new ArrayList<>();
		
		String query = "SELECT * FROM User;";
		
		ResultSet result = this.database.query(query);
		
		try {
			
			// Percorre a tabela de usu�rios
			while (result.next()) {
			
				
				String username = result.getString("username");
				String password = result.getString("password");
				
				UserModel user = new UserModel();
				user.setUsername(username);
				user.setPassword(password);
				
				lista.add(user);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return lista;
	}

}
