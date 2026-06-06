package com.crm.service;
 
import com.crm.model.Product;
import com.crm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
 
import java.util.List;
 
@Service
@RequiredArgsConstructor
public class ProductService {
 
    private final ProductRepository productRepository;
 
    public List<Product> getAll() {
        return productRepository.findAll();
    }
 
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mahsulot topilmadi: " + id));
    }
 
    public Product save(Product product) {
        return productRepository.save(product);
    }
 
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
 
    public List<Product> search(String query) {
        return productRepository.findByNameContainingIgnoreCase(query);
    }
 
    public long countAll() {
        return productRepository.count();
    }
 
    public long countAvailable() {
        return productRepository.countByStatus(Product.ProductStatus.AVAILABLE);
    }
}