package com.empowerbiz.clientsservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.empowerbiz.clientsservice.dto.ClientDTO;

import com.empowerbiz.clientsservice.repository.impl.ClientRepositoryimpl;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepositoryimpl clientRepository;

    @Autowired
    private ModelMapper mapper;

   
    @GetMapping
public ResponseEntity<List<ClientDTO>> readAll() throws Exception {
    List<ClientDTO> list = clientRepository.readAll().stream()
                                    .map(cat -> mapper.map(cat, ClientDTO.class))
                                    .collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
}

    }


