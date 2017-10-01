package curatetechnologies.com.curate.models;

/**
 * Created by mremondi on 10/1/17.
 */

public class MenuItem {

    private String itemID;
    private String itemName;


    public MenuItem(){
        // needed for firebase
    }

    public MenuItem(String itemID, String itemName) {
        this.itemID = itemID;
        this.itemName = itemName;
    }


    public String getItemName(){
        return this.itemName;
    }

}
