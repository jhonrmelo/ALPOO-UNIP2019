package util;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

public class SqlConnection {
	public final static String DATABASE = "JavaProjectNP1";
	public final static String URL = "jdbc:postgresql:" + DATABASE;
	public final static String USER = "postgres";
	public final static String PASS = "jhow";

	public static Connection GetConnection() {
		
		Connection connection = null;
		
		try{
			
			connection = DriverManager.getConnection(URL, USER, PASS);
			
			if(connection == null ) {
				System.out.println("A conexão com o banco falhou!");
			}
		}
		
		catch(SQLException exSql) {
			System.out.println("Não foi possivel conectar com o banco, ocorreu um erro:" + exSql);
		}
		
		return connection;	
	}
	
}
