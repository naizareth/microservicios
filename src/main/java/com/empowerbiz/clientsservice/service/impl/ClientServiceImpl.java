package com.empowerbiz.clientsservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empowerbiz.clientsservice.model.Client;
import com.empowerbiz.clientsservice.repository.impl.ClientRepositoryImpl;
import com.empowerbiz.clientsservice.service.IClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    //private final ClientMapper clientMapper;

    private final ClientRepositoryImpl clientRepositoryimpl;

    @Override
    @Transactional
    public int delete(long clientId) {
        return clientRepositoryimpl.delete(clientId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll(Long clientId) {

        return clientRepositoryimpl.findAll(clientId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findByEmail(String email) {
        return clientRepositoryimpl.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findById(long clientId) {
        return clientRepositoryimpl.findById(clientId);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return clientRepositoryimpl.save(client);
    }

    @Override
    @Transactional
    public Client update(Client client) {
        return clientRepositoryimpl.update(client);
    }
    
}
