package com.api.cripto.controllers;

import com.api.cripto.services.ServiceCoinmarketCap;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerQuery {
    @Autowired
    ServiceCoinmarketCap serviceCoinmarketCap;

    @RequestMapping("api/query")
    public JSONObject query(@RequestParam(value = "idCripto", required = false) String idCripto, @RequestParam(value = "convert", required = false) String convert) {
        JSONObject results = new JSONObject();

        try {
            if(idCripto != null) {
                // obtener información detallada sobre una criptomoneda específica
                results = serviceCoinmarketCap.getIdCriptoResults(idCripto);

            } else if (convert != null) {
                // obtener las estadísticas globales del mercado de criptomonedas
                results = serviceCoinmarketCap.getValorCriptoResults(convert);
            } else {
                // informacion general
                results = serviceCoinmarketCap.getAllResults();
            }

        } catch (Exception exception) {
            results.put("Error", exception.getMessage());
        }
        return results;
    }
}