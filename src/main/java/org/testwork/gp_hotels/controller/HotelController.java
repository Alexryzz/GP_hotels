package org.testwork.gp_hotels.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.testwork.gp_hotels.api.HotelApi;
import org.testwork.gp_hotels.dto.request.CreateHotelRequest;
import org.testwork.gp_hotels.dto.response.HotelExtendedResponse;
import org.testwork.gp_hotels.dto.response.HotelResponse;
import org.testwork.gp_hotels.service.HotelService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property-view")
@RequiredArgsConstructor
@Slf4j
public class HotelController implements HotelApi {
    private final HotelService hotelService;

    @Override
    @PostMapping("/hotels")
    public ResponseEntity<HotelResponse> createHotel(@Valid @RequestBody CreateHotelRequest createHotelRequest) {
        log.info("create hotel request");
        HotelResponse hotelResponse = hotelService.createHotel(createHotelRequest);
        return ResponseEntity.ok(hotelResponse);
    }
    @Override
    @PostMapping("/hotels/{id}/amenities")
    public ResponseEntity<Void> addAmenities(@PathVariable Long id,
                                             @RequestBody List<String> amenities) {
        log.info("add amenities request");
        hotelService.addAmenitiesToHotel(id, amenities);
        return ResponseEntity.ok().build();
    }
    @Override
    @GetMapping("/hotels")
    public ResponseEntity<List<HotelResponse>> getAllHotels() {
        log.info("get hotels request");
        List<HotelResponse> allHotels = hotelService.getAllHotels();
        return ResponseEntity.ok(allHotels);
    }
    @Override
    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelExtendedResponse> getHotel(@PathVariable Long id) {
        log.info("get hotel request");
        HotelExtendedResponse hotelExtendedResponse = hotelService.getExtendedInfoOfHotel(id);
        return ResponseEntity.ok(hotelExtendedResponse);
    }
    @Override
    @GetMapping("/search")
    public ResponseEntity<List<HotelResponse>> getHotelsByParam(@RequestParam(required = false) String name,
                                                                @RequestParam(required = false) String brand,
                                                                @RequestParam(required = false) String city,
                                                                @RequestParam(required = false) String country,
                                                                @RequestParam(required = false) List<String> amenities) {
        log.info("get hotels by param request");
        List<HotelResponse> hotelsByParams = hotelService
                .getHotelsByParams(name, brand, city, country, amenities);
        return ResponseEntity.ok(hotelsByParams);
    }
    @Override
    @GetMapping("/histogram/{param}")
    public ResponseEntity<Map<String, Long>> getHotelsHistogramByParam(@PathVariable String param) {
        log.info("get hotels histogram by param request");
        Map<String, Long> histogram = hotelService.getHistogram(param);
        return ResponseEntity.ok(histogram);
    }
}
