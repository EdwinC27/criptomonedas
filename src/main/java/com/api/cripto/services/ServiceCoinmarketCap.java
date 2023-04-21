package com.api.cripto.services;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Service
public class ServiceCoinmarketCap {
    @Value("${ApiKeyServiceCoinmarketCap}")
    private String ApiKey;
    @Value("${UrlServiceCoinmarketCap}")
    private String URL;

    public JSONObject getAllResults() {
        String url = URL + "cryptocurrency/listings/latest?start=1&limit=5&convert=USD";

        return getPeticion(url);
    }

    public JSONObject getIdCriptoResults(String id) {
        String url = URL + "cryptocurrency/info?id=" + id;

        return getPeticion(url);
    }

    public JSONObject getValorCriptoResults(String convert) {
        String url = URL + "global-metrics/quotes/latest?convert=" + convert;

        return getPeticion(url);
    }

    public JSONObject getPeticion(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", ApiKey);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.getBody());
        return jsonObject;
    }
}
