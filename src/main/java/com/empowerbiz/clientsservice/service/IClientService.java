package com.empowerbiz.clientsservice.service;

import java.util.List;
import java.util.Optional;

import com.empowerbiz.clientsservice.model.Client;

public interface IClientService {

    Client save(Client client);

    Client update(Client client);

    List<Client> findAll(Long clientId);

    Optional<Client> findById(long clientId);

    int delete(long clientId);

    Optional<Client> findByEmail(String email);

}
