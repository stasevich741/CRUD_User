package com.examples.service;

import com.examples.entity.Customer;
import com.examples.exception.NotFoundException;
import com.examples.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService {

    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException("customer with id " + id + " not found");
                    log.error("error in getCustomerById for id : {} ", id, notFoundException);
                    return notFoundException;
                });
    }
}
