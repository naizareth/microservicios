package com.empowerbiz.clientsservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.empowerbiz.clientsservice.dto.ClientDTO;
import com.empowerbiz.clientsservice.model.Client;
import com.empowerbiz.clientsservice.service.IClientService;
import com.empowerbiz.clientsservice.validators.ClientValidator;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private IClientService service;

    @Autowired
    private ModelMapper mapper;

    private ClientValidator clientValidator;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getClients(@RequestParam(required = false) Long clientId) {
        List<Client> clients = service.findAll(clientId);
        List<ClientDTO> clientDTOs = clients.stream()
                .map(client -> mapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(clientDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long clientId) {
        // Validar que el cliente exista antes de eliminarlo
        clientValidator.validateClientExists(clientId);

        // Proceder a eliminar el cliente
        service.delete(clientId);

        // Devolver una respuesta con un mensaje de éxito
        Map<String, String> response = new HashMap<>();
        response.put("message", "Cliente eliminado exitosamente");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody ClientDTO dto) {
        // Validar que el correo electrónico no esté en uso
        clientValidator.validateEmailNotInUse(dto.getEmail());
        
        // Guardar el nuevo cliente
        Client createdClient = service.save(mapper.map(dto, Client.class));

        // Devolver un ResponseEntity con el objeto creado
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(createdClient, ClientDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable("id") long clientId, @Validated @RequestBody ClientDTO dto) {
        // Validar que el cliente exista
        Client existingClient = clientValidator.validateClientExists(clientId);

        // Verificar la unicidad del correo electrónico
        clientValidator.checkEmailUniqueness(dto.getEmail(), existingClient, clientId);

        // Mapear los datos del DTO al objeto Client
        Client mapClient = mapper.map(dto, Client.class);

        // Establecer el ID del cliente en el objeto Client
        mapClient.setClientId(clientId);

        // Actualizar el cliente en el servicio
        Client obj = service.update(mapClient);

        // Devolver la respuesta HTTP con el cliente actualizado mapeado a DTO
        return ResponseEntity.ok().body(mapper.map(obj, ClientDTO.class));
    }
}
