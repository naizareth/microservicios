package com.empowerbiz.clientsservice.repository.impl;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.empowerbiz.clientsservice.model.Client;
import com.empowerbiz.clientsservice.repository.IClientRepository;

@Repository
public class ClientRepositoryimpl implements IClientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Client create(Client client) throws Exception {
        String sql = "INSERT INTO clients (clientname, email,address,phone) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, client.getClientName(), client.getEmail(), client.getAddress(), client.getPhone());
        return client;
    }

    @Override
    public Client update(Client client) throws Exception {
        String sql = "UPDATE clients SET clientname = ?, email = ? WHERE clientid = ?";
        jdbcTemplate.update(sql, client.getClientName(), client.getEmail(), client.getClientId());
        return client;
    }

    @Override
    public List<Client> findAll(Long clientId) throws Exception {
        if (clientId != null) {
            return Collections.singletonList(findById(clientId));
        } else {
            String sql = "SELECT * FROM clients";
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Client.class));
        }
    }

    @Override
    public Client findById(long clientId) throws Exception {
        String sql = "SELECT * FROM clients WHERE clientId = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Client.class), clientId);

    }

    @Override
    public int delete(long clientId) throws Exception {
        String sql = "DELETE FROM clients WHERE clientId = ?";
        return jdbcTemplate.update(sql, clientId);
    }

}
