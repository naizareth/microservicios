package com.empowerbiz.clientsservice.repository;



import java.util.List;
import com.empowerbiz.clientsservice.model.Client;



public interface IClientRepository {

  Client save(Client client) throws Exception;
    Client update(Client client) throws Exception;
    List<Client> readAll() throws Exception;
    Client readById(long id) throws Exception;
    void delete(long id) throws Exception;

}