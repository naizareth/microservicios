package com.empowerbiz.clientsservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empowerbiz.clientsservice.model.Client;
import com.empowerbiz.clientsservice.repository.impl.ClientRepositoryImpl;
import com.empowerbiz.clientsservice.service.IClientService;

@Service
public class ClientServiceImpl implements IClientService{
@Autowired
    private ClientRepositoryImpl clientRepositoryimpl;

    @Override
    public int delete(long clientId) {
       
        return clientRepositoryimpl.delete(clientId);
    }

    @Override
    public List<Client> findAll(Long clientId) {
              return clientRepositoryimpl.findAll(clientId);
    }

    @Override
    public Optional<Client> findByEmail(String email) {
               return clientRepositoryimpl.findByEmail(email);
    }

    @Override
    public Optional<Client> findById(long clientId) {
               return clientRepositoryimpl.findById( clientId);
    }

    @Override
    public Client save(Client client) {
                return clientRepositoryimpl.save(client);
    }

    @Override
    public Client update(Client client) {
              return clientRepositoryimpl.update(client);
    }

    
}
