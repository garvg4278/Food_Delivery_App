package com.harshitraj.admincampuscraving;

public class User {
    private String name;
    private String RestaurantName;
    private String email;

    public User() {
        // Default constructor required for Firebase
    }

    public User(String name,String RestaurantName ,String email) {
        this.name = name;
        this.email = email;
        this.RestaurantName=RestaurantName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getrestaurantName() {
        return RestaurantName;
    }

    public void setrestaurantName(String RestaurantName) {
        this.RestaurantName = RestaurantName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
