package com.crm.service;
 
import com.crm.model.Order;
import com.crm.model.Product;
import com.crm.repository.OrderRepository;
import com.crm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import java.math.BigDecimal;
import java.util.List;
 
@Service
@RequiredArgsConstructor
public class OrderService {
 
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
 
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
 
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buyurtma topilmadi: " + id));
    }
 
    @Transactional
    public Order save(Order order) {
        // Total amount hisoblash
        if (order.getProduct() != null && order.getQuantity() != null) {
            Product product = productRepository.findById(order.getProduct().getId())
                    .orElse(null);
            if (product != null) {
                order.setTotalAmount(
                    product.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()))
                );
                order.setProduct(product);
            }
        }
        return orderRepository.save(order);
    }
 
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
 
    public List<Order> getRecentOrders() {
        return orderRepository.findTop5ByOrderByCreatedAtDesc();
    }
 
    public BigDecimal getTotalRevenue() {
        return orderRepository.getTotalRevenue();
    }
 
    public long countAll() {
        return orderRepository.count();
    }
 
    public long countPending() {
        return orderRepository.countByStatus(Order.OrderStatus.PENDING);
    }
}