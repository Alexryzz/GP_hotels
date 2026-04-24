package org.testwork.gp_hotels.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String description;
    private String brand;
    @Embedded
    private Address address;
    @Embedded
    private Contact contacts;
    @Embedded
    private ArrivalTime arrivalTime;

}
