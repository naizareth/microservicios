package com.empowerbiz.clientsservice.repository.impl;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.sql.ResultSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.SQLException;
import com.empowerbiz.clientsservice.model.Client;
import com.empowerbiz.clientsservice.repository.IClientRepository;

public class ClientRepositoryimpl implements IClientRepository {
   
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private ModelMapper modelMapper;

    public ClientRepositoryimpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
        
    }

    @Override
    public Client save(Client client) throws Exception {
        String sql = "INSERT INTO clients (clientname, email,address,phone) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, client.getClientName(), client.getEmail(),client.getAddress(),client.getPhone());
        return client;
    }

    @Override
    public Client update(Client client) throws Exception {
        String sql = "UPDATE clients SET name = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, client.getClientName(), client.getEmail(), client.getClientId());
        return client;
    }

    @Override
    public List<Client> readAll() throws Exception {
        String sql = "SELECT * FROM clients";
        return jdbcTemplate.query(sql, getClientRowMapper());
    }

    @Override
    public Client readById(long id) throws Exception {
        String sql = "SELECT * FROM clients WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, getClientRowMapper(), id);
    
    }

    @Override
    public void delete(long id) throws Exception {
        String sql = "DELETE FROM clients WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        return modelMapper.map(rs, Client.class);
    }
   
 }

