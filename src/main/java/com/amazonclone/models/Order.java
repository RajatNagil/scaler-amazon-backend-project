package com.amazonclone.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate = LocalDateTime.now();

    private Double amount;

    private String paymentStatus;   // PENDING / SUCCESS / FAILED
    private String orderStatus;     // PLACED / SHIPPED / OUT_FOR_DELIVERY / DELIVERED

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @OneToOne
    private Address shippingAddress;
}
