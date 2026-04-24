package org.testwork.gp_hotels.entity;

import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class Address {
    private String houseNumber;
    private String street;
    private String city;
    private String country;
    private String postCode;
}
