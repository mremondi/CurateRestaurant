package curatetechnologies.com.curate.models;

/**
 * Created by mremondi on 10/1/17.
 */

public class MenuItem {

    private Long itemID;
    private String itemName;


    public MenuItem(){
        // needed for firebase
    }

    public MenuItem(Long itemID, String itemName) {
        this.itemID = itemID;
        this.itemName = itemName;
    }


    public String getItemName(){
        return this.itemName;
    }

    public Long getItemID() {
        return this.itemID;
    }

    public void setItemID(Long itemID){
        this.itemID = itemID;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }
}
