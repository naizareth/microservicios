package com.empowerbiz.clientsservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empowerbiz.clientsservice.model.Client;
import com.empowerbiz.clientsservice.repository.IClientRepository;
import com.empowerbiz.clientsservice.service.IClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    @Transactional
    public int delete(long clientId) {
        return clientRepository.delete(clientId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll(Long clientId) {
        return clientRepository.findAll(clientId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findById(long clientId) {
        return clientRepository.findById(clientId);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client update(Client client) {
        return clientRepository.update(client);
    } 
}
