package com.mycompany.customer_app.factory;

import com.mycompany.customer_app.connection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mycompany.customer_app.model.Customer;

public class CustomerFactoryImpl implements CustomerFactory {

	private Connection connection = null;
	private PreparedStatement pStatement = null;
	private Statement statement = null;
	Customer tempCustomer = null;

	public CustomerFactoryImpl() throws SQLException {
		super();
		connection = MySqlConnection.getMyConnection();
	}

	public Customer createCustomer(int id, String name, String email) {
		// TODO Auto-generated method stub
		try {
			tempCustomer = new Customer(id, name, email);
			pStatement = connection.prepareStatement("INSERT INTO CUSTOMER values(?, ?, ?)");
			pStatement.setInt(1, tempCustomer.getId());
			pStatement.setString(2, tempCustomer.getName().toString());
			pStatement.setString(3, tempCustomer.getEmail().toString());
			int count = pStatement.executeUpdate();
			System.out.println(count + " row(s) inserted...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tempCustomer;

	}

	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		List<Customer> list = new ArrayList();
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM CUSTOMER");
			while (rs.next()) {
				list.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void findById(int id) {
		try {
			int count = 0;
			pStatement = connection.prepareStatement("SELECT * FROM CUSTOMER where ID=?");
			pStatement.setInt(1, id);
			ResultSet rs = pStatement.executeQuery();
			while (rs.next()) {
				count++;
			}
			rs.beforeFirst();
			if (count == 0) {
				System.out.println("No such record!");
			} else {
				while (rs.next()) {
					System.out.println(rs.getString(2) + "," + rs.getString(3));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateCustomer(int id) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Updated name: ");
			String name = sc.next();
			System.out.println("Updated email: ");
			String email = sc.next();
			pStatement = connection.prepareStatement("UPDATE CUSTOMER set NAME=?, EMAIL=? where ID=?");
			pStatement.setString(1, name);
			pStatement.setString(2, email);
			pStatement.setInt(3,  id);
			int i = pStatement.executeUpdate();
			if(i != 0) {
				System.out.println();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteCustomer(int id) {
		try {
			pStatement = connection.prepareStatement("DELETE FROM CUSTOMER where ID=?");
			pStatement.setInt(1,  id);
			System.out.println("Customer with id " + id + " deleted successfully!");
			boolean i = pStatement.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
