package com.empowerbiz.clientsservice.mapper;

import org.springframework.stereotype.Component;

import com.empowerbiz.clientsservice.dto.ClientDTO;
import com.empowerbiz.clientsservice.model.Client;

@Component
public class ClientMapper {

    public ClientDTO toDTO (Client client){
        if (client==null){
            return null;
        }
        return new ClientDTO(client.getClientId(),client.getClientName(),client.getEmail(),client.getAddress(),client.getPhone());
    }

    public Client toEntity (ClientDTO clientDTO){
        if (clientDTO==null){
            return null;
        }
        return new Client(clientDTO.getClientId(), clientDTO.getClientName(),clientDTO.getEmail(),clientDTO.getAddress(),clientDTO.getPhone());
    }
}
