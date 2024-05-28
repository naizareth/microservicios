package com.empowerbiz.clientsservice.repository.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.empowerbiz.clientsservice.exception.ModelNotFoundException;
import com.empowerbiz.clientsservice.model.Client;
import com.empowerbiz.clientsservice.repository.IClientRepository;

@Repository
public class ClientRepositoryImpl implements IClientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Client save(Client client) {
        String sql = "INSERT INTO clients (clientname, email,address,phone) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, client.getClientName(), client.getEmail(), client.getAddress(), client.getPhone());
        return client;
    }

    @Override
    public Client update(Client client) {
        String sql = "UPDATE clients SET clientname = ?, email = ? WHERE clientid = ?";
        jdbcTemplate.update(sql, client.getClientName(), client.getEmail(), client.getClientId());
        return client;
    }

    @Override
    public List<Client> findAll(Long clientId) {
        if (clientId != null) {
            Optional<Client> clientOptional = findById(clientId);
            return clientOptional.map(Collections::singletonList).orElse(Collections.emptyList());
        } else {
            String sql = "SELECT * FROM clients";
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Client.class));
        }
    }
    
    @Override
    public Optional<Client> findById(long clientId) {
        String sql = "SELECT * FROM clients WHERE clientId = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Client.class), clientId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        String sql = "SELECT * FROM clients WHERE email = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Client.class), email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int delete(long clientId) {
        String sql = "DELETE FROM clients WHERE clientId = ?";
        return jdbcTemplate.update(sql, clientId);
    }
}
