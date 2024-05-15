package com.empowerbiz.clientsservice.service.impl;

import com.empowerbiz.clientsservice.model.Client;
import com.empowerbiz.clientsservice.repository.IClientRepository;
import com.empowerbiz.clientsservice.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientRepository clientRepository;


    @Override
    public Client create(Client client) {
        try {
            return clientRepository.create(client);
        } catch (Exception e) {
           
            throw new RuntimeException("Error al crear cliente", e);
        }
    }

    @Override
    public Client update(Client client) {
        try {
            return clientRepository.update(client);
        } catch (Exception e) {
            
            throw new RuntimeException("Error al actualizar cliente", e);
        }
    }

    @Override
    public List<Client> findAll(long clientId) {
        try {
            return clientRepository.findAll(null);
        } catch (Exception e) {
           
            throw new RuntimeException("Error al buscar clientes", e);
        }
    }

    @Override
    public Client findById(long clientId) {
        try {
            return clientRepository.findById(clientId);
        } catch (Exception e) {
            
            throw new RuntimeException("Error al buscar cliente por ID", e);
        }
    }

    @Override
    public void delete(long clientId) {
        try {
            clientRepository.delete(clientId);
        } catch (Exception e) {
          
            throw new RuntimeException("Error al eliminar cliente", e);
        }
    }
}
