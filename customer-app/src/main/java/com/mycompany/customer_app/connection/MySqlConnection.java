package com.mycompany.customer_app.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	private static Connection connection = null;
	
	private MySqlConnection() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root","pass@word1");
	}
	public static Connection getMyConnection()  {
		if(connection == null) {
			try {
				new MySqlConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}
}
