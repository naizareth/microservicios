// package com.empowerbiz.clientsservice.controller;

// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import java.util.Optional;

// import org.springframework.http.MediaType;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.modelmapper.ModelMapper;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import com.empowerbiz.clientsservice.dto.ClientDTO;
// import com.empowerbiz.clientsservice.model.Client;
// import com.empowerbiz.clientsservice.service.IClientService;
// import com.empowerbiz.clientsservice.validators.ClientValidator;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.ArgumentMatchers.anyString;

// @WebMvcTest(ClientController.class)
// public class ClientContollerTest {

//         @Autowired
//         private ClientValidator validator; 

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private IClientService service;

//     @MockBean
//     private ModelMapper modelMapper;

//     @Autowired
//     private ObjectMapper objectMapper;

//     Client CLIENT_1 = new Client(1, "jose", "jors@gmail.com", "alcala la real ", "2035987");

//     ClientDTO CLIENTDTO_1 = new ClientDTO( null, "jose", "jors@gmail.com", "alcala la real ", "2035987");

//     @Test
//     public void testGetClients() throws Exception {
//         mockMvc.perform(MockMvcRequestBuilders
//                 .get("/clients")
//                 .content(MediaType.APPLICATION_JSON_VALUE))
//                 .andExpect(status().isOk());
//     }
//     @Test
//     public void testUpdate() throws Exception {
//         // Simula el comportamiento del servicio para devolver un cliente existente cuando se llama a findById
//         Mockito.when(service.findById(CLIENTDTO_1.getClientId())).thenReturn(Optional.of(CLIENT_1));
//         // Simula el comportamiento del servicio para devolver un cliente vacío cuando se llama a findByEmail
//         Mockito.when(service.findByEmail(anyString())).thenReturn(Optional.empty());
//         Mockito.when(service.update(any())).thenReturn(CLIENT_1);
    
//         // Construye la solicitud PUT
//         MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
//                 .put("/clients/" + CLIENTDTO_1.getClientId())
//                 .contentType(MediaType.APPLICATION_JSON_VALUE)
//                 .accept(MediaType.APPLICATION_JSON_VALUE)
//                 .content(objectMapper.writeValueAsString(CLIENTDTO_1));
    
//         // Realiza la solicitud PUT y espera un estado 200 (OK)
//         mockMvc.perform(mockRequest)
//                 .andExpect(status().isOk());
    
//         // Actualiza la simulación para devolver un cliente vacío cuando se llama a findById
//         Mockito.when(service.findById(CLIENTDTO_1.getClientId())).thenReturn(Optional.empty());
    
//         // Construye de nuevo la solicitud PUT (esto es importante para asegurar que la solicitud PUT se actualice)
//         mockRequest = MockMvcRequestBuilders
//                 .put("/clients/" + CLIENTDTO_1.getClientId())
//                 .contentType(MediaType.APPLICATION_JSON_VALUE)
//                 .accept(MediaType.APPLICATION_JSON_VALUE)
//                 .content(objectMapper.writeValueAsString(CLIENTDTO_1));
    
//         // Realiza la solicitud PUT nuevamente y espera un estado 404 (Not Found)
//         mockMvc.perform(mockRequest)
//                 .andExpect(status().isNotFound());
//     }
    


//     /*public void testUpdate() throws Exception {
//         // Simula el comportamiento del servicio para devolver un cliente existente cuando se llama a findById
//         Mockito.when(service.findById(CLIENTDTO_1.getClientId())).thenReturn(Optional.of(CLIENT_1));
//         // Simula el comportamiento del servicio para devolver un cliente vacío cuando se llama a findByEmail
//         Mockito.when(service.findByEmail(anyString())).thenReturn(Optional.empty());
//         Mockito.when(service.update(any())).thenReturn(CLIENT_1);
    
//         // Construye la solicitud PUT
//         MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
//                 .put("/clients/" + CLIENTDTO_1.getClientId())
//                 .contentType(MediaType.APPLICATION_JSON_VALUE)
//                 .accept(MediaType.APPLICATION_JSON_VALUE)
//                 .content(objectMapper.writeValueAsString(CLIENTDTO_1));
    
//         // Realiza la solicitud PUT y espera un estado 200 (OK)
//         mockMvc.perform(mockRequest)
//                 .andExpect(status().isOk());
    
//         // Actualiza la simulación para devolver un cliente vacío cuando se llama a findById
//         Mockito.when(service.findById(CLIENTDTO_1.getClientId())).thenReturn(Optional.empty());
    
//         // Construye de nuevo la solicitud PUT (esto es importante para asegurar que la solicitud PUT se actualice)
//         mockRequest = MockMvcRequestBuilders
//                 .put("/clients/" + CLIENTDTO_1.getClientId())
//                 .contentType(MediaType.APPLICATION_JSON_VALUE)
//                 .accept(MediaType.APPLICATION_JSON_VALUE)
//                 .content(objectMapper.writeValueAsString(CLIENTDTO_1));
    
//         // Realiza la solicitud PUT nuevamente y espera un estado 404 (Not Found)
//         mockMvc.perform(mockRequest)
//                 .andExpect(status().isNotFound());
//     }
    
    
//     @Test
//     public void testUpdateClientNotFound() throws Exception {
//         Mockito.when(service.findByEmail(anyString())).thenReturn(Optional.empty());

//         // Construye la solicitud PUT
//         MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
//                 .put("/clients/" + CLIENTDTO_1.getClientId())
//                 .contentType(MediaType.APPLICATION_JSON_VALUE)
//                 .accept(MediaType.APPLICATION_JSON_VALUE)
//                 .content(objectMapper.writeValueAsString(CLIENTDTO_1));

//         // Realiza la solicitud PUT y espera un estado 404 (Not Found)
//         mockMvc.perform(mockRequest)
//                 .andExpect(status().isNotFound());
//     }*/

//     @Test
//     public void testCreate() throws Exception {
//         // Simula el comportamiento del servicio y el mapeo
//         Mockito.when(service.findByEmail(anyString())).thenReturn(Optional.empty());
//         Mockito.when(service.save(any())).thenReturn(CLIENT_1);
//         Mockito.when(modelMapper.map(CLIENT_1, ClientDTO.class)).thenReturn(CLIENTDTO_1);

//         // Construye la solicitud POST
//         MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
//                 .post("/clients")
//                 .contentType(MediaType.APPLICATION_JSON_VALUE)
//                 .accept(MediaType.APPLICATION_JSON_VALUE)
//                 .content(objectMapper.writeValueAsString(CLIENTDTO_1));

//         // Realiza la solicitud y verifica el estado de la respuesta
//         mockMvc.perform(mockRequest)
//                 .andExpect(status().isCreated());
//     }

//     @Test
//     public void testDeleteClientFound() throws Exception {
//         // Simula el comportamiento del servicio
//         Mockito.when(service.findById(anyLong())).thenReturn(Optional.of(CLIENT_1));

//         // Realiza la solicitud DELETE
//         mockMvc.perform(MockMvcRequestBuilders
//                 .delete("/clients/" + CLIENT_1.getClientId())
//                 .contentType(MediaType.APPLICATION_JSON_VALUE))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void testDeleteClientNotFound() throws Exception {
//         // Simula el comportamiento del servicio
//         Mockito.when(service.findById(anyLong())).thenReturn(Optional.empty());

//         // Realiza la solicitud DELETE
//         mockMvc.perform(MockMvcRequestBuilders
//                 .delete("/clients/" + CLIENT_1.getClientId())
//                 .contentType(MediaType.APPLICATION_JSON_VALUE))
//                 .andExpect(status().isNotFound());
//     }

// }
