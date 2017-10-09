package curatetechnologies.com.curate.models;

/**
 * Created by mremondi on 10/9/17.
 */

public class Restaurant{
    private String _id;
    private String restaurant;
    private String restaurant_description;
    private String restaurant_URL;
    private String restaurant_logo_URL;
    private String phone_number;
    private String address;
    private String zipcode;
    private String[] tags;
    private String[] menus;
    private Loc loc;

    public Restaurant(String _id,
                      String restaurant,
                      String restaurant_description,
                      String restaurant_URL,
                      String restaurant_logo_URL,
                      String phone_number,
                      String address,
                      String zipcode,
                      String[] tags,
                      String[] menus,
                      Loc loc) {
        this._id = _id;
        this.restaurant = restaurant;
        this.restaurant_description = restaurant_description;
        this.restaurant_URL = restaurant_URL;
        this.restaurant_logo_URL = restaurant_logo_URL;
        this.phone_number = phone_number;
        this.address = address;
        this.zipcode = zipcode;
        this.tags = tags;
        this.menus = menus;
        this.loc = loc;
    }


    public Loc getLoc() {
        return loc;
    }

    public void setLoc(Loc geometry) {
        this.loc = geometry;
    }

    public String getObjectID() {
        return _id;
    }

    public void setObjectID(String objectID) {
        this._id = objectID;
    }

    public String getRestaurantName() {
        return restaurant;
    }

    public void setRestaurantName(String name) {
        this.restaurant = name;
    }

    public String getRestaurant_description() {
        return restaurant_description;
    }

    public void setRestaurant_description(String restaurant_description) {
        this.restaurant_description = restaurant_description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getRestaurant_URL() {
        return restaurant_URL;
    }

    public void setRestaurant_URL(String restaurant_URL) {
        this.restaurant_URL = restaurant_URL;
    }

    public String[] getMenus() {
        return menus;
    }

    public void setMenus(String[] menus) {
        this.menus = menus;
    }


    public String getRestaurant_logo_URL() {
        return restaurant_logo_URL;
    }

    public void setRestaurant_logo_URL(String restaurant_logo_URL) {
        this.restaurant_logo_URL = restaurant_logo_URL;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

}
