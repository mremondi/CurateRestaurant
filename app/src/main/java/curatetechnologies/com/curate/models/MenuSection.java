package curatetechnologies.com.curate.models;

/**
 * Created by mremondi on 11/22/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuSection {

    @SerializedName("Section")
    @Expose
    private String section;
    @SerializedName("MenuSectionID")
    @Expose
    private Integer menuSectionID;

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

}