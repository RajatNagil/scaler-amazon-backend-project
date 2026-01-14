package com.amazonclone.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    private Double price;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
