package org.testwork.gp_hotels.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Embeddable
@Data
@Schema(description = "Address information")
public class Address {
    @Schema(example = "9", description = "House number")
    @NotBlank
    private String houseNumber;
    @Schema(example = "Pobediteley Avenue", description = "Street name")
    @NotBlank
    private String street;
    @Schema(example = "Minsk", description = "City")
    @NotBlank
    private String city;
    @Schema(example = "Belarus", description = "Country")
    @NotBlank
    private String country;
    @Schema(example = "220004", description = "Postal code")
    @NotBlank
    private String postCode;
}
