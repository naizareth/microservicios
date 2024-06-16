package com.empowerbiz.clientsservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.empowerbiz.clientsservice.dto.ClientDTO;
import com.empowerbiz.clientsservice.model.Client;
import com.empowerbiz.clientsservice.service.ClientManagementService;
import com.empowerbiz.clientsservice.service.IClientService;
import com.empowerbiz.clientsservice.util.Mesagges;
import com.empowerbiz.clientsservice.util.Paths;
import com.empowerbiz.clientsservice.validators.ClientValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(Paths.ACCESS_PATH)
public class ClientController {

    private final IClientService service;

    private final ModelMapper mapper;

    private final ClientValidator clientValidator;

    private final ClientManagementService clientManagementService;


    @GetMapping
    public ResponseEntity<List<ClientDTO>> getClients(@RequestParam(required = false) Long clientId) {
        List<Client> clients = service.findAll(clientId);
        List<ClientDTO> clientDTOs = clients.stream()
                .map(client -> mapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(clientDTOs, HttpStatus.OK);
    }

    @DeleteMapping(Paths.ID_PATH)
    public ResponseEntity<Object> delete(@PathVariable("id") long clientId) {
        clientValidator.validateClientExists(clientId);

        service.delete(clientId);

        Map<String, String> response = new HashMap<>();
        response.put("message", Mesagges.DELETED);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@Validated @RequestBody ClientDTO dto) {
        return clientManagementService.createClient(dto); 
    }

    @PutMapping(Paths.ID_PATH)
    public ResponseEntity<ClientDTO> update(@PathVariable("id") long clientId, @Validated @RequestBody ClientDTO dto) {   
        return clientManagementService.updateClient(clientId,dto);
    }
}
