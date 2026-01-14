package com.amazonclone.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddToCartRequest {
    private Long productId;
    private Integer quantity;
}
