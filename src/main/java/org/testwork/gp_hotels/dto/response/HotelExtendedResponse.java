package org.testwork.gp_hotels.dto.response;

import lombok.Data;
import org.testwork.gp_hotels.entity.Address;
import org.testwork.gp_hotels.entity.ArrivalTime;
import org.testwork.gp_hotels.entity.Contact;

import java.util.List;
@Data
public class HotelExtendedResponse {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private Address address;
    private Contact contacts;
    private ArrivalTime arrivalTime;
    private List<String> amenities;
}
