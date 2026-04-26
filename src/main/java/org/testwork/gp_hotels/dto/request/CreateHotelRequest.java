package org.testwork.gp_hotels.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.testwork.gp_hotels.entity.Address;
import org.testwork.gp_hotels.entity.ArrivalTime;
import org.testwork.gp_hotels.entity.Contact;
@Data
@Schema(description = "Create hotel request")
public class CreateHotelRequest {
    @Schema(example = "DoubleTree by Hilton Minsk", description = "Hotel name")
    @NotBlank
    private String name;
    @Schema(example = "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital" +
            " and stunning views of Minsk city from the hotel's 20th floor ...",
            description = "Hotel description")
    private String description;
    @Schema(example = "Hilton", description = "Hotel brand")
    @NotBlank
    private String brand;
    @Schema(description = "Hotel address")
    @NotNull(message = "Address is required")
    @Valid
    private Address address;
    @Schema(description = "Contact information")
    @NotNull(message = "Contacts are required")
    @Valid
    private Contact contacts;
    @Schema(description = "Check-in/check-out times")
    @NotNull(message = "Registration time is required")
    @Valid
    private ArrivalTime arrivalTime;
}
