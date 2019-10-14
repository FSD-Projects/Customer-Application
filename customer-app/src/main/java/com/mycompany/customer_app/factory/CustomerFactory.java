package com.mycompany.customer_app.factory;
import java.util.List;

import com.mycompany.customer_app.model.*;

public interface CustomerFactory {
	public Customer createCustomer(int id, String name, String email);
	public List<Customer> getAllCustomer();
	public void findById(int id);
	public void updateCustomer(int id);
	public void deleteCustomer(int id);
}
