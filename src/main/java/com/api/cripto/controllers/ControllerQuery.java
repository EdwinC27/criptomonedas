package com.api.cripto.controllers;

import com.api.cripto.services.ServiceCoinmarketCap;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerQuery {
    @Autowired
    ServiceCoinmarketCap serviceCoinmarketCap;

    @RequestMapping("api/query")
    public JSONObject query() {
        JSONObject results = new JSONObject();
        try {
            results = serviceCoinmarketCap.getResults();
        } catch (Exception exception) {
            results.put("Error", exception.getMessage());
        }
        return results;
    }
}