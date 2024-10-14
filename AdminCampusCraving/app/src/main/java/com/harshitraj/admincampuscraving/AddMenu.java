package com.harshitraj.admincampuscraving;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class AddMenu {
    private String foodName;
    private String foodPrice;
    private String imageURL;
    private String shortDescription;
    private String ingredient;

    public AddMenu() {
        // Default constructor required for calls to DataSnapshot.getValue(AddMenu.class)
    }

    public AddMenu(String foodName, String foodPrice, String imageURL, String shortDescription, String ingredient) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.imageURL = imageURL;
        this.shortDescription = shortDescription;
        this.ingredient = ingredient;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
