package com.crm.repository;
 
import com.crm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import java.util.List;
 
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
 
    List<Customer> findByNameContainingIgnoreCase(String name);
 
    List<Customer> findByStatus(Customer.CustomerStatus status);
 
    @Query("SELECT COUNT(c) FROM Customer c WHERE c.status = 'ACTIVE'")
    long countActiveCustomers();
 
    boolean existsByEmail(String email);
}