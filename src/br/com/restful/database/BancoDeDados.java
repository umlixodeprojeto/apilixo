package br.com.restful.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BancoDeDados implements IBancoDeDados {

	protected String host;
	protected String username;
	protected String password;
	protected String database;
	protected int port;
	private Connection connection;
	private boolean isConnect;

	// -------------------Construtores----------------------------------------------

	public BancoDeDados(String host, String username, String password, String database, int port) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.database = database;
		this.port = port;
	}

	public BancoDeDados(String host, String username, String password, String database) {
		this(host, username, password, database, 3306);
	}

	public BancoDeDados(String username, String password, String database) {
		this("127.0.0.1", username, password, database);
	}

	// ---------------- GERENCIAR CONEXAO -------------------------------------------

	/**
	 * Verifica se o banco de dados esta conectado.
	 * 
	 * @author Caio de Freitas
	 * @since 2017/09/19
	 * @return Retorna um boolean TRUE caso o banco estej� conectado.
	 */
	public boolean isConnect() {
		return this.isConnect;
	}

	/**
	 * Cria uma conex�o com o banco de dados.
	 * @author Caio de Freitas
	 * @since 2017/09/19
	 * @return Retorna um boolean true caso a conex�o com o banco de dados sej� criada com sucesso.
	 */
	public boolean connect() {
		boolean result = true;
		try {
			// verifica se existe uma conex�o com o banco de dados
			if (this.connection != null) this.connection.close();
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			String url = "jdbc:mysql://%s:%s/%s?user=%s&password=%s";
            url = String.format(url ,this.host, this.port, this.database, this.username, this.password);
			this.connection = DriverManager.getConnection(url);
			this.connection.setAutoCommit(true);
			this.isConnect = true;
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Fecha uma conex�o com o banco de dados.
	 * @author Caio de Freitas
	 * @since 2017/09/19
	 * @return Retorna um boolean true caso a conex�o sej� fechada com sucesso.
	 */
	public boolean disconnect() {
		boolean result = true;
		try {
			// Verifica se existe uma conex�o com o banco de dados
			if ( this.connection != null && !this.connection.isClosed() ) {
				this.connection.close();
				this.isConnect = false;
			}
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}

	// ---------------------- COMANDOS SQL ------------------------------------------
	public boolean executarComandosSQL(String sql) {
		try {
			Statement stmt = connection.createStatement();
			stmt.execute(sql);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public ResultSet query (String sql) {
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			return rs;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}

}
