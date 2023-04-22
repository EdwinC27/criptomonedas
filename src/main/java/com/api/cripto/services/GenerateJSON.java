package com.api.cripto.services;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class GenerateJSON {
    public JSONObject accommodateJSON(JSONArray dataArray) {
        JSONObject jsonObject = new JSONObject();

        // Recorrer toda la info que contiene dataArray
        for (int i = 0; i < dataArray.size(); i++) {
            JSONObject coin = (JSONObject) dataArray.get(i);
            JSONObject filteredCoin = new JSONObject();

            filteredCoin.put("symbol", coin.get("symbol"));
            filteredCoin.put("circulating_supply", coin.get("circulating_supply"));
            filteredCoin.put("last_updated", coin.get("last_updated"));
            filteredCoin.put("total_supply", coin.get("total_supply"));
            filteredCoin.put("cmc_rank", coin.get("cmc_rank"));

            JSONObject quote = (JSONObject) coin.get("quote");
            JSONObject usd = (JSONObject) quote.get("USD");

            filteredCoin.put("price", usd.get("price"));
            filteredCoin.put("percent_change_24h", usd.get("percent_change_24h"));

            jsonObject.put((String) coin.get("name"), filteredCoin);
        }

        return jsonObject;
    }
}
