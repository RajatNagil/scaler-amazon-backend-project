package com.amazonclone.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String houseNumber;
    private String street;
    private String city;
    private String state;
    private String country;
    private String pinCode;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
