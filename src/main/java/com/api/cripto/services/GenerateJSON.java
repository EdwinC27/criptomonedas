package com.api.cripto.services;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class GenerateJSON {
    public JSONObject accommodateJSONAllResults(JSONArray dataArray) {
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

    public JSONObject accommodateJSONIdCriptoResults(JSONObject data) {
        JSONObject result = new JSONObject();
        result.put("symbol", data.get("symbol"));
        result.put("description", data.get("description"));
        result.put("contracts", data.get("contracts"));
        result.put("tags", data.get("tags"));
        result.put("social", data.get("social"));

        return result;
    }

    public JSONObject accommodateJSONValorCriptoResults(JSONObject coin) {
        JSONObject filteredCoin = new JSONObject();

        filteredCoin.put("eth_dominance", coin.get("eth_dominance"));
        filteredCoin.put("active_cryptocurrencies", coin.get("active_cryptocurrencies"));
        filteredCoin.put("stablecoin_market_cap", coin.get("stablecoin_market_cap"));
        filteredCoin.put("derivatives_volume_24h", coin.get("derivatives_volume_24h"));
        filteredCoin.put("eth_dominance_yesterday", coin.get("eth_dominance_yesterday"));
        filteredCoin.put("last_updated", coin.get("last_updated"));
        filteredCoin.put("defi_volume_24h", coin.get("defi_volume_24h"));
        filteredCoin.put("active_exchanges", coin.get("active_exchanges"));
        filteredCoin.put("derivatives_24h_percentage_change", coin.get("derivatives_24h_percentage_change"));
        filteredCoin.put("defi_market_cap", coin.get("defi_market_cap"));
        filteredCoin.put("total_cryptocurrencies", coin.get("total_cryptocurrencies"));
        filteredCoin.put("btc_dominance_24h_percentage_change", coin.get("btc_dominance_24h_percentage_change"));
        filteredCoin.put("btc_dominance_yesterday", coin.get("btc_dominance_yesterday"));
        filteredCoin.put("total_exchanges", coin.get("total_exchanges"));
        filteredCoin.put("stablecoin_volume_24h", coin.get("stablecoin_volume_24h"));
        filteredCoin.put("stablecoin_volume_24h_reported", coin.get("stablecoin_volume_24h_reported"));
        filteredCoin.put("defi_volume_24h_reported", coin.get("defi_volume_24h_reported"));
        filteredCoin.put("active_market_pairs", coin.get("active_market_pairs"));
        filteredCoin.put("btc_dominance", coin.get("btc_dominance"));
        filteredCoin.put("stablecoin_24h_percentage_change", coin.get("stablecoin_24h_percentage_change"));
        filteredCoin.put("defi_24h_percentage_change", coin.get("defi_24h_percentage_change"));
        filteredCoin.put("eth_dominance_24h_percentage_change", coin.get("eth_dominance_24h_percentage_change"));

        return filteredCoin;
    }
}
