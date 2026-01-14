package com.amazonclone.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Double price;
    private Integer stock;
    private Long categoryId;
    private String categoryName;
}
