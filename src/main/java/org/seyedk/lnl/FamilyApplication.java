package org.seyedk.lnl;

import java.util.HashSet;
import java.util.Set;

import org.seyedk.lnl.Repository.CustomerRepository;
import org.seyedk.lnl.domain.Customer;
import org.seyedk.lnl.domain.Heir;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FamilyApplication {
	private static final Logger log = LoggerFactory.getLogger(FamilyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FamilyApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			Customer customerJack = new Customer("Jack", "Bauer", "123413", null);
			Set<Heir> heirs = new HashSet<Heir>();
			heirs.add(new Heir("seyed", "ketabchi", "male", customerJack));
			heirs.add(new Heir("Aryan", "ketabchi", "male", customerJack));
			heirs.add(new Heir("mahshid", "ketabchi", "female", customerJack));

			customerJack.setHeirs(heirs);
			repository.save(customerJack);
			Customer custChloe = new Customer("Chloe", "O'Brian", "AABBCCDD", null);
			repository.save(custChloe);
			Customer custKim = new Customer("Kim", "Bauer", "A1B2C3D4", null);
			repository.save(custKim);
			Customer custDavid = new Customer("David", "Palmer", "1A2B3C4D", null);
			repository.save(custDavid);
			Customer custMichelle = new Customer("Michelle", "Dessler", "1234ABCD", null);
			repository.save(custMichelle);

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			repository.findById(1L).ifPresent(customer -> {
				log.info("Customer found with findById(1L):");
				log.info("--------------------------------");
				log.info(customer.toString());
				log.info("");
			});

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			// log.info(bauer.toString());
			// }
			log.info("");
		};
	}

}
