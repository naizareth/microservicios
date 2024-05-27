package com.empowerbiz.clientsservice.controller;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.empowerbiz.clientsservice.dto.ClientDTO;
import com.empowerbiz.clientsservice.model.Client;
import com.empowerbiz.clientsservice.service.IClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;



@WebMvcTest(ClientController.class)
public class ClientContollerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IClientService service ;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;


    Client CLIENT_1 = new Client(1,"jose","jors@gmail.com","alcala la real ","2035987");

    ClientDTO CLIENTDTO_1 = new ClientDTO(1,"jose","jors@gmail.com","alcala la real ","2035987");

    @Test
    public void testGetClients() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/clients")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
    @Test
    public void testUpdate() throws Exception{
        Mockito.when(service.findById(anyLong())).thenReturn(CLIENT_1);
        Mockito.when(service.update(any())).thenReturn(CLIENT_1);
        Mockito.when(modelMapper.map(CLIENTDTO_1, ClientDTO.class)).thenReturn(CLIENTDTO_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
        .put("/clients")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(CLIENTDTO_1));

        mockMvc.perform(mockRequest)
        .andDo(print())
        .andExpect(status().isOk());
       
                
    }

    @Test
    public void testCreate() throws Exception{
        Mockito.when(service.save(any())).thenReturn(CLIENT_1);
        Mockito.when(modelMapper.map(CLIENTDTO_1, ClientDTO.class)).thenReturn(CLIENTDTO_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
        .post("/clients")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(CLIENTDTO_1));

        mockMvc.perform(mockRequest)
        .andDo(print())
        .andExpect(status().isCreated());
       
                
    }
    @Test
    public void testDelete()throws Exception{
        Mockito.when(service.findById(anyLong())).thenReturn(CLIENT_1);
      

        mockMvc.perform(MockMvcRequestBuilders
        .delete("/clients/" + CLIENT_1.getClientId())
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());
    }
}
