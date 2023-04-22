package com.api.cripto.controllers;

import com.api.cripto.services.ServiceCoinmarketCap;
import com.api.cripto.services.ServiceDataBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerQuery {
    @Autowired
    ServiceCoinmarketCap serviceCoinmarketCap;
    @Autowired
    ServiceDataBase serviceDataBase;

    @Operation(summary = "Query cryptocurrency information")
    @RequestMapping(value = "api/query", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = JSONObject.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = Error.class)))
    })
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