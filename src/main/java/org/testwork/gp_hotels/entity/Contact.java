package org.testwork.gp_hotels.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Embeddable
@Data
@Schema(description = "Contact information")
public class Contact {
    @Schema(example = "+375 17 309-80-00", description = "Phone number")
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^\\+[0-9]{1,3}[0-9\\s\\-]{7,20}$",
            message = "Phone must be in international format, e.g., +375 17 309-80-00")
    private String phone;

    @Schema(example = "doubletreeminsk.info@hilton.com", description = "Email address")
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;
}
