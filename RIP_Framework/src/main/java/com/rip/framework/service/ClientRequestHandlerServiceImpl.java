package com.rip.framework.service;

import com.rip.framework.mongodao.ProjectManagementDao;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("clientRequestHandlerService")
@Transactional
public class ClientRequestHandlerServiceImpl implements ClientRequestHandlerService {

    @Autowired
    ProjectManagementDao projectManagementDao;

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
        String msg = "{\"msg\" : \"Success\"}";
        
        JSONObject jason = new JSONObject(RIP_JSON);
        Object Database_Design = jason.getJSONObject("Database_Design");

        //Calling RIP_SQL Gen
        try {
            URL url = new URL("http://localhost:8084/RIP_SQL/sql-file");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = Database_Design.toString();

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ~~~~ Output from RIP_SQL_GEN ~~~~  >>>>>>>>>>>>>>>>>>>>>>>\n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ~~~~ END RIP_SQL_GEN Output  ~~~~ >>>>>>>>>>>>>>>>>>>>>>>\n");
            conn.disconnect();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        
        //Calling RIP_API
        try {
            URL url = new URL("http://localhost:8084/RIP_API/rest-api");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = RIP_JSON;

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ~~~~ Output from RIP_API_GEN ~~~~  >>>>>>>>>>>>>>>>>>>>>>>\n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ~~~~ END RIP_API_GEN Output  ~~~~ >>>>>>>>>>>>>>>>>>>>>>>\n");
            conn.disconnect();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        
        //Calling RIP_UI_Gen
        try {
            URL url = new URL("http://localhost:8084/RIP_UI_Gen/rest-client-app");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = RIP_JSON;

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ~~~~ Output from RIP_UI_Gen ~~~~  >>>>>>>>>>>>>>>>>>>>>>>\n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ~~~~ END RIP_UI_Gen Output  ~~~~ >>>>>>>>>>>>>>>>>>>>>>>\n");
            conn.disconnect();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        
        //Calling RIP Test Env
        try {
            URL url = new URL("http://localhost:8084/RIP_Test/test-report");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = RIP_JSON;

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ~~~~ Output from RIP_Test ~~~~  >>>>>>>>>>>>>>>>>>>>>>>\n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ~~~~ END RIP_Test Output  ~~~~ >>>>>>>>>>>>>>>>>>>>>>>\n");
            conn.disconnect();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        
        //Calling RIP File Handler in RIP_Framework
        try {
            URL url = new URL("http://localhost:8084/RIP_Framework/zip-file");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = RIP_JSON;

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ~~~~ Output from RIP_Framework File Handler ~~~~  >>>>>>>>>>>>>>>>>>>>>>>\n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ~~~~ END RIP_Framework File Handler Output  ~~~~ >>>>>>>>>>>>>>>>>>>>>>>\n");
            conn.disconnect();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }

        return msg;
    }
    
    

}
