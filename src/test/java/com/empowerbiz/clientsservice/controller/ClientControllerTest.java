package com.empowerbiz.clientsservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.empowerbiz.clientsservice.dto.ClientDTO;
import com.empowerbiz.clientsservice.model.Client;
import com.empowerbiz.clientsservice.service.IClientService;
import com.empowerbiz.clientsservice.validators.ClientValidator;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @Mock
    private IClientService service;

    @Mock
    private ModelMapper mapper;

    @Mock
    private ClientValidator clientValidator;

    @InjectMocks
    private ClientController controller;

    @SuppressWarnings("null")
    @Test
    public void testGetClients() {
        // Arrange
        List<Client> clients = Arrays.asList(new Client(), new Client());
        when(service.findAll(null)).thenReturn(clients);

        // Act
        ResponseEntity<List<ClientDTO>> response = controller.getClients(null);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clients.size(), response.getBody().size());
    }

    @SuppressWarnings("null")
    @Test
    public void testDelete() {
        // Arrange
        long clientId = 1L;

        // Act
        ResponseEntity<Object> response = controller.delete(clientId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cliente eliminado exitosamente", ((Map<?, ?>) response.getBody()).get("message"));
        verify(clientValidator).validateClientExists(clientId);
        verify(service).delete(clientId);
    }

    @Test
    public void testCreate() {
        // Arrange
        ClientDTO clientDTO = new ClientDTO();
        Client client = new Client();
        when(mapper.map(clientDTO, Client.class)).thenReturn(client);
        when(service.save(client)).thenReturn(client);

        // Act
        ResponseEntity<Object> response = controller.create(clientDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(clientValidator).validateEmailNotInUse(clientDTO.getEmail());
        verify(service).save(client);
    }

    @Test
    public void testUpdate() {
        // Arrange
        long clientId = 1L;
        ClientDTO clientDTO = new ClientDTO();
        Client client = new Client();
        when(clientValidator.validateClientExists(clientId)).thenReturn(client);
        when(mapper.map(clientDTO, Client.class)).thenReturn(client);
        when(service.update(client)).thenReturn(client);

        // Act
        ResponseEntity<ClientDTO> response = controller.update(clientId, clientDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(clientValidator).validateClientExists(clientId);
        verify(clientValidator).checkEmailUniqueness(clientDTO.getEmail(), client, clientId);
        verify(service).update(client);
    }
}
