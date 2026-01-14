package com.amazonclone.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private LocalDateTime orderDate;
    private Double amount;
    private String paymentStatus;
    private String orderStatus;
    private List<OrderItemDto> items;
}
