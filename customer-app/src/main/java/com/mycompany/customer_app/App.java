package com.mycompany.customer_app;

import java.util.List;
import java.util.Scanner;

import com.mycompany.customer_app.factory.CustomerFactory;
import com.mycompany.customer_app.factory.CustomerFactoryImpl;
import com.mycompany.customer_app.model.Customer;

/**
 * Hello world!
 *
 */
public class App {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int choice = 0;
		try {
			do {
				CustomerFactory impl = new CustomerFactoryImpl();
				System.out.println("1. Create Customer");
				System.out.println("2. Display all Customers");
				System.out.println("3. Find Customer my ID");
				System.out.println("4. Update Customer");
				System.out.println("5. Remove Customer");
				System.out.println("0. Exit");
				System.out.println("Enter your choice");
				choice = scanner.nextInt();

				switch (choice) {
				case 1:
					System.out.println("ID: ");
					int id = scanner.nextInt();
					System.out.println("NAME: ");
					String name = scanner.next();
					System.out.println("EMAIL: ");
					String email = scanner.next();

					Customer customer = impl.createCustomer(id, name, email);
					System.out.println(customer.toString());
					break;
				case 2:
					List<Customer> list = impl.getAllCustomer();
					for (Customer c : list) {
						System.out.println(c);
					}
					break;
				case 3:
					System.out.println("Enter the id to search: ");
					id = scanner.nextInt();
					impl.findById(id);
					break;
				case 4: 
					System.out.println("Enter the id to update: ");
					id = scanner.nextInt();
					impl.updateCustomer(id);
					break;
				case 5: 
					System.out.println("Enter the id to delete: ");
					id= scanner.nextInt();
					impl.deleteCustomer(id);
					break;
				case 0:
					System.out.println("Bye");
					System.exit(0);	
				default:
					System.out.println("Invalid Choice!");
					break;
				}
			} while(choice >= 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
