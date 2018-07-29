package com.joe.morte.backend.impl;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;


import com.joe.morte.backend.api.AzureEmotion;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

@Configuration
public class AzureEmotionImpl implements AzureEmotion {
    @Autowired
    private ResourceLoader resourceLoader;
    @Value("${azure.subscription.key}")
    private String subscriptionKey;
    private static final String faceAttributes =
            "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise";

    private static final String uriBase =
            "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect";

    private HttpPost emotionRequest;

    @PostConstruct
    public void init() throws URISyntaxException {
        emotionRequest = createEmotionRequest();
    }

    private HttpPost createEmotionRequest() throws URISyntaxException {

        URIBuilder builder = new URIBuilder(uriBase);

        // Request parameters. All of them are optional.
        builder.setParameter("returnFaceId", "true");
        builder.setParameter("returnFaceLandmarks", "false");
        builder.setParameter("returnFaceAttributes", faceAttributes);

        // Prepare the URI for the REST API call.
        URI uri = builder.build();
        HttpPost request = new HttpPost(uri);

        // Request headers.
        // if input is an URL then change value to "application/json"
        request.setHeader("Content-Type", "application/octet-stream");
        request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
        return request;
    }

    @Lazy
    @Bean
    public Object demo() {

        try {
            Resource fileResource = resourceLoader.getResource("classpath:RH_Louise_Lillian_Gish.jpg");
//            String input = encodeFileToBase64Binary(fileResource.getFile());
            InputStream input = encodeFileToStream(fileResource.getFile());
            // Request body.
//            StringEntity reqEntity = new StringEntity("{\"data\":\"" + input + "\"}");
            InputStreamEntity reqEntity = new InputStreamEntity(input);
            emotionRequest.setEntity(reqEntity);
            // should create this every time?
            HttpClient httpclient = new DefaultHttpClient();
            // Execute the REST API call and get the response entity.
            HttpResponse response = httpclient.execute(emotionRequest);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // Format and display the JSON response.
                System.out.println("REST Response:\n");

                String jsonString = EntityUtils.toString(entity).trim();

                return jsonString;
            }
        } catch (Exception e) {
            // Display error message.
            System.out.println(e.getMessage());
        }
        return "no msg";
    }

    @Override
    public String detectEmotion(InputStream input) {
        try {
            // Request body.
            InputStreamEntity reqEntity = new InputStreamEntity(input);
            emotionRequest.setEntity(reqEntity);
            // should create this every time?
            HttpClient httpclient = new DefaultHttpClient();
            // Execute the REST API call and get the response entity.
            HttpResponse response = httpclient.execute(emotionRequest);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // Format and display the JSON response.
                System.out.println("REST Response:\n");

                String jsonString = EntityUtils.toString(entity).trim();

                System.out.println(jsonString);

                return jsonString;
            }
        } catch (Exception e) {
            // Display error message.
            System.out.println(e.getMessage());
        }
        return "no msg";
    }

    private String encodeFileToBase64Binary(File file) {
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
//            encodedfile = Base64.encodeBase64(bytes).toString();
            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encodedfile;
    }

    private InputStream encodeFileToStream(File file) {
        InputStream encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new ByteArrayInputStream(bytes);
            return encodedfile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encodedfile;
    }
}
