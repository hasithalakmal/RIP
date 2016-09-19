package com.rip.framework.mongodao;

public interface ClientRequestHandlerDao {

    String createClientRequest(String design);

    String deleteClientRequest(String projectName, String version);

    String updateClientRequest(String projectName, String version);

    String selectClientRequest(String projectName, String version);
}
