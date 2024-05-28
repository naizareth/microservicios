package com.empowerbiz.clientsservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.empowerbiz.clientsservice.dto.ClientDTO;
import com.empowerbiz.clientsservice.exception.ModelNotFoundException;
import com.empowerbiz.clientsservice.model.Client;
import com.empowerbiz.clientsservice.service.IClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private IClientService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getClients(@RequestParam(required = false) Long clientId) throws Exception {
        try {
            List<Client> clients = service.findAll(clientId);
            List<ClientDTO> clientDTOs = clients.stream()
                    .map(client -> mapper.map(client, ClientDTO.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(clientDTOs, HttpStatus.OK);
        } catch (Exception e) {
            throw new ModelNotFoundException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long clientId) {
        try {
            Optional<Client> client = service.findById(clientId);
            if (client.isEmpty()) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Cliente no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Si el cliente existe, proceder a eliminarlo
            service.delete(clientId);

            // Devolver una ResponseEntity con el código de estado 204 (No Content) y un
            Map<String, String> response = new HashMap<>();
            response.put("message", "Cliente eliminado exitosamente");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (ModelNotFoundException e) {
            throw new ModelNotFoundException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody ClientDTO dto) {
        try {
            Optional<Client> existingClientOptional = service.findByEmail(dto.getEmail());

            if (existingClientOptional.isPresent()) {
                throw new Exception("Ya existe un cliente con el mismo correo electrónico");
            }

            service.save(mapper.map(dto, Client.class));

            // Busca el cliente recién creado por su nombre
            Optional<Client> createdClient = service.findByEmail(dto.getEmail());

            // Verifica si se encontró el producto recién creado
            if (createdClient.isEmpty()) {
                throw new ModelNotFoundException("No se pudo encontrar el producto recién creado");
            }

            // Devolver un ResponseEntity con el objeto creado
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(createdClient, ClientDTO.class));
        } catch (Exception e) {
            throw new ModelNotFoundException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable("id") long clientId, @Validated @RequestBody ClientDTO dto) {
        try {
            // Buscar el cliente existente por su ID
            Optional<Client> existingClientOptional = service.findById(clientId);

            // Verificar si el cliente existe
            if (existingClientOptional.isEmpty()) {
                throw new ModelNotFoundException("Cliente no encontrado");
            }

            Client existingClient = existingClientOptional.get();
            // Si el correo electrónico actualizado es diferente del correo electrónico
            // original
            if (!existingClient.getEmail().equals(dto.getEmail())) {
                // Verificar si ya existe un cliente con el nuevo correo electrónico
                Optional<Client> existingWithEmail = service.findByEmail(dto.getEmail());
                if (existingWithEmail.isPresent() && existingWithEmail.get().getClientId() != clientId) {
                    throw new ModelNotFoundException("Ya existe un cliente con el mismo correo electrónico");
                }
            }

            // Actualizar el cliente
            Client updatedClient = mapper.map(dto, Client.class);
            updatedClient.setClientId(clientId);
            Client obj = service.update(updatedClient);
            return ResponseEntity.ok().body(mapper.map(obj, ClientDTO.class));
        } catch (Exception e) {
            throw new ModelNotFoundException(e.getMessage());
        }
    }
}