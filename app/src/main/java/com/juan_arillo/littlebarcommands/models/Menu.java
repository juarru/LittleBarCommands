package com.juan_arillo.littlebarcommands.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;

public class Menu {

    private static LinkedList<Dish> sDish = new LinkedList<>();
    private static final String MENU_URL = "http://www.mocky.io/v2/584d6b9e0f00004714d40f74";

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
            JSONArray platos = jsonRoot.getJSONArray("platos");
            for (int i = 0; i < platos.length(); i++) {
                JSONObject currentDish = platos.getJSONObject(i);

                int id = currentDish.getInt("id");
                String name = currentDish.getString("nombre");
                String description = currentDish.getString("descripcion");
                double price = currentDish.getDouble("precio");
                JSONArray arrayAllergens = currentDish.getJSONArray("alergenos");
                ArrayList<String> allergens = new ArrayList<String>();
                for (int j = 0; j < arrayAllergens.length(); j++) {
                    String currentAllergen = arrayAllergens.getString(j);
                    allergens.add(currentAllergen);
                }
                String image = currentDish.getString("imagen");

                Dish dish = new Dish(id, name, description, price, image, "", allergens);
                sDish.add(dish);
            }

            return sDish;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
