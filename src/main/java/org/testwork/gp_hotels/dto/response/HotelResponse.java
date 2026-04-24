package org.testwork.gp_hotels.dto.response;

import lombok.Data;
import org.testwork.gp_hotels.entity.Address;
@Data
public class HotelResponse {
    private Long id;
    private String name;
    private String description;
    private Address address;
    private String phone;
}
