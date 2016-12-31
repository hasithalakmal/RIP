package com.rip.api.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.rip.api.common.*;
import com.rip.api.dto.BasicHibernateConfigurations;
import com.rip.api.dto.ConfigDetails;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kasun Eranga on 10/3/2016.
 */
@RestController
@Component
public class RESTAPIGenerator {

    private static final Logger LOGGER = Logger.getLogger(RESTAPIGenerator.class);
    String msg = null;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser jsonParser = new JsonParser();

    @RequestMapping(value = "rest-api", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> generateRESTAPI(@RequestBody String ripJsonString) {

        String template = "";
        JSONObject configJSON = null;
        Map<String, Object> hibernateConfigData = new HashMap<>();

        try {
            ConfigDetails configDetails = ConfigDetailExtractor.getConfigDetails(ripJsonString);
            template = Common.getTemplate(configDetails.getProgrammingLanguage(), configDetails.getRestFramework());
            configJSON = Common.getConfigurationJSON(configDetails.getPackageName(), configDetails.getModelPackageName(),configDetails.getProjectName());

            AbstractFileWriter.writeToFile(configDetails.getInputJsonPath(), gson.toJson(jsonParser.parse(configDetails.getRestAPISpecification().toString()))); // writing RIP REST API Specification to a JSON
            AbstractFileWriter.writeToFile(configDetails.getConfigJsonPath(), gson.toJson(jsonParser.parse(configJSON.toString()))); // writing Configuration details to a JSON
            AbstractProcessBuilder.buildProcess(null, "java -jar " + configDetails.getGeneratorPath() + " generate -i " + configDetails.getInputJsonPath() + " -l " + template + " -o " + configDetails.getOutputPath() + " -s -c " + configDetails.getConfigJsonPath()); // generate REST API using swagger
            AbstractProcessBuilder.buildProcess(null, "java -jar " + configDetails.getGeneratorPath() + " generate -i " + configDetails.getInputJsonPath() + " -l html -o " + configDetails.getOutputPathDocs() + "//API-Doc -s -c " + configDetails.getConfigJsonPath()); // generate REST API DOC
            //AbstractProcessBuilder.buildProcess(new File(configDetails.getPostmanGeneratorPath()), "java -jar " + "swagger2postman.jar " + configDetails.getInputJsonPath() + " " + configDetails.getPostmanCollectionPath() + " Content-Type=application/json"); // generate Postman Test collection

            if (configDetails.getTechnicalSpecification().getString("orm_framework").equals("hibernate")) {
                hibernateConfigData.put("package", configDetails.getPackageName().substring(0, configDetails.getPackageName().lastIndexOf('.')));
                BasicHibernateConfigurations configData = new BasicHibernateConfigurations(configDetails.getTechnicalSpecification().getString("dbms"), configDetails.getDatabaseDesign().getString("db_name"), configDetails.getResourcesPath(), configDetails.getProjectUtilPath(), configDetails.getPackageName(), hibernateConfigData, configDetails.getDatabaseDesign());
                HibernateGenerator.generateBasicHibernateConfigurations(configData);
                HibernateGenerator.generateHibernateEntityClasses(configDetails.getPackageName(), configDetails.getOutputPath(), configDetails.getDatabaseDesign(), configDetails.getRestAPISpecification().getJSONObject("paths"));
            }

            Common.cleanProject(configDetails);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            msg = "{" + "    \"response\": \"Error\"" + "  }";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }

        msg = "{" + "    \"response\": \"Success\"" + "  }";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
