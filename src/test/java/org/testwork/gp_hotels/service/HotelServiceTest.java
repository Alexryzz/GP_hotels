package org.testwork.gp_hotels.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testwork.gp_hotels.dto.request.CreateHotelRequest;
import org.testwork.gp_hotels.dto.response.HotelResponse;
import org.testwork.gp_hotels.entity.Hotel;
import org.testwork.gp_hotels.mapper.HotelMapper;
import org.testwork.gp_hotels.repository.HotelRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private HotelMapper hotelMapper;

    @InjectMocks
    private HotelService hotelService;

    @Test
    void createHotel_ShouldSaveAndReturnResponse() {
        // given
        CreateHotelRequest request = new CreateHotelRequest();
        request.setName("Test Hotel");

        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Test Hotel");

        HotelResponse expectedResponse = new HotelResponse();
        expectedResponse.setId(1L);
        expectedResponse.setName("Test Hotel");

        when(hotelMapper.toHotel(request)).thenReturn(hotel);
        when(hotelRepository.save(hotel)).thenReturn(hotel);
        when(hotelMapper.toHotelResponse(hotel)).thenReturn(expectedResponse);

        // when
        HotelResponse result = hotelService.createHotel(request);

        // then
        assertThat(result).isEqualTo(expectedResponse);
        verify(hotelRepository).save(hotel);
    }

    @Test
    void addAmenitiesToHotel_ShouldAddAmenities_WhenHotelExists() {
        // given
        Long hotelId = 1L;
        List<String> amenities = Arrays.asList("Free parking", "Free WiFi");

        Hotel hotel = new Hotel();
        hotel.setId(hotelId);
        hotel.setAmenities(new ArrayList<>());

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));

        // when
        hotelService.addAmenitiesToHotel(hotelId, amenities);

        // then
        assertThat(hotel.getAmenities()).containsExactly("Free parking", "Free WiFi");
        verify(hotelRepository).save(hotel);
    }

    @Test
    void addAmenitiesToHotel_ShouldThrowException_WhenHotelNotFound() {
        // given
        Long hotelId = 999L;
        List<String> amenities = List.of("Free parking");

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> hotelService.addAmenitiesToHotel(hotelId, amenities))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("hotel not found");

        verify(hotelRepository, never()).save(any());
    }
}
