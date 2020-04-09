package br.com.devdojo.javaclient;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class JavaClientTest {
    public static void main(String[] args) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String user = "bruno";
        String password = "devdojo";
        try {
            URL url = new URL("http://localhost:8080/v1/user/students/1");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Authorization", "Basic " + encodeUsernamePassword(user, password)); // YnJ1bm86ZGV2ZG9qbw==
            System.out.println(encodeUsernamePassword(user, password));
            // fazer a conexão e pegar o valor
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonSB = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                jsonSB.append(line);
            }
            System.out.println(jsonSB.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(reader);
            if (connection != null)
                connection.disconnect();
        }
    }
    private static String encodeUsernamePassword(String user, String password){
        String userPassword = user + ":" + password;
        return new String(Base64.encodeBase64(userPassword.getBytes()));
    }
}
