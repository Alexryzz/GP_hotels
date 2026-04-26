package org.testwork.gp_hotels.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.testwork.gp_hotels.dto.request.CreateHotelRequest;
import org.testwork.gp_hotels.dto.response.HotelExtendedResponse;
import org.testwork.gp_hotels.dto.response.HotelResponse;

import java.util.List;
import java.util.Map;

public interface HotelApi {
    @Operation(
            summary = "Create hotel",
            description ="Create a new hotel using the provided data"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = HotelResponse.class)))
    })
    ResponseEntity<HotelResponse> createHotel(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(implementation = CreateHotelRequest.class))
            )
            @Valid @RequestBody CreateHotelRequest createHotelRequest
    );

    @Operation(
            summary = "Add amenities to hotel",
            description = "Adds a list of amenities to the specified hotel"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")
    })
    ResponseEntity<Void> addAmenities(
            @Parameter(description = "Hotel ID", example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = @ExampleObject(value = """
            [
                "Free parking",
                "Free WiFi",
                "Non-smoking rooms",
                "Concierge",
                "On-site restaurant",
                "Fitness center",
                "Pet-friendly rooms",
                "Room service",
                "Business center",
                "Meeting rooms"
            ]
        """))
            )
            @RequestBody List<String> amenities
    );

    @Operation(
            summary = "Get all hotels",
            description = "Returns a list of all hotels with short information"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = HotelResponse.class))))
    })
    ResponseEntity<List<HotelResponse>> getAllHotels();

    @Operation(
            summary = "Get hotel by ID",
            description = "Returns extended information about a specific hotel"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = HotelExtendedResponse.class))),
            @ApiResponse(responseCode = "404", description = "Hotel not found", content = @Content)
    })
    ResponseEntity<HotelExtendedResponse> getHotel(@Parameter(description = "Hotel ID", example = "1") @PathVariable Long id);

    @Operation(
            summary = "Search hotels",
            description = "Returns a list of hotels filtered by specified parameters"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = HotelResponse.class))))
    })

    ResponseEntity<List<HotelResponse>> getHotelsByParam(
            @Parameter(description = "Hotel name (partial match)", example = "DoubleTree")
            @RequestParam(required = false) String name,
            @Parameter(description = "Hotel brand (exact match)", example = "Hilton")
            @RequestParam(required = false) String brand,
            @Parameter(description = "City (exact match)", example = "Minsk")
            @RequestParam(required = false) String city,
            @Parameter(description = "Country (exact match)", example = "Belarus")
            @RequestParam(required = false) String country,
            @Parameter(description = "List of amenities (hotel must have all)", example = "[\"Free parking\", \"Free WiFi\"]")
            @RequestParam(required = false) List<String> amenities
    );

    @Operation(
            summary = "Get histogram",
            description = "Returns a count of hotels grouped by specified parameter (brand, city, country, amenities)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(examples = @ExampleObject(value = """
            {
                "Hilton": 1,
                "Marriott": 2,
                "Hyatt": 1
            }
        """))),
            @ApiResponse(responseCode = "400", description = "Invalid parameter",
                    content = @Content(examples = @ExampleObject(value = """
            "Illegal request argument: Invalid histogram parameter: invalid"
        """)))
    })
    ResponseEntity<Map<String, Long>> getHotelsHistogramByParam(
            @Parameter(description = "Parameter to group by (brand, city, country, amenities)",
                    example = "city",
                    schema = @Schema(allowableValues = {"brand", "city", "country", "amenities"}))
            @PathVariable String param
    );
}
