package com.juan_arillo.littlebarcommands.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by juan_arillo on 06/12/16.
 */

public class Menu {

    private static LinkedList<Dish> sDish = new LinkedList<>();
    private static final String MENU_URL = "http://www.mocky.io/v2/584d4a9c0f0000bd10d40f54";

    public Menu(LinkedList<Dish> sDish) {
        Menu.sDish = sDish;
    }

    public static LinkedList<Dish> getMenu() {
        return sDish;
    }

    public void setsDish(LinkedList<Dish> sDish) {
        Menu.sDish = sDish;
    }

    public static LinkedList<Dish> downloadMenu() {
        // Loading JSON

        try {
            URLConnection connection = new URL(MENU_URL).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();

            JSONObject jsonRoot = new JSONObject(sb.toString());
            JSONArray menu = jsonRoot.getJSONArray("menu");
            for (int i = 0; i < menu.length(); i++) {
                JSONObject currentDish = menu.getJSONObject(i);

                int id = currentDish.getInt("id");
                String name = currentDish.getString("name");
                String description = currentDish.getString("description");
                String type = currentDish.getString("type");
                double price = currentDish.getDouble("price");
                JSONArray arrayAllergens = currentDish.getJSONArray("allergens");
                ArrayList<String> allergens = new ArrayList<String>();
                for (int j = 0; j < arrayAllergens.length(); j++) {
                    String currentAllergen = arrayAllergens.getString(j);
                    allergens.add(currentAllergen);
                }
                String image = currentDish.getString("image");

                Dish dish = new Dish(id, name, description, type, price, image, "", allergens);
                sDish.add(dish);
            }

            return sDish;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
