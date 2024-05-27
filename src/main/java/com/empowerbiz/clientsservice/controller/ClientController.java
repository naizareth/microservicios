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
import com.empowerbiz.clientsservice.repository.IClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getClients(@RequestParam(required = false) Long clientId) {
        try {
            List<Client> clients = clientRepository.findAll(clientId);
            List<ClientDTO> clientDTOs = clients.stream()
                    .map(client -> mapper.map(client, ClientDTO.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(clientDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     
   @DeleteMapping("/{id}")
public ResponseEntity<?> delete(@PathVariable("id") long clientId) {
    try {
        // Buscar el cliente por su ID
        clientRepository.findById(clientId);
        
        // Si el cliente existe, proceder a eliminarlo
        clientRepository.delete(clientId);
        
        // Devolver una ResponseEntity con el código de estado 204 (No Content) y un objeto JSON con el mensaje
        Map<String, String> response = new HashMap<>();
        response.put("message", "Cliente eliminado exitosamente");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    } catch (ModelNotFoundException e) {
        // Capturar la excepción ModelNotFoundException y devolver una ResponseEntity con el código de estado 404 (Not Found) y un objeto JSON con el mensaje de error
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}

    
    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody ClientDTO dto) {
        try {
            Client existingClient = clientRepository.findByEmail(dto.getEmail());

            if (existingClient != null) {
                // Si ya existe un cliente con el mismo correo electrónico, lanzar una excepción con el mensaje de error
                throw new Exception("Ya existe un cliente con el mismo correo electrónico");
            }
            
            // Si no existe un cliente con el mismo correo electrónico, proceder a crear uno nuevo
            Client obj = clientRepository.save(mapper.map(dto, Client.class));
            // Devolver un ResponseEntity con el objeto creado
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(obj, ClientDTO.class));
        } catch (Exception e) {
            // Capturar cualquier excepción y devolver un ResponseEntity con un código de estado de error y el mensaje de la excepción
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<ClientDTO> update(@Validated @RequestBody ClientDTO dto) throws Exception{
        Client obj = clientRepository.update(mapper.map(dto, Client.class));
        return new ResponseEntity<>(mapper.map(obj, ClientDTO.class), HttpStatus.OK);
    }



}