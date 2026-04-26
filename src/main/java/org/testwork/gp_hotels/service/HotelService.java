package org.testwork.gp_hotels.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.testwork.gp_hotels.dto.request.CreateHotelRequest;
import org.testwork.gp_hotels.dto.response.HotelExtendedResponse;
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

    public List<HotelResponse> getAllHotels() {
        log.info("get hotels logic");
        List<Hotel> hotels = hotelRepository.findAll();
        return hotelMapper.toHotelResponseList(hotels);
    }

    public HotelExtendedResponse getExtendedInfoOfHotel(Long id) {
        log.info("get hotel extended logic");
        Hotel hotel = findHotelById(id);
        return hotelMapper.toHotelExtendedResponse(hotel);
    }

    public List<HotelResponse> getHotelsByParams(String name, String brand, String city,
                                                 String country, List<String> amenities) {
        log.info("get hotels by parameters logic");
        long size = amenities != null ? amenities.size() : 0;
        List<Hotel> hotels = hotelRepository.search(name, brand, city, country, amenities, size);
        return hotelMapper.toHotelResponseList(hotels);
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
