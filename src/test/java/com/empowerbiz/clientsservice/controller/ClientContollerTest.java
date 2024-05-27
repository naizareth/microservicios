package com.empowerbiz.clientsservice.controller;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.empowerbiz.clientsservice.service.IClientService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ClientController.class)
public class ClientContollerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IClientService service ;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void testGetClients() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/clients")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

}
