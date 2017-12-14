package curatetechnologies.com.curate.models.Curate;

/**
 * Created by mremondi on 10/2/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurateMenu {

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
    private List<CurateMenuSection> curateMenuSections = null;

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

    public List<CurateMenuSection> getCurateMenuSections() {
        return curateMenuSections;
    }

    public void setCurateMenuSections(List<CurateMenuSection> curateMenuSections) {
        this.curateMenuSections = curateMenuSections;
    }

}