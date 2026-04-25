package org.testwork.gp_hotels.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @ElementCollection
    @CollectionTable(name = "hotel_amenities",
            joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "amenity")
    private List<String> amenities = new ArrayList<>();

}
