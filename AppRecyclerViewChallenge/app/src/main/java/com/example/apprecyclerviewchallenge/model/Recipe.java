package com.example.apprecyclerviewchallenge.model;

import android.graphics.Bitmap;

public class Recipe {

    Bitmap image;
    String title;
    String description;
    String[] ingredients;
    String[] procedures;

    public Recipe(Bitmap image,String title, String description,String[] ingredients, String[] procedures) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.procedures = procedures;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getProcedures() {
        return procedures;
    }

    public void setProcedures(String[] procedures) {
        this.procedures = procedures;
    }

    public Bitmap getImage() {
        return image;
    }
}
