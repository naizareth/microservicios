package com.empowerbiz.clientsservice.util;

public class SqlQueries {
    public static final String UPDATE_CLIENT = "UPDATE clients SET clientname = ?, email = ?, address = ? , phone = ? WHERE clientid = ?";
    public static final String INSERT_CLIENT = "INSERT INTO clients (clientname, email,address,phone) VALUES (?, ?, ?, ?)";
    public static final String SELECT_CLIENT_FINDALL = "SELECT * FROM clients";
    public static final String SELECT_CLIENT_FINDBYID = "SELECT * FROM clients WHERE clientId = ?";
    public static final String SELECT_CLIENT_FINDBYEMAIL = "SELECT * FROM clients WHERE email = ?";
    public static final String DELETE_CLIENT = "DELETE FROM clients WHERE clientId = ?";

}
