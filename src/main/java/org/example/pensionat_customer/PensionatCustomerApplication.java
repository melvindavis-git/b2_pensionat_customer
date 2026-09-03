package org.example.pensionat_customer;

import org.example.pensionat_customer.Model.Customer;
import org.example.pensionat_customer.Repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class PensionatCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PensionatCustomerApplication.class, args);
    }

    @Bean
    public CommandLineRunner createRooms(CustomerRepository customerRepo) {
        return (args) -> {
            customerRepo.save(new Customer("Melvin", "melvin@gmail.com", "070123456"));
            customerRepo.save(new Customer("Tim", "tim@gmail.com", "070123456"));
            customerRepo.save(new Customer("Mikael", "mikael@gmail.com", "070123456"));

        };
    }

}
