package com.amazonclone.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private Long id;
    private Long productId;
    private String productTitle;
    private Integer quantity;
    private Double price;
}
