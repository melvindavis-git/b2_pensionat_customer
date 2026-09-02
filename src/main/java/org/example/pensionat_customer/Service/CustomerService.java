package org.example.pensionat_customer.Service;

import org.example.pensionat_customer.DTO.CustomerDTO;
import org.example.pensionat_customer.Model.Customer;
import org.example.pensionat_customer.Repository.CustomerRepository;
import org.springdoc.core.converters.ResponseSupportConverter;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {

    private final CustomerRepository customerRepo;
    private final GenericResponseService responseBuilder;
    private final ResponseSupportConverter responseSupportConverter;


    public CustomerService(CustomerRepository customerRepo, GenericResponseService responseBuilder, ResponseSupportConverter responseSupportConverter) {
        this.customerRepo = customerRepo;
        this.responseBuilder = responseBuilder;
        this.responseSupportConverter = responseSupportConverter;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepo.findAll().stream().map(c -> CustomerToCustomerDTO(c)).toList();
    }

    public CustomerDTO CustomerToCustomerDTO(Customer c) {
        return CustomerDTO.builder().id(c.getId()).name(c.getName()).email(c.getEmail()).phone(c.getPhone()).build();
    }


    public CustomerDTO registerCustomer(CustomerDTO customerDTO) {

        Customer newCustomer = new Customer(customerDTO.getName(), customerDTO.getEmail(), customerDTO.getPhone());

        customerRepo.save(newCustomer);

        return CustomerToCustomerDTO(newCustomer);
    }

    public boolean deleteById(Long customerId) {

        Customer deletedCustomer = customerRepo.findById(customerId).orElse(null);

        //SKA SKICKA TILL BOOKING

        if(deletedCustomer==null){
            return false;
        }

        //Måste ge errorkod senare
        //FIXA
        CustomerDTO deletedCustomerDTO = CustomerToCustomerDTO(deletedCustomer);

        customerRepo.deleteById(customerId);
        return true;
    }

    public ResponseEntity<CustomerDTO> getCustomerById(Long id) {

        if (customerRepo.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        CustomerDTO customerToSend = CustomerToCustomerDTO(customerRepo.findById(id).orElse(null));
            return ResponseEntity.status(HttpStatus.OK).body(customerToSend);

    }


    public ResponseEntity <CustomerDTO> editCst(CustomerDTO cstToBeEdited) {

        if (customerRepo.findById(cstToBeEdited.getId()).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cstToBeEdited);
        }

        Customer cstToBeSaved = new Customer(cstToBeEdited.getId(), cstToBeEdited.getName(), cstToBeEdited.getEmail(), cstToBeEdited.getPhone());
        customerRepo.save(cstToBeSaved);
        return ResponseEntity.status(HttpStatus.OK).body(CustomerToCustomerDTO(cstToBeSaved));
    }



}
