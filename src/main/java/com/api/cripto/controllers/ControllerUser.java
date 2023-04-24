package com.api.cripto.controllers;

import com.api.cripto.configuration.ConfigurationPasswordUtils;
import com.api.cripto.entity.EntityUser;
import com.api.cripto.services.ServiceAccessToken;
import com.api.cripto.services.ServiceDataBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControllerUser {
    @Autowired
    ServiceDataBase serviceDataBase;
    @Autowired
    private ConfigurationPasswordUtils passwordUtils;
    @Autowired
    private ServiceAccessToken serviceAccessToken;

    @PostMapping("login")
    @Operation(summary = "Obtener token necesario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token generador correctamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos o faltantes"),
            @ApiResponse(responseCode = "500", description = "Error al general token")
    })
    public JSONObject login(@RequestBody EntityUser consultaUser) {
        JSONObject mostrarToken = new JSONObject();

        if (passwordUtils.validarUsuario(consultaUser)) {
            String token = serviceAccessToken.getJWTToken(consultaUser.getUserName());
            consultaUser.setToken(token);

            serviceDataBase.guardarMitoken(consultaUser.getUserName(), token);

            mostrarToken.put("token", token);

        } else {
            mostrarToken.put("Error", "Error usuario o contraseña incorrecta");

        }

        return mostrarToken;
    }

}