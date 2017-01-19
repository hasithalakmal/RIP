package com.rip.framework.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("clientRequestHandlerService")
@Transactional
public class ClientRequestHandlerServiceImpl implements ClientRequestHandlerService {

    @Override
    public String createClientRequest(String design) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteClientRequest(String projectName, String version) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateClientRequest(String projectName, String version) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String selectClientRequest(String projectName, String version) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Async
    public String genarateClientResponse(String RIP_JSON) {
        String baseURL = "http://localhost:8084";

        JSONObject responceObject = new JSONObject();
        String overall_state = "";
        String downloardURL = "";
        String SQLGen_state = "";
        String SQLGen_responce = "";
        String SQLGen_Result = "";
        String APIGen_state = "";
        String APIGen_responce = "";
        String APIGen_Result = "";
        String UIGen_state = "";
        String UIGen_responce = "";
        String TestEnv_state = "";
        String TestEnv_responce = "";
        String TestEnv_Result = "";
        String MicroServices_state = "";
        String MicroServices_responce = "";
        String MicroServices_Result = "";

        JSONObject pim = new JSONObject(RIP_JSON);

        //Calling RIP_SQL Gen
        try {
            URL url = new URL(baseURL + "/RIP_Framework_Project/project");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String input = pim.toString();
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {

            }
            MicroServices_state = Integer.toString(conn.getResponseCode());
            MicroServices_responce = conn.getResponseMessage();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;

            while ((output = br.readLine()) != null) {
                System.out.println(output);
                MicroServices_Result = MicroServices_Result + output;
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }

        //Calling RIP_SQL Gen
        try {
            URL url = new URL(baseURL + "/RIP_SQL/sql-file");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String input = pim.toString();
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                SQLGen_state = Integer.toString(conn.getResponseCode());
                SQLGen_responce = conn.getResponseMessage();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;

            while ((output = br.readLine()) != null) {
                System.out.println(output);
                SQLGen_Result = SQLGen_Result + output;
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }

//        //Calling RIP_API
        try {
            URL url = new URL(baseURL + "/RIP_API/rest-api");
            HttpURLConnection conn1 = (HttpURLConnection) url.openConnection();
            conn1.setDoOutput(true);
            conn1.setRequestMethod("POST");
            conn1.setRequestProperty("Content-Type", "application/json");
            String input = RIP_JSON;
            OutputStream os = conn1.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            if (conn1.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println(conn1.getResponseMessage());
            }
            APIGen_state = Integer.toString(conn1.getResponseCode());
            APIGen_responce = conn1.getResponseMessage();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn1.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                APIGen_Result = APIGen_Result + output;
            }
            conn1.disconnect();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
//        
//      
//        
        //Calling RIP Test Env
        try {
            URL url = new URL(baseURL + "/RIP_Test/rest-api-test");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String input = RIP_JSON;
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                System.out.println(conn.getResponseMessage());
            }
            TestEnv_state = Integer.toString(conn.getResponseCode());
            TestEnv_responce = conn.getResponseMessage();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                TestEnv_Result = TestEnv_Result + output;
            }
            conn.disconnect();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
//        
//        //Calling RIP File Handler in RIP_Framework
//        try {
//            URL url = new URL("http://localhost:8084/RIP_Framework/zip-file");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setDoOutput(true);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json");
//
//            String input = RIP_JSON;
//
//            OutputStream os = conn.getOutputStream();
//            os.write(input.getBytes());
//            os.flush();
//
//            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + conn.getResponseCode());
//            }
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(
//                    (conn.getInputStream())));
//
//            String output;
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ~~~~ Output from RIP_Framework File Handler ~~~~  >>>>>>>>>>>>>>>>>>>>>>>\n");
//            while ((output = br.readLine()) != null) {
//                System.out.println(output);
//            }
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ~~~~ END RIP_Framework File Handler Output  ~~~~ >>>>>>>>>>>>>>>>>>>>>>>\n");
//            conn.disconnect();
//
//        } catch (MalformedURLException e) {
//        } catch (IOException e) {
//        }

        overall_state = "success";

        responceObject.accumulate("overall_state", overall_state);
        responceObject.accumulate("downloardURL", downloardURL);
        responceObject.accumulate("SQLGen_state", SQLGen_state);
        responceObject.accumulate("SQLGen_responce", SQLGen_responce);
        responceObject.accumulate("SQLGen_Result", SQLGen_Result);
        responceObject.accumulate("APIGen_state", APIGen_state);
        responceObject.accumulate("APIGen_responce", APIGen_responce);
        responceObject.accumulate("APIGen_Result", APIGen_Result);
        responceObject.accumulate("UIGen_state", UIGen_state);
        responceObject.accumulate("UIGen_responce", UIGen_responce);
        responceObject.accumulate("TestEnv_state", TestEnv_state);
        responceObject.accumulate("TestEnv_responce", TestEnv_responce);
        responceObject.accumulate("TestEnv_Result", TestEnv_Result);
        responceObject.accumulate("MicroServices_state", MicroServices_state);
        responceObject.accumulate("MicroServices_responce", MicroServices_responce);
        responceObject.accumulate("MicroServices_Result", MicroServices_Result);
        return responceObject.toString();
    }

}
