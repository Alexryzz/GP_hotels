package org.testwork.gp_hotels.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.testwork.gp_hotels.entity.Address;
import org.testwork.gp_hotels.entity.ArrivalTime;
import org.testwork.gp_hotels.entity.Contact;
@Data
public class CreateHotelRequest {
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String brand;
    @NotNull(message = "Address is required")
    @Valid
    private Address address;
    @NotNull(message = "Contacts are required")
    @Valid
    private Contact contacts;
    @NotNull(message = "Registration time is required")
    @Valid
    private ArrivalTime arrivalTime;
}
