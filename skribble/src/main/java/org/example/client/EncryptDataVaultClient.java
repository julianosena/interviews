package org.example.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class EncryptDataVaultClient {

    private static final int MAX_RETRY_ATTEMPTS = 3;

    private final String vaultBaseUrl;
    private final RestTemplate restTemplate;

    public EncryptDataVaultClient(String vaultBaseUrl) {
        this.vaultBaseUrl = vaultBaseUrl;
        this.restTemplate = new RestTemplate();
    }

    public byte[] encryptData(String rsaKeyName, byte[] data) {
        int retryAttempts = 0;
        do {
            try {
                return doEncryptData(rsaKeyName, data);
            } catch (HttpServerErrorException e) {
                // Log the error or perform other error handling actions
                retryAttempts++;
            }
        } while (retryAttempts < MAX_RETRY_ATTEMPTS);

        throw new RuntimeException("Failed to encrypt data after max retry attempts");
    }

    private byte[] doEncryptData(String rsaKeyName, byte[] data) {
        String encryptUrl = vaultBaseUrl + "/v1/transit/encrypt/" + rsaKeyName;

        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> requestBody = Map.of("plaintext", data);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> responseEntity = restTemplate.exchange(
                encryptUrl,
                HttpMethod.POST,
                requestEntity,
                Map.class
        );

        // Process the response and extract the encrypted data
        Map<String, Object> responseBody = responseEntity.getBody();
        if (responseBody != null && responseBody.containsKey("ciphertext")) {
            return (byte[]) responseBody.get("ciphertext");
        } else {
            throw new RuntimeException("Unexpected response format");
        }
    }
}
