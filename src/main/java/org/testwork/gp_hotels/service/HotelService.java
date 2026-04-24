package org.testwork.gp_hotels.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.testwork.gp_hotels.dto.request.CreateHotelRequest;
import org.testwork.gp_hotels.dto.response.HotelResponse;
import org.testwork.gp_hotels.entity.Hotel;
import org.testwork.gp_hotels.mapper.HotelMapper;
import org.testwork.gp_hotels.repository.HotelRepository;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelResponse createHotel(CreateHotelRequest createHotelRequest){
        Hotel hotel = hotelMapper.toHotel(createHotelRequest);
        hotelRepository.save(hotel);
        return hotelMapper.toHotelResponse(hotel);
    }
}
