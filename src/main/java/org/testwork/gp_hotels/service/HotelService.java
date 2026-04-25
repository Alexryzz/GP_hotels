package org.testwork.gp_hotels.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.testwork.gp_hotels.dto.request.CreateHotelRequest;
import org.testwork.gp_hotels.dto.response.HotelResponse;
import org.testwork.gp_hotels.entity.Hotel;
import org.testwork.gp_hotels.mapper.HotelMapper;
import org.testwork.gp_hotels.repository.HotelRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelResponse createHotel(CreateHotelRequest createHotelRequest) {
        log.info("create hotel logic");
        Hotel hotel = hotelMapper.toHotel(createHotelRequest);
        hotelRepository.save(hotel);
        return hotelMapper.toHotelResponse(hotel);
    }

    public void addAmenitiesToHotel(Long id, List<String> amenities) {
        log.info("add amenities to hotel logic");
        Hotel hotel = findHotelById(id);
        hotel.setAmenities(amenities);
        hotelRepository.save(hotel);
    }

    private Hotel findHotelById(Long id) {
        return hotelRepository.findById(id).orElseThrow(
                () -> {
                    log.info("hotel id {} not found", id);
                    return new NoSuchElementException("hotel not found");
                }
        );
    }
}
