package org.testwork.gp_hotels.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.testwork.gp_hotels.entity.Address;
import org.testwork.gp_hotels.entity.ArrivalTime;
import org.testwork.gp_hotels.entity.Contact;
@Data
public class CreateHotelRequest {
    private String name;
    private String description;
    private String brand;
    private Address address;
    private Contact contacts;
    private ArrivalTime arrivalTime;
}
