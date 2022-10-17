package com.examples.controller;

import com.examples.entity.Customer;
import com.examples.exception.ApiRequestException;
import com.examples.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v2/customers")
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") Long id) {
        return service.getCustomerById(id);
    }

    @GetMapping("{customerId}/exception")
    public Customer getCustomerByIdException(@PathVariable("customerId") Long id) {
        throw new ApiRequestException(
                "apiRequestException for customer " + id
        );
    }

    @PostMapping()
    public void createNewCustomer(@Valid @RequestBody Customer customer) {
        System.out.println("created " + customer);
    }

    @PutMapping
    public void updateCostumer(@RequestBody Customer customer) {
        System.out.println("updated " + customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long id) {
        System.out.println("deleted id " + id);
    }
}
