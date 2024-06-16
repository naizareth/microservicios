package com.empowerbiz.clientsservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.empowerbiz.clientsservice.dto.ClientDTO;
import com.empowerbiz.clientsservice.mapper.ClientMapper;
import com.empowerbiz.clientsservice.model.Client;
import com.empowerbiz.clientsservice.util.Mesagges;
import com.empowerbiz.clientsservice.validators.ClientValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientManagementService {

    private final IClientService service;

    private final ClientMapper mapper;

    private final ClientValidator clientValidator;

    private final ResponseService responseService;

    public ResponseEntity<ClientDTO> createClient(ClientDTO dto) {

        clientValidator.validateEmailNotInUse(dto.getEmail());

        Client createdClient = service.save(mapper.toModel(dto));

        return responseService.createResponse(mapper.toDTO(createdClient));
    }

    public ResponseEntity<ClientDTO> updateClient(long clientId, ClientDTO dto) {

        Client existingClient = clientValidator.validateClientExists(clientId);

        clientValidator.checkEmailUniqueness(dto.getEmail(), existingClient, clientId);

        Client mapClient = mapper.toModel(dto);

        mapClient.setClientId(clientId);

        Client obj = service.update(mapClient);

        return responseService.updateResponse(mapper.toDTO(obj));
    }

    public ResponseEntity<Object> delateClient(Long clientId) {
        clientValidator.validateClientExists(clientId);

        service.delete(clientId);

        Map<String, String> response = new HashMap<>();

        response.put("message", Mesagges.DELETED);

        return responseService.deleteResponse(response);
    }

    public ResponseEntity<List<ClientDTO>> getClient(Long clientId) {
        List<Client> clients = service.findAll(clientId);

        List<ClientDTO> clientDTO = mapper.toDtoList(clients);

        return responseService.getClient(clientDTO);
    }
}
