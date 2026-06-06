package com.crm.model;
 
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
 
import java.math.BigDecimal;
import java.time.LocalDateTime;
 
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(name = "order_number", unique = true)
    private String orderNumber;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
 
    private Integer quantity = 1;
 
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
 
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;
 
    private String notes;
 
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
 
    @PrePersist
    public void generateOrderNumber() {
        if (this.orderNumber == null) {
            this.orderNumber = "ORD-" + System.currentTimeMillis();
        }
    }
 
    public enum OrderStatus {
        PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
    }
}