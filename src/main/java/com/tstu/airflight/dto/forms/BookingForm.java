package com.tstu.airflight.dto.forms;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class BookingForm {

    private Long clientId;
    @NotEmpty
    @NotNull
    private String firstName;
    @NotEmpty
    @NotNull
    private String lastName;
    @NotEmpty
    @NotNull
    private String phone;
    @NotEmpty
    @NotNull
    private String passport;
}
