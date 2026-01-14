package com.amazonclone.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceOrderRequest {
    private String houseNumber;
    private String street;
    private String city;
    private String state;
    private String country;
    private String pinCode;
}
