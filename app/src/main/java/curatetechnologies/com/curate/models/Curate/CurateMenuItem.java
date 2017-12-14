package curatetechnologies.com.curate.models.Curate;

/**
 * Created by mremondi on 10/1/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurateMenuItem {

    @SerializedName("Item_ID")
    @Expose
    private Integer itemID;
    @SerializedName("Item_Name")
    @Expose
    private String itemName;
    @SerializedName("Item_Description")
    @Expose
    private String itemDescription;
    @SerializedName("Item_Price")
    @Expose
    private Double itemPrice;
    @SerializedName("Item_ItemTags")
    @Expose
    private Object itemItemTags;
    @SerializedName("Item_NumberOfOverallRatings")
    @Expose
    private Integer itemNumberOfOverallRatings;
    @SerializedName("Item_SumOfOverallRatings")
    @Expose
    private Integer itemSumOfOverallRatings;
    @SerializedName("Menu_ID")
    @Expose
    private Integer menuID;
    @SerializedName("MenuSection_ID")
    @Expose
    private Integer menuSectionID;
    @SerializedName("distance_in_miles")
    @Expose
    private Double distanceInMiles;
    @SerializedName("Menu_Name")
    @Expose
    private String menuName;
    @SerializedName("MenuSection_Name")
    @Expose
    private String menuSectionName;
    @SerializedName("Restaurant_ID")
    @Expose
    private String restaurantID;
    @SerializedName("Restaurant_Name")
    @Expose
    private String restaurantName;

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Object getItemItemTags() {
        return itemItemTags;
    }

    public void setItemItemTags(Object itemItemTags) {
        this.itemItemTags = itemItemTags;
    }

    public Integer getItemNumberOfOverallRatings() {
        return itemNumberOfOverallRatings;
    }

    public void setItemNumberOfOverallRatings(Integer itemNumberOfOverallRatings) {
        this.itemNumberOfOverallRatings = itemNumberOfOverallRatings;
    }

    public Integer getItemSumOfOverallRatings() {
        return itemSumOfOverallRatings;
    }

    public void setItemSumOfOverallRatings(Integer itemSumOfOverallRatings) {
        this.itemSumOfOverallRatings = itemSumOfOverallRatings;
    }

    public Integer getMenuID() {
        return menuID;
    }

    public void setMenuID(Integer menuID) {
        this.menuID = menuID;
    }

    public Integer getMenuSectionID() {
        return menuSectionID;
    }

    public void setMenuSectionID(Integer menuSectionID) {
        this.menuSectionID = menuSectionID;
    }

    public Double getDistanceInMiles() {
        return distanceInMiles;
    }

    public void setDistanceInMiles(Double distanceInMiles) {
        this.distanceInMiles = distanceInMiles;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuSectionName() {
        return menuSectionName;
    }

    public void setMenuSectionName(String menuSectionName) {
        this.menuSectionName = menuSectionName;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}