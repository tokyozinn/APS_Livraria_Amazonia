package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DAORoot {

	private Connection con;
	
	public DAORoot() {}
	
	public Connection abreConexao() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/livraria?serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
