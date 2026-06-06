package com.crm.repository;
 
import com.crm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
 
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
 
    List<Product> findByNameContainingIgnoreCase(String name);
 
    List<Product> findByCategory(String category);
 
    List<Product> findByStatus(Product.ProductStatus status);
 
    long countByStatus(Product.ProductStatus status);
}