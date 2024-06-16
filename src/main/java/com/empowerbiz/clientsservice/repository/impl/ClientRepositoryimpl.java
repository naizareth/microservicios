package com.empowerbiz.clientsservice.repository.impl;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.empowerbiz.clientsservice.model.Client;
import com.empowerbiz.clientsservice.repository.IClientRepository;
import com.empowerbiz.clientsservice.util.SqlQueries;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ClientRepositoryImpl implements IClientRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Client save(Client client) {

        String sql = SqlQueries.INSERT_CLIENT;
        jdbcTemplate.update(sql, client.getClientName(), client.getEmail(), client.getAddress(), client.getPhone());
        return client;
    }

    @Override
    public Client update(Client client) {
        
        String sql = SqlQueries.UPDATE_CLIENT;
        jdbcTemplate.update(sql, client.getClientName(), client.getEmail(), client.getAddress(), client.getPhone(),
                client.getClientId());
        return client;
    }

    @Override
    public List<Client> findAll(Long clientId) {
        if (clientId != null) {
            Optional<Client> clientOptional = findById(clientId);
            return clientOptional.map(Collections::singletonList).orElse(Collections.emptyList());
        } else {
            String sql = SqlQueries.SELECT_CLIENT_FINDALL;
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Client.class));
        }
    }

    @Override
    public Optional<Client> findById(long clientId) {
        String sql = SqlQueries.SELECT_CLIENT_FINDBYID;
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Client.class), clientId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        String sql = SqlQueries.SELECT_CLIENT_FINDBYEMAIL;
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Client.class), email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int delete(long clientId) {
        String sql = SqlQueries.DELETE_CLIENT;
        return jdbcTemplate.update(sql, clientId);
    }
}
