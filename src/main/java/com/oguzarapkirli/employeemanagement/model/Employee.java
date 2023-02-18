package com.oguzarapkirli.employeemanagement.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    private String id = UUID.randomUUID().toString();

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    @Size(min = 2, max = 40, message = "Surname must be between 2 and 20 characters")
    private String surname;

    @NotBlank
    @Email(message = "Email should be valid")
    @Indexed(unique = true)
    private String email;

    @Past(message = "Birth date must be in the past")
    @NotNull(message = "Birth date is necessary")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$", message = "Identity number must be 11 digits")
    @Indexed(unique = true)
    @NotBlank(message = "Identity number is necessary")
    private String identityNumber;

    @Indexed(unique = true)
    @Pattern(regexp = "^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$", message = "Phone number should be valid")
    @NotBlank(message = "Phone number is necessary")
    private String phone;

    @Size(min = 10, max = 100, message = "Address must be between 10 and 100 characters")
    @NotBlank(message = "Address is necessary")
    private String address;

    public Employee(String name, String surname, String email, Date birthDate, String identityNumber, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
        this.identityNumber = identityNumber;
        this.phone = phone;
        this.address = address;
    }
}
