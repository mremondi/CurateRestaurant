package curatetechnologies.com.curate.models;

/**
 * Created by mremondi on 11/22/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuSection {

    @SerializedName("MenuSection_Name")
    @Expose
    private String section;
    @SerializedName("MenuSection_ID")
    @Expose
    private Integer menuSectionID;
    @SerializedName("Items")
    @Expose
    private List<MenuItem> items;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getMenuSectionID() {
        return menuSectionID;
    }

    public void setMenuSectionID(Integer menuSectionID) {
        this.menuSectionID = menuSectionID;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

}