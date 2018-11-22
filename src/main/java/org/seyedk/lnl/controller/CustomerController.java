package org.seyedk.lnl.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.seyedk.lnl.FamilyApplication;
import org.seyedk.lnl.Repository.CustomerRepository;
import org.seyedk.lnl.domain.Customer;
import org.seyedk.lnl.domain.Heir;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(FamilyApplication.class);

	@Autowired
	private CustomerRepository repo;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Customer AddCustomer(@RequestBody Customer customer) {
		System.out.println("The Customers are: " + customer.getHeirs().toString());
		System.out.println("Checking if customer with this  key already name already exist");
		Optional<Customer> custWithSecretKey = repo.findBySecretKey(customer.getSecretKey());
		if (!custWithSecretKey.isPresent()) {
			Customer newCustomer = repo.save(customer);
			Set<Heir> newHeirs = new HashSet<Heir>();
			for (Heir heir : customer.getHeirs()) {
				System.out.println("my heir is : ");
				heir.setHeiress(newCustomer);
				newHeirs.add(heir);

			}

			newCustomer.setHeirs(newHeirs);
			newCustomer = repo.save(newCustomer);

			return newCustomer;

		}

		return customer;
	}

	@RequestMapping(value = "/{id}/heir", method = RequestMethod.POST)
	public Customer addHierToCustomer(@PathVariable("id") long id

			, @RequestBody Heir heir) {
		Optional<Customer> c = repo.findById(id);
		if (c.isPresent()) {
			System.out.println("The customer is " + c.get().getHeirs().toString());

			Customer cust = c.get();
			Set<Heir> heirs = cust.getHeirs();
			heirs.add(heir);
			cust.setHeirs(heirs);
			repo.save(cust);

			return c.get();
		}
		return null;
	}

	@RequestMapping("/")
	public List<Customer> getAllCustomers() {
		List<Customer> c = (List<Customer>) repo.findAll();
		return c;
	}

	@RequestMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Customer getCustomer(@PathVariable("id") long id) {
		System.out.println("We are trying to retrive that custoemr with Id =" + id);

		Optional<Customer> c = repo.findById(id);
		if (c.isPresent()) {
			System.out.println("The customer is " + c.get().toString());

			return c.get();
		}
		return null;

	}

	@RequestMapping("/{id}/heir")
	public Set<Heir> getCustomerHeirs(@PathVariable("id") long id) {
		Optional<Customer> c = repo.findById(id);
		if (c.isPresent()) {
			System.out.println("The customer is " + c.get().getHeirs().toString());
			return c.get().getHeirs();
		}
		return null;
	}

}
