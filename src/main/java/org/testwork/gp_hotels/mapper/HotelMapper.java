package org.testwork.gp_hotels.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.testwork.gp_hotels.dto.request.CreateHotelRequest;
import org.testwork.gp_hotels.dto.response.HotelResponse;
import org.testwork.gp_hotels.entity.Hotel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    Hotel toHotel(CreateHotelRequest createHotelRequest);
    @Mapping(source = "contacts.phone", target = "phone")
    HotelResponse toHotelResponse(Hotel hotel);
    @Mapping(source = "contact.phone", target = "phone")
    List<HotelResponse> toHotelResponseList(List<Hotel> hotels);
}
