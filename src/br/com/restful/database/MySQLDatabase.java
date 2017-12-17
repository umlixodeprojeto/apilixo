package br.com.restful.database;

public class MySQLDatabase extends BancoDeDados {

	public MySQLDatabase(String username, String password, String database) {
		super(username, password, database);
	}

	@Override
	public boolean insert(String sql) {
		return this.executarComandosSQL(sql);
	}

	@Override
	public boolean update(String sql) {
		return this.executarComandosSQL(sql);
	}

	@Override
	public boolean delete(String sql) {
		return this.executarComandosSQL(sql);
	}

}

