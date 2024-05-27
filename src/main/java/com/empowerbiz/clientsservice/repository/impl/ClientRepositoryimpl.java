package com.empowerbiz.clientsservice.repository.impl;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.empowerbiz.clientsservice.exception.ModelNotFoundException;
import com.empowerbiz.clientsservice.model.Client;
import com.empowerbiz.clientsservice.repository.IClientRepository;

@Repository
public class ClientRepositoryimpl implements IClientRepository {
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
            return Collections.singletonList(findById(clientId));
        } else {
            String sql = "SELECT * FROM clients";
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Client.class));
        }
    }

    @Override
    public Client findById(long clientId)  {

        try {
            String sql = "SELECT * FROM clients WHERE clientId = ?";
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Client.class), clientId);
            
        } catch (EmptyResultDataAccessException e) {
            throw new ModelNotFoundException("Cliente no encontrado");
        }
    }
    @Override
    public Client findByEmail(String email) {

        
            Client client = jdbcTemplate.queryForObject("SELECT * FROM clients WHERE email=?",
                BeanPropertyRowMapper.newInstance(Client.class), email);

            return client;
       
  }

    @Override
    public int delete(long clientId)  {
        String sql = "DELETE FROM clients WHERE clientId = ?";
        return jdbcTemplate.update(sql, clientId);
    }

}