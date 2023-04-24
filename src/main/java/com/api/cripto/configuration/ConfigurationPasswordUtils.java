package com.api.cripto.configuration;

import com.api.cripto.entity.EntityUser;
import com.api.cripto.repository.RepositoryUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
public class ConfigurationPasswordUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationPasswordUtils.class);

    @Autowired
    private RepositoryUser repositorioUser;

    public boolean validarUsuario(EntityUser userJson) {
        try {
            EntityUser usuarioBaseDatos = repositorioUser.findByuserName(userJson.getUserName());

            LOGGER.debug("userJson: " + userJson.getUserName());
            LOGGER.debug("usuarioBaseDatos: " + usuarioBaseDatos.getUserName());
            LOGGER.debug("passwordJson: " + userJson.getPassword());
            LOGGER.debug("PasswordBD: " + usuarioBaseDatos.getPassword());

            return verifyPassword(userJson.getPassword(), usuarioBaseDatos.getPassword());
        } catch (Exception e) {
            return false;
        }
    }

    private boolean verifyPassword(String passwordJson, String passwordBaseDatos) {
        String passwordJsonHash = hashPasswordJson(passwordJson);
        return passwordJsonHash.equals(passwordBaseDatos);
    }

    private String hashPasswordJson(String passwordJson) {
        String passwordJsonHash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // utiliza el algoritmo SHA-256
            byte[] hashInBytes = md.digest(passwordJson.getBytes(StandardCharsets.UTF_8)); // generar una secuencia de bytes hash a partir de la cadena
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) { // convierte en una cadena hexadecimal
                sb.append(String.format("%02x", b));
            }
            passwordJsonHash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return passwordJsonHash;
    }
}