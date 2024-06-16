package com.empowerbiz.clientsservice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.empowerbiz.clientsservice.dto.ClientDTO;
import com.empowerbiz.clientsservice.model.Client;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ClientMapper {

    private final ModelMapper mapper;

    public Client toModel(ClientDTO dto) {
        return mapper.map(dto, Client.class);
    }

    public ClientDTO toDTO (Client client) {
        
        return mapper.map(client,ClientDTO.class);
    }


    public List<ClientDTO> toDtoList(List<Client> clients) {
        return clients.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<Client> toModelList(List<ClientDTO> clientDTOs) {
        return clientDTOs.stream().map(this::toModel).collect(Collectors.toList());
    }
}
