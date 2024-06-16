package com.empowerbiz.clientsservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.empowerbiz.clientsservice.dto.ClientDTO;
import com.empowerbiz.clientsservice.service.ClientManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ClientControllerTests {

    @Mock
    private ClientManagementService clientManagementService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetClientsWithNullId() {

        List<ClientDTO> mockClients = Arrays.asList(
            new ClientDTO(1L, "John Doe", "john@gmail.com", "Barquisimeto", "04125546153"),
            new ClientDTO(2L, "John Doe", "karla@gmail.com", "Guanare", "04241146141"),
            new ClientDTO(3L, "John Doe", "maria@gmail.com", "Caracas", "041642346152")
        );

        ResponseEntity<List<ClientDTO>> mockResponse = new ResponseEntity<>(mockClients, HttpStatus.OK);

        when(clientManagementService.getClient(null)).thenReturn(mockResponse);

        ResponseEntity<List<ClientDTO>> response = clientController.getClients(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(mockClients, response.getBody());
    }

    @Test
    public void testGetClients() {

        List<ClientDTO> mockClients = Collections.singletonList(new ClientDTO());

        ResponseEntity<List<ClientDTO>> mockResponse = new ResponseEntity<>(mockClients, HttpStatus.OK);

        when(clientManagementService.getClient(anyLong())).thenReturn(mockResponse);

        ResponseEntity<List<ClientDTO>> response = clientController.getClients(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(mockClients, response.getBody());
    }

    @Test
    public void testCreateClient() {

        ClientDTO mockClientDTO = new ClientDTO();

        ResponseEntity<ClientDTO> mockResponse = new ResponseEntity<>(mockClientDTO, HttpStatus.CREATED);

        when(clientManagementService.createClient(any(ClientDTO.class))).thenReturn(mockResponse);

        ResponseEntity<ClientDTO> response = clientController.create(mockClientDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertEquals(mockClientDTO, response.getBody());
    }

    @Test
    public void testUpdateClient() {

        long clientId = 1L;

        ClientDTO mockClientDTO = new ClientDTO();

        ResponseEntity<ClientDTO> mockResponse = new ResponseEntity<>(mockClientDTO, HttpStatus.OK);

        when(clientManagementService.updateClient(eq(clientId), any(ClientDTO.class))).thenReturn(mockResponse);

        ResponseEntity<ClientDTO> response = clientController.update(clientId, mockClientDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(mockClientDTO, response.getBody());
    }

    @Test
    public void testDeleteClient() {

        long clientId = 1L;

        ResponseEntity<Object> mockResponse = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        when(clientManagementService.delateClient(clientId)).thenReturn(mockResponse);

        ResponseEntity<Object> response = clientController.delete(clientId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
