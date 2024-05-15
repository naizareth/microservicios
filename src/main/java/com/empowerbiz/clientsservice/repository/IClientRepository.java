package com.empowerbiz.clientsservice.repository;
import java.util.List;
import com.empowerbiz.clientsservice.model.Client;



public interface IClientRepository {

  Client save(Client client) throws Exception;
    Client update(Client client) throws Exception;
    List<Client> findAll(Long clientId) throws Exception;
    Client findById(long clientId) throws Exception;
    int delete(long clientId) throws Exception;
}