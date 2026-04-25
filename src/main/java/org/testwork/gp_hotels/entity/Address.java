package org.testwork.gp_hotels.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Embeddable
@Data
public class Address {
    @NotBlank
    private String houseNumber;
    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    @NotBlank
    private String postCode;
}
