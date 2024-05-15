package com.empowerbiz.clientsservice.controller;
import java.util.List;
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
    private IClientService clientService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getClients(@RequestParam(required = false) Long clientId) {
        try {
            List<Client> clients = clientService.findAll(clientId);
            List<ClientDTO> clientDTOs = clients.stream()
                    .map(client -> mapper.map(client, ClientDTO.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(clientDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> delete(@PathVariable("clientId") long clientId) throws Exception {
        Client obj = clientService.findById(clientId);

        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + clientId);
        }
        clientService.delete(clientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

     @PostMapping
    public ResponseEntity<ClientDTO> create(@Validated @RequestBody ClientDTO dto) throws Exception{
        Client obj = clientService.create(mapper.map(dto, Client.class));
        return new ResponseEntity<>(mapper.map(obj, ClientDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ClientDTO> update(@Validated @RequestBody ClientDTO dto) throws Exception{
        Client obj = clientService.update(mapper.map(dto, Client.class));
        return new ResponseEntity<>(mapper.map(obj, ClientDTO.class), HttpStatus.OK);
    }



}
