package com.crm.model;
 
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
 
import java.math.BigDecimal;
import java.time.LocalDateTime;
 
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank(message = "Mahsulot nomi majburiy")
    @Column(nullable = false)
    private String name;
 
    private String description;
    private String category;
    private String sku;
 
    @Positive(message = "Narx musbat bo'lishi kerak")
    @Column(nullable = false)
    private BigDecimal price;
 
    @Column(name = "stock_quantity")
    private Integer stockQuantity = 0;
 
    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.AVAILABLE;
 
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
 
    public enum ProductStatus {
        AVAILABLE, OUT_OF_STOCK, DISCONTINUED
    }
}