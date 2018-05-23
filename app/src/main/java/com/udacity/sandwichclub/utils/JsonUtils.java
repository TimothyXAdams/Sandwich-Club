package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        JSONArray ingredients = null;
        String mainName = null;
        JSONArray alsoKnownAs = null;
        String description = null;
        String imageSourceOnline = null;
        String placeOfOrigin = null;
        Sandwich sandwich = null;

        try {
            JSONObject Sandwich = new JSONObject(json);
            imageSourceOnline = Sandwich .getString("image");
            ingredients = Sandwich .getJSONArray("ingredients");
            JSONObject name = Sandwich .getJSONObject("name");
            mainName = name.getString("mainName");
            alsoKnownAs = name.getJSONArray("alsoKnownAs");
            description = Sandwich .getString("description");
            placeOfOrigin = Sandwich .getString("placeOfOrigin");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        sandwich = new Sandwich();
        sandwich.setImage(imageSourceOnline);
        sandwich.setMainName(mainName);
        sandwich.setDescription(description);
        sandwich.setPlaceOfOrigin(placeOfOrigin);


        int akalength = alsoKnownAs.length();
        if (akalength > 0) {
            List<String> alsoKnownAsList = new ArrayList<>();
            for(int i=0; i<akalength; i++) {
                try {
                    alsoKnownAsList.add(alsoKnownAs.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            sandwich.setAlsoKnownAs(alsoKnownAsList);
        }


        int ingredientsLength = ingredients.length();
        if (ingredientsLength > 0) {
            List<String> ingredientsList = new ArrayList<>();
            for(int i=0; i<ingredientsLength; i++) {
                try {
                    ingredientsList.add(ingredients.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            sandwich.setIngredients(ingredientsList);
        }


        return sandwich;
    }
}
