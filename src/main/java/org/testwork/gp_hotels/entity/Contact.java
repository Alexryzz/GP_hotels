package org.testwork.gp_hotels.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Embeddable
@Data
public class Contact {
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^\\+[0-9]{1,3}[0-9\\s\\-]{7,20}$",
            message = "Phone must be in international format, e.g., +375 17 309-80-00")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;
}
