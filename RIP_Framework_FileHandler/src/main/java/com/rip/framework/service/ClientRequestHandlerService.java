package com.rip.framework.service;

public interface ClientRequestHandlerService {

    String createClientRequest(String design);

    String deleteClientRequest(String projectName, String version);

    String updateClientRequest(String projectName, String version);

    String selectClientRequest(String projectName, String version);
    
    String genarateClientResponse(String RIP_JSON);
    
}
