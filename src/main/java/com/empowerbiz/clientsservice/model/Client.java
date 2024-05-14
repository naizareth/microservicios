package com.empowerbiz.clientsservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Client {

private long clientId;

private String clientName;

private String email;

private String address;

private String phone;
    
}
