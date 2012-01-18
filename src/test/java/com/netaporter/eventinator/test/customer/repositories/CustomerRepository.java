package com.netaporter.eventinator.test.customer.repositories;

import com.netaporter.eventinator.test.customer.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * User: Chris Wright [chris.wright@net-a-porter.com]
 * Date: 05/01/12
 * Time: 17:10
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, String> {
    List<Customer> findByName(String name);

     Customer findById(String id);

    Customer findByEmailAddress(String emailAddress);
}
