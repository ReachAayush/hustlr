package edu.cmu.hustlr.Util;

import org.json.JSONException;
import org.json.JSONObject;

import edu.cmu.hustlr.Entities.Portfolio;
import edu.cmu.hustlr.Entities.User;

/**
 * Created by ruei on 2015/12/4.
 */
public class JsonToUser {
    public static User transfer(JSONObject obj) throws JSONException {
        User user = new User();
        user.setCash(Double.valueOf(obj.get("cash").toString()));
        Portfolio portfolio = JsonToPortfolio.transfer(obj);
        user.setPortfolio(portfolio);
        return user;
    }
}