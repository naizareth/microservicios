package com.empowerbiz.clientsservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.empowerbiz.clientsservice.dto.ClientDTO;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ResponseService {

   public ResponseEntity<ClientDTO> createResponse(ClientDTO dto) {
      return ResponseEntity.status(HttpStatus.CREATED).body(dto);
   }

   public ResponseEntity<ClientDTO> updateResponse(ClientDTO dto) {
      return ResponseEntity.ok().body(dto);
   }

   public ResponseEntity<Object> deleteResponse(Object response) {
      return ResponseEntity.status(HttpStatus.OK).body(response);
   }

   public ResponseEntity<List<ClientDTO>> getClient(List<ClientDTO> clientDTOs) {
      return new ResponseEntity<>(clientDTOs, HttpStatus.OK);
   }
}
