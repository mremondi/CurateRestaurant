package curatetechnologies.com.curate.models;

/**
 * Created by mremondi on 10/1/17.
 */

public class MenuItem {

    private String itemID;
    private String itemName;
    private String itemPrice;


    public MenuItem(){
        // needed for firebase
    }

    public MenuItem(String itemID, String itemName, String itemPrice) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }


    public String getItemName(){
        return this.itemName;
    }

    public String getItemID() {
        return this.itemID;
    }

    public void setItemID(String itemID){
        this.itemID = itemID;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    public void setItemPrice(String itemPrice){
        this.itemPrice = itemPrice;
    }

    public String getItemPrice(){
        return this.itemPrice;
    }
}

