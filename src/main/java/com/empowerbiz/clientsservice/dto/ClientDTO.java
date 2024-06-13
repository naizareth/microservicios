package com.empowerbiz.clientsservice.dto;
import com.empowerbiz.clientsservice.util.Mesagges;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ClientDTO{
   
    private long clientId;

    @NotBlank(message = Mesagges.EMPTY_NAME)
    private String clientName;

    @Email(message = Mesagges.EMAIL_INVALID)
    @NotBlank(message = Mesagges.EMPTY_EMAIL)
    @NotNull(message = Mesagges.EMPTY_EMAIL)
    private String email;

    @NotBlank(message = Mesagges.EMPTY_ADDRESS)
    private String address;

    @NotBlank(message = Mesagges.EMPTY_PHONE)
    private String phone;

}
