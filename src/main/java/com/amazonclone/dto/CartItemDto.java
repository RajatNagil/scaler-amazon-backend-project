package com.amazonclone.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {
    private Long id;
    private Long productId;
    private String productTitle;
    private Double price;
    private Integer quantity;
}
