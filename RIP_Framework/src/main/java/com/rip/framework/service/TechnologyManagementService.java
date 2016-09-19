package com.rip.framework.service;

import java.util.ArrayList;

public interface TechnologyManagementService {

    String addNewTechnology(String technology);

    String deleteExsistingTechnology(String techName, String version);

    ArrayList getExsistingTechnologies();

    ArrayList getExsistingTechnologies(String catogery);

    String getExsistingTechnology(String techName, String version);

}
