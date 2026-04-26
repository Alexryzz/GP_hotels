package org.testwork.gp_hotels.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.testwork.gp_hotels.entity.Address;
@Data
@Schema(description = "Short hotel information response")
public class HotelResponse {
    @Schema(example = "1", description = "Hotel ID")
    private Long id;
    @Schema(example = "DoubleTree by Hilton Minsk", description = "Hotel name")
    private String name;
    @Schema(example = "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital" +
            " and stunning views of Minsk city from the hotel's 20th floor ...",
            description = "Hotel description")
    private String description;
    @JsonIgnore
    private Address address;

    @Schema(example = "9 Pobediteley Avenue, Minsk, 220004, Belarus", description = "Full address as string")
    @JsonProperty("address")
    public String getAddressAsString() {
        return String.format("%s %s, %s, %s, %s",
                address.getHouseNumber(),
                address.getStreet(),
                address.getCity(),
                address.getPostCode(),
                address.getCountry()
        );
    }
    @Schema(example = "+375 17 309-80-00", description = "Contact phone")
    private String phone;
}
