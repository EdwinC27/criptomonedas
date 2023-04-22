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

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class ServiceCoinmarketCap {
    @Value("${ApiKeyServiceCoinmarketCap}")
    private String ApiKey;
    @Value("${UrlServiceCoinmarketCap}")
    private String urlPeticion;

    public JSONObject getAllResults() throws MalformedURLException {
        URL url = new URL( urlPeticion + "cryptocurrency/listings/latest?start=1&limit=5&convert=USD");

        return getPeticion(url);
    }

    public JSONObject getIdCriptoResults(String id) throws MalformedURLException {
        URL url = new URL(urlPeticion + "cryptocurrency/info?id=" + id);

        return getPeticion(url);
    }

    public JSONObject getValorCriptoResults(String convert) throws MalformedURLException {
        URL url = new URL(urlPeticion + "global-metrics/quotes/latest?convert=" + convert);

        return getPeticion(url);
    }

    public JSONObject getPeticion(URL url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", ApiKey);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(url.toString(), HttpMethod.GET, entity, String.class);
        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.getBody());
        return jsonObject;
    }
}
