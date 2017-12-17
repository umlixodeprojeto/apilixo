package br.com.restful.database;

import java.sql.ResultSet;

public interface IBancoDeDados {

	public boolean connect();
	public boolean disconnect();
	public boolean executarComandosSQL(String sql);
	
	public boolean insert(String sql);
	public boolean update(String sql);
	public boolean delete(String sql);
	public ResultSet query(String sql);
}
