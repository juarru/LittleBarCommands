package com.juan_arillo.littlebarcommands.models;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;


public class Dish implements Serializable {

    private int mIdDish = -1;
    private String mName = null;
    private String mDescription = null;
    private String mType = null;
    private double mPrice = 0;
    private String mImage = "1";
    private String mWishList = null;
    private ArrayList<String> mAllergens = null;
    private Bitmap mBitmap;

    public Dish(int idDish, String name, String description, double price, String image, String wishList, ArrayList<String> allergens) {
        mIdDish = idDish;
        mName = name;
        mDescription = description;
        mPrice = price;
        mImage = image;
        mAllergens = allergens;
    }

    public int getIdDish() {
        return mIdDish;
    }

    public void setIdDish(int idDish) {
        mIdDish = idDish;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public ArrayList<String> getAllergens() {
        return mAllergens;
    }

    public void setAllergens(ArrayList<String> allergens) {
        mAllergens = allergens;
    }

}
