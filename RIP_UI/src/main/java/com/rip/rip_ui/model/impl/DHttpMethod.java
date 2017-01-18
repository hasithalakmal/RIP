/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.model.impl;

import com.rip.rip_ui.core.text.DataLabel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javafx.collections.ObservableMap;

/**
 *
 * @author Supun
 */
public class DHttpMethod {

    private ArrayList<String> tags;
    private String summary;
    private String description;
    private String operationId;
    private ArrayList<String> produces;
    private ArrayList<DParameter> parameters;
    private HashMap<String, DResponse> responses;

    public DHttpMethod(HashMap<String, String> paramDetails) {

        initializeArraysandObjects();

        tags.add(paramDetails.get(DataLabel.RESOURCE_TABLE));

        setParameters(paramDetails);

    }

    private void setParameters(HashMap<String, String> paramDetails) {

        if (Integer.parseInt(paramDetails.get(DataLabel.PARAMETER_COUNT)) == 0) {
            
            DParameter dataParam = new DParameter(
                        paramDetails.get(DataLabel.PARAM_PREFIX + 0),
                        paramDetails.get(DataLabel.PARAMETER_TYPE),
                        paramDetails.get(DataLabel.DATA_TYPE_PREFIX)
            );
            
            if (paramDetails.get(DataLabel.PARAMETER_TYPE).equals("body")) {
                DSchema schema = new DSchema();
                schema.set$ref(getRefValue());
                dataParam.setSchema(schema);
            }
            
            parameters.add(dataParam);

        }

        for (int i = 0; i < Integer.parseInt(paramDetails.get(DataLabel.PARAMETER_COUNT)); i++) {

            DParameter dataParam = new DParameter(
                    paramDetails.get(DataLabel.PARAM_PREFIX + i),
                    paramDetails.get(DataLabel.PARAMETER_TYPE),
                    paramDetails.get(DataLabel.DATA_TYPE_PREFIX + i)
            );
            parameters.add(dataParam);
        }
    }

    public String getRefValue() {
        return DataLabel.REF_PREFIX + tags.get(0);
    }

    private void initializeArraysandObjects() {
        produces = new ArrayList<>();
        produces.add("application/json");

        parameters = new ArrayList<>();
        tags = new ArrayList<>();

        responses = new HashMap();

        responses.put("default", createDefaultResponse());
    }

    private DResponse createDefaultResponse() {
        DResponse defaultResponse = new DResponse();
        defaultResponse.setDescription(DataLabel.DEFAULT_RES_TITLE);

        DSchema schema = createSchema();
        defaultResponse.setSchema(schema);

        defaultResponse.getSchema().set$ref(DataLabel.RES_ERROR_MODEL);

        return defaultResponse;
    }

    private DResponse createHttpResponse(String description, String type) {
        DResponse httpResponse = new DResponse();
        httpResponse.setDescription(description);

        if (!type.equals("n/a")) {

            DSchema schema = createSchema();
            httpResponse.setSchema(schema);

            if (type.equals("array")) {

                httpResponse.getSchema().setType(type);
                HashMap<String, String> items = new HashMap();
                items.put("$ref", getRefValue());
                httpResponse.getSchema().setItems(items);

            } else {
                httpResponse.getSchema().set$ref(getRefValue());
            }

        }

        return httpResponse;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public ArrayList<String> getProduces() {
        return produces;
    }

    public void setProduces(ArrayList<String> produces) {
        this.produces = produces;
    }

    public ArrayList<DParameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<DParameter> parameters) {
        this.parameters = parameters;
    }

    public HashMap getResponsess() {
        return responses;
    }

    public void setResponse(HashMap response) {
        this.responses = response;
    }

    public void setModifiedDetails(ObservableMap<String, Object> modifiedDetails, String paramCount) {
        
        if(paramCount.equals("0")){
            parameters.get(0).setName(modifiedDetails.get(DataLabel.PARAM_PREFIX+0).toString());
            parameters.get(0).setType(modifiedDetails.get(DataLabel.DATA_TYPE_PREFIX+0).toString());
        }

        setModifiedParamDetails(modifiedDetails);
    }

    public void setModifiedDetails(ObservableMap<String, Object> modifiedDetails) {

        setModifiedParamDetails(modifiedDetails);
    }

    private void setModifiedParamDetails(ObservableMap<String, Object> modifiedDetails) {

        this.operationId = modifiedDetails.get(DataLabel.EDIT_PARAM_OPT_ID).toString();
        this.description = modifiedDetails.get(DataLabel.EDIT_PARAM_DESCRIPTION).toString();
        this.summary = modifiedDetails.get(DataLabel.EDIT_PARAM_SUMMARY).toString();

        String codeDescription = modifiedDetails.get(DataLabel.CODE_DESCRIPTION).toString();
        String responseType = modifiedDetails.get(DataLabel.RESPONSE_DATA_TYPE).toString();

        responses.put(
                modifiedDetails.get(DataLabel.HTTP_CODE).toString(),
                createHttpResponse(codeDescription, responseType)
        );

        for (int i = 0; i < parameters.size(); i++) {
            String required = modifiedDetails.get(DataLabel.PARAM_REQUIRED + i).toString();
            parameters.get(i).setRequired(required);

            String description = modifiedDetails.get(DataLabel.PARAM_DESCRIPTION + i).toString();
            parameters.get(i).setDescription(description);
        }
    }

    private DSchema createSchema() {
        return new DSchema();

    }

}
