package com.api.cripto.services;

import com.api.cripto.configuration.ConfigurationCache;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class ServiceCoinmarketCap {
    @Value("${ApiKeyServiceCoinmarketCap}")
    private String ApiKey;
    @Value("${UrlServiceCoinmarketCap}")
    private String urlPeticion;
    @Autowired
    private GenerateJSON generateJSON;
    @Autowired
    private ConfigurationCache configurationCache;

    @PostConstruct
    public void iniciarLimpiarCache() {
        configurationCache.limpiarCache();
    }

    public JSONObject getAllResults() throws MalformedURLException {
        String cacheKey = "allResults";
        JSONObject cacheResult = configurationCache.cacheResultados.get(cacheKey);

        if (cacheResult != null) return cacheResult;

        URL url = new URL( urlPeticion + "cryptocurrency/listings/latest?start=1&limit=5&convert=USD");
        JSONObject response = getPeticion(url);

        JSONArray dataArray = (JSONArray) response.get("data");

        JSONObject result = generateJSON.accommodateJSONAllResults(dataArray);

        configurationCache.cacheResultados.put(cacheKey, result);

        return result;
    }


    public JSONObject getIdCriptoResults(String id) throws MalformedURLException {
        String cachekey = "IdCriptoResults:"+id;
        JSONObject cacheResult = configurationCache.cacheResultados.get(cachekey);

        if(cacheResult != null) return cacheResult;

        URL url = new URL(urlPeticion + "cryptocurrency/info?id=" + id);
        JSONObject response = getPeticion(url);

        JSONObject general = (JSONObject) response.get("data");
        JSONObject data = (JSONObject) general.get(id);

        JSONObject result = generateJSON.accommodateJSONIdCriptoResults(data);

        configurationCache.cacheResultados.put(cachekey, result);

        return result;
    }

    public JSONObject getValorCriptoResults(String convert) throws MalformedURLException {
        String cachekey = "ValorCriptoResults:"+convert;

        JSONObject cacheResult = configurationCache.cacheResultados.get(cachekey);

        if(cacheResult != null) return cacheResult;

        URL url = new URL(urlPeticion + "global-metrics/quotes/latest?convert=" + convert);
        JSONObject response = getPeticion(url);

        JSONObject general = (JSONObject) response.get("data");

        JSONObject result = generateJSON.accommodateJSONValorCriptoResults(general);

        configurationCache.cacheResultados.put(cachekey, result);

        return result;
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
