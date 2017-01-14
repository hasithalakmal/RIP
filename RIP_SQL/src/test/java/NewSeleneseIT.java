/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 *
 * @author Hasitha Lakmal
 */
public class NewSeleneseIT {

    @Test
    public void testSimple() throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:\\Software Setups\\Software Engineering\\Servers\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // And now use this to visit NetBeans
        driver.get("http://www.netbeans.org");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.netbeans.org");

        // Check the title of the page
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.getTitle().contains("NetBeans");
            }
        });

        //Close the browser
        driver.quit();
    }

    @Test
    public void aptTesting() throws Exception {
        try {
            URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=chicago&sensor=false&#8221;");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application / json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP error code: "
                        + conn.getResponseCode()
                );
            }

            Scanner scan = new Scanner(url.openStream());
            String entireResponse = new String();
            while (scan.hasNext()) {
                entireResponse += scan.nextLine();
            }

            System.out.println("Response: " + entireResponse);

            scan.close();

            JSONObject obj = new JSONObject(entireResponse);
            String responseCode = obj.getString("status");
            System.out.println(
                    "status: " + responseCode
            );

            JSONArray arr = obj.getJSONArray("results");
            for (int i = 0; i < arr.length(); i++) {
                String placeid = arr.getJSONObject(i).getString("place_id");
                System.out.println(
                        "Place id : " + placeid
                );
                String formatAddress = arr.getJSONObject(i).getString("formatted_address");
                System.out.println(
                        "Address: " + formatAddress
                );

//validating Address as per the requirement
                if (formatAddress.equalsIgnoreCase("Chicago, IL, USA")) {
                    System.out.println("Address is as Expected");
                } else {
                    System.out.println("Address is not as Expected");
                }
            }

            conn.disconnect();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }

    }
    
    
    @Test
    public void aptTesting2() throws Exception {
        try {
            URL url = new URL("http://localhost:8084/RIP_SQL/test");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
           // conn.setRequestProperty("Accept", "application / json");
          //  conn.setRequestProperty("Content-Type", "application/json");
            //conn.setRequestProperty("body", "{ \"user_name\": \"lakmal\", \"password\": \"abc\", \"mobile\": 717584227, \"emial\": \"ghasithalakmal@gmail.com\" }");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP error code: "
                        + conn.getResponseCode()+" "+conn.getAllowUserInteraction()
                );
            }

           

            conn.disconnect();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }

    }
}
