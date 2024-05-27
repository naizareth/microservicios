package com.empowerbiz.clientsservice.service;

import java.util.List;

import com.empowerbiz.clientsservice.model.Client;

public interface IClientService {

    Client save(Client client);
    Client update(Client client);
    List<Client> findAll(Long clientId);
    Client findById(long clientId);
    int delete(long clientId);
    Client findByEmail(String email);

}
