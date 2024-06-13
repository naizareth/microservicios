package com.empowerbiz.clientsservice.dto;
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

    @NotBlank(message = "El nombre del cliente no puede estar en blanco")
    private String clientName;

    @Email(message = "El correo electrónico debe ser válido")
    @NotBlank(message = "El correo electrónico no puede estar en blanco")
    @NotNull(message = "El correo electrónico no puede estar en blanco")
    private String email;

    @NotBlank(message = "La dirección no puede estar en blanco")
    private String address;

    @NotBlank(message = "El teléfono no puede estar en blanco")
    private String phone;

}
