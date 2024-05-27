package com.empowerbiz.clientsservice.repository;
import java.util.List;
import com.empowerbiz.clientsservice.model.Client;



public interface IClientRepository {

  Client save(Client client) ;
    Client update(Client client) ;
    List<Client> findAll(Long clientId) ;
    Client findById(long clientId);
    int delete(long clientId) ;
    Client findByEmail(String email) ;
}