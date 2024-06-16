package com.empowerbiz.clientsservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.empowerbiz.clientsservice.dto.ClientDTO;
import com.empowerbiz.clientsservice.service.ClientManagementService;
import com.empowerbiz.clientsservice.util.Paths;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(Paths.ACCESS_PATH)
public class ClientController {

    private final ClientManagementService clientManagementService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getClients(@RequestParam(required = false) Long clientId) {
        return clientManagementService.getClient(clientId);
    }

    @DeleteMapping(Paths.ID_PATH)
    public ResponseEntity<Object> delete(@PathVariable("id") long clientId) {
        return clientManagementService.delateClient(clientId);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@Validated @RequestBody ClientDTO dto) {
        return clientManagementService.createClient(dto);
    }

    @PutMapping(Paths.ID_PATH)
    public ResponseEntity<ClientDTO> update(@PathVariable("id") long clientId, @Validated @RequestBody ClientDTO dto) {
        return clientManagementService.updateClient(clientId, dto);
    }
}
