package com.api.cripto.controllers;

import com.api.cripto.services.ServiceCoinmarketCap;
import com.api.cripto.services.ServiceDataBase;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerQuery {
    @Autowired
    ServiceCoinmarketCap serviceCoinmarketCap;
    @Autowired
    ServiceDataBase serviceDataBase;

    @RequestMapping("api/query")
    public JSONObject query(@RequestParam(value = "idCripto", required = false) String idCripto, @RequestParam(value = "convert", required = false) String convert) {
        JSONObject results = new JSONObject();
        String Endpoint = null;
        String Status;

        try {
            if(idCripto != null) { // obtener información detallada sobre una criptomoneda específica
                Endpoint = "api/query?idCripto";

                results = serviceCoinmarketCap.getIdCriptoResults(idCripto);

            } else if (convert != null) { // obtener las estadísticas globales del mercado de criptomonedas
                Endpoint = "api/query?convert";

                results = serviceCoinmarketCap.getValorCriptoResults(convert);

            } else { // informacion general
                Endpoint = "api/query";

                results = serviceCoinmarketCap.getAllResults();
            }

            Status = "ok";
        } catch (Exception exception) {
            results.put("Error", exception.getMessage());
            Status = exception.getMessage();
        }

        try {
            serviceDataBase.guardarMiEntidad(Endpoint, Status);
        } catch (Exception exception) {
            results.put("Error", "No se pudo guardar en la base de datos");
        }

        return results;
    }
}