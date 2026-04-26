package org.testwork.gp_hotels.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.testwork.gp_hotels.entity.Address;
import org.testwork.gp_hotels.entity.ArrivalTime;
import org.testwork.gp_hotels.entity.Contact;

import java.util.List;
@Data
@Schema(description = "Extended hotel information response")
public class HotelExtendedResponse {
    @Schema(example = "1", description = "Hotel ID")
    private Long id;
    @Schema(example = "DoubleTree by Hilton Minsk", description = "Hotel name")
    private String name;
    @Schema(example = "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital" +
            " and stunning views of Minsk city from the hotel's 20th floor ...",
            description = "Hotel description")
    private String description;
    @Schema(example = "Hilton", description = "Hotel brand")
    private String brand;
    @Schema(description = "Hotel address")
    private Address address;
    @Schema(description = "Contact information")
    private Contact contacts;
    @Schema(description = "Check-in/check-out times")
    private ArrivalTime arrivalTime;
    @Schema(example = "[\"Free parking\", \"Free WiFi\", \"Non-smoking rooms\"]", description = "List of amenities")
    private List<String> amenities;
}
