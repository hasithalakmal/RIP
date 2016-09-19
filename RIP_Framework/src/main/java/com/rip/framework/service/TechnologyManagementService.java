package com.rip.framework.service;

import java.util.ArrayList;

public interface TechnologyManagementService {

    String introduceNewTechnology(String technology);

    String removeExsistingTechnology(String userName, String user);

    ArrayList getExsistingTechnologies();

    ArrayList getExsistingTechnologies(String catogery);

    String getExsistingTechnology(String technologyName);

}
