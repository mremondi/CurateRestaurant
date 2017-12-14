package curatetechnologies.com.curate.models.Firebase;

/**
 * Created by mremondi on 12/14/17.
 */

public class FirebaseMenuItem {

    private String itemID;
    private String itemName;
    private String itemPrice;

    public FirebaseMenuItem(){
        // needed for firebase
    }

    public FirebaseMenuItem(String itemID, String itemName, String itemPrice) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
