package com.crm.model;
 
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
 
import java.time.LocalDateTime;
import java.util.List;
 
@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank(message = "Ism majburiy")
    @Column(nullable = false)
    private String name;
 
    @Email(message = "Email noto'g'ri")
    @Column(unique = true)
    private String email;
 
    private String phone;
    private String company;
    private String address;
 
    @Enumerated(EnumType.STRING)
    private CustomerStatus status = CustomerStatus.ACTIVE;
 
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
 
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;
 
    public enum CustomerStatus {
        ACTIVE, INACTIVE, PROSPECT
    }
}