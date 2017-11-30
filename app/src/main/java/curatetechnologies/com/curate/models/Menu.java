package curatetechnologies.com.curate.models;

/**
 * Created by mremondi on 10/2/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu {

    @SerializedName("Menu_ID")
    @Expose
    private Integer menuID;
    @SerializedName("Menu_Name")
    @Expose
    private String menuName;
    @SerializedName("Menu_RestaurantID")
    @Expose
    private Integer restaurantID;
    @SerializedName("MenuSections")
    @Expose
    private List<MenuSection> menuSections = null;

    public Integer getMenuID() {
        return menuID;
    }

    public void setMenuID(Integer menuID) {
        this.menuID = menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(Integer restaurantID) {
        this.restaurantID = restaurantID;
    }

    public List<MenuSection> getMenuSections() {
        return menuSections;
    }

    public void setMenuSections(List<MenuSection> menuSections) {
        this.menuSections = menuSections;
    }

}