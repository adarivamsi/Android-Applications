package com.londonappbrewery.bitcointicker;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by adari on 6/7/2017.
 */

public class BitCoinResult {

    private double mPrice;

    public static BitCoinResult fromJson(JSONObject source) {
        BitCoinResult result = new BitCoinResult();

        try {
            result.setPrice(source.getDouble("price"));

            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

}
