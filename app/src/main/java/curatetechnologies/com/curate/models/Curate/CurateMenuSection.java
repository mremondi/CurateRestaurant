package curatetechnologies.com.curate.models.Curate;

/**
 * Created by mremondi on 11/22/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import curatetechnologies.com.curate.models.Curate.CurateMenuItem;

public class CurateMenuSection {

    @SerializedName("MenuSection_Name")
    @Expose
    private String section;
    @SerializedName("MenuSection_ID")
    @Expose
    private Integer menuSectionID;
    @SerializedName("Items")
    @Expose
    private List<CurateMenuItem> items;

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

    public List<CurateMenuItem> getItems() {
        return items;
    }

    public void setItems(List<CurateMenuItem> items) {
        this.items = items;
    }

}