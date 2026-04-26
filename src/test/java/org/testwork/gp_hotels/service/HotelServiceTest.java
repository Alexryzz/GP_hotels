package org.testwork.gp_hotels.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testwork.gp_hotels.dto.request.CreateHotelRequest;
import org.testwork.gp_hotels.dto.response.HotelExtendedResponse;
import org.testwork.gp_hotels.dto.response.HotelResponse;
import org.testwork.gp_hotels.entity.Contact;
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

    @Test
    void getAllHotels_ShouldReturnList() {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        Contact contact = new Contact();
        contact.setPhone("+123");
        hotel.setContacts(contact);

        when(hotelRepository.findAll()).thenReturn(List.of(hotel));
        when(hotelMapper.toHotelResponseList(any())).thenReturn(List.of(new HotelResponse()));

        List<HotelResponse> result = hotelService.getAllHotels();

        assertThat(result).hasSize(1);
        verify(hotelRepository).findAll();
    }

    @Test
    void getExtendedInfoOfHotel_ShouldReturnResponse_WhenExists() {
        Long id = 1L;
        Hotel hotel = new Hotel();
        when(hotelRepository.findById(id)).thenReturn(Optional.of(hotel));
        when(hotelMapper.toHotelExtendedResponse(hotel)).thenReturn(new HotelExtendedResponse());

        HotelExtendedResponse result = hotelService.getExtendedInfoOfHotel(id);

        assertThat(result).isNotNull();
    }

    @Test
    void getExtendedInfoOfHotel_ShouldThrow_WhenNotFound() {
        Long id = 999L;
        when(hotelRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> hotelService.getExtendedInfoOfHotel(id))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void getHistogram_ShouldReturnMap() {
        List<Object[]> results = new ArrayList<>();
        results.add(new Object[]{"Hilton", 3L});
        when(hotelRepository.countByBrand()).thenReturn(results);

        Map<String, Long> histogram = hotelService.getHistogram("brand");

        assertThat(histogram).containsEntry("Hilton", 3L);
    }

    @Test
    void getHistogram_ShouldThrow_WhenInvalidParam() {
        assertThatThrownBy(() -> hotelService.getHistogram("invalid"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
