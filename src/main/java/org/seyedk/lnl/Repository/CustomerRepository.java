package org.seyedk.lnl.Repository;

import java.util.List;
import java.util.Optional;

import org.seyedk.lnl.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findByLastName(String lastName);

	Optional<Customer> findBySecretKey(String secretKey);
}
