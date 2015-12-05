package edu.cmu.hustlr.Util;

import org.json.JSONException;
import org.json.JSONObject;

import edu.cmu.hustlr.Entities.Portfolio;
import edu.cmu.hustlr.Entities.User;

/**
 * Created by rueiminl on 2015/12/4.
 * An utility function to transfer json object (from backend) to a User object
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
