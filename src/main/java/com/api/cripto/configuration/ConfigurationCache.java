package com.api.cripto.configuration;

import net.minidev.json.JSONObject;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableScheduling
public class ConfigurationCache {
    public Map<String, JSONObject> cacheResultados = new HashMap<>();

    private static final long MILISEGUNDO_MINUTO = 60000;

    @Scheduled(fixedRate = MILISEGUNDO_MINUTO * 3) // 3 minutos en milisegundos
    public void limpiarCache() {
        cacheResultados.clear();
    }
}
