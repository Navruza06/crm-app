package com.crm.repository;
 
import com.crm.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import java.math.BigDecimal;
import java.util.List;
 
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
 
    List<Order> findByCustomerId(Long customerId);
 
    List<Order> findByStatus(Order.OrderStatus status);
 
    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE o.status != 'CANCELLED'")
    BigDecimal getTotalRevenue();
 
    long countByStatus(Order.OrderStatus status);
 
    List<Order> findTop5ByOrderByCreatedAtDesc();
}