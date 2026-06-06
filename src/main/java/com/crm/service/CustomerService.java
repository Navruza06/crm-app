package com.crm.service;
 
import com.crm.model.Customer;
import com.crm.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
 
import java.util.List;
 
@Service
@RequiredArgsConstructor
public class CustomerService {
 
    private final CustomerRepository customerRepository;
 
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
 
    public Customer getById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mijoz topilmadi: " + id));
    }
 
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
 
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
 
    public List<Customer> search(String query) {
        return customerRepository.findByNameContainingIgnoreCase(query);
    }
 
    public long countActive() {
        return customerRepository.countActiveCustomers();
    }
 
    public long countAll() {
        return customerRepository.count();
    }
}