package com.empowerbiz.clientsservice.service;

import com.empowerbiz.clientsservice.model.Client;

import java.util.List;

public interface IClientService {
    
    Client create(Client client);

    Client update(Client client);

    List<Client> findAll(long clientid);

    Client findById(long clientId);

    void delete(long clientId);

}
