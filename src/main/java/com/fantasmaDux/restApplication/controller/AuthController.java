package com.fantasmaDux.restApplication.controller;

import com.fantasmaDux.restApplication.dto.AuthDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AuthController {
    @Value("${client-id}")
    private String clientId;

    @Value("${resource-url}")
    private String resourceServerURL;

    @Value("${grant-type}")
    private String grantType;

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setResourceServerURL(String resourceServerURL) {
        this.resourceServerURL = resourceServerURL;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    @PostMapping("/auth")
    public String auth(@RequestBody AuthDTO authDTO) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var body = "client_id=" + clientId +
                "&username=" + authDTO.login() +
                "&password=" + authDTO.password() +
                "&grant_type=" + grantType;

        var requestEntity = new HttpEntity<>(body, headers);
        var restTemplate = new RestTemplate();

        var response = restTemplate.exchange(
                resourceServerURL,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }
}
