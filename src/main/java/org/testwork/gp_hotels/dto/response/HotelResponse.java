package org.testwork.gp_hotels.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.testwork.gp_hotels.entity.Address;
@Data
public class HotelResponse {
    private Long id;
    private String name;
    private String description;
    @JsonIgnore
    private Address address;

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
    private String phone;
}
