package com.airfrance.userDemo;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.http.HttpHeaders;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Test
    public void testGetUserData () throws JSONException {
        HttpHeaders postHeaders = new HttpHeaders();
        postHeaders.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = "{\"nom\":\"MABROUk\", \"prenom\":\"Amine\", \"age\":\"35\", \"pays\":\"france\"}";
        HttpEntity<String> userPostEntity = new HttpEntity<String>(requestJson, postHeaders);


        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<String> addUserResponse = restTemplate.exchange(
                createURLWithPort("/save"),
                HttpMethod.POST, userPostEntity, String.class);

        System.out.println("user: " + addUserResponse.getBody());

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/1"),
                HttpMethod.GET, entity, String.class);

        String expected = "[{\"nom\":\"MABROUk\", \"prenom\":\"Amine\", \"age\":\"35\", \"pays\":\"france\"}]";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
