package com.empowerbiz.clientsservice.validators;

import com.empowerbiz.clientsservice.exception.ModelNotFoundException;
import com.empowerbiz.clientsservice.model.Client;
import com.empowerbiz.clientsservice.service.IClientService;
import com.empowerbiz.clientsservice.util.Mesagges;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@RequiredArgsConstructor
@Component
@Validated
public class ClientValidator {


    private final IClientService service;

    // Verifica la unicidad del correo electrónico para un cliente existente
    public void checkEmailUniqueness(String email, Client existingClient, long clientId) {
        // Si el correo electrónico ha cambiado, realizar la validación
        if (!existingClient.getEmail().equals(email)) {
            Optional<Client> existingWithEmail = service.findByEmail(email);
            if (existingWithEmail.isPresent() && existingWithEmail.get().getClientId() != clientId) {
                throw new ModelNotFoundException(Mesagges.DUPLICATE_EMAIL);
            }
        }
    }


    public void validateEmailNotInUse(String email) {
        Optional<Client> existingClientOptional = service.findByEmail(email);
        if (existingClientOptional.isPresent()) {
            throw new ModelNotFoundException(Mesagges.DUPLICATE_EMAIL);
        }
    }

  
    public Client validateClientExists(long clientId) {
        Optional<Client> client = service.findById(clientId);
        if (client.isEmpty()) {
            throw new ModelNotFoundException(Mesagges.NOT_FOUND);
        }
        return client.get();
    }
}
