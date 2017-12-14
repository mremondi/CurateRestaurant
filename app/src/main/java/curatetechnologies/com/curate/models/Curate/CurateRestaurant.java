package curatetechnologies.com.curate.models.Curate;

/**
 * Created by mremondi on 10/9/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CurateRestaurant {

    @SerializedName("Restaurant_ID")
    @Expose
    private Integer iD;
    @SerializedName("Restaurant_Name")
    @Expose
    private String name;
    @SerializedName("Restaurant_Description")
    @Expose
    private String description;
    @SerializedName("Restaurant_URL")
    @Expose
    private String uRL;
    @SerializedName("Restaurant_HoursOfOperations")
    @Expose
    private Object hoursOfOperations;
    @SerializedName("Restaurant_Address")
    @Expose
    private String address;
    @SerializedName("Restaurant_Zipcode")
    @Expose
    private String zipcode;
    @SerializedName("Restaurant_Coordinates")
    @Expose
    private Coordinates coordinates;
    @SerializedName("Restaurant_CuisineTags")
    @Expose
    private String cuisineTags;
    @SerializedName("Restaurant_PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("Restaurant_LogoURL")
    @Expose
    private String logoURL;
    @SerializedName("Restaurant_NumberOfItemRatings")
    @Expose
    private Integer numberOfItemRatings;
    @SerializedName("Restaurant_SumOfItemRatings")
    @Expose
    private Integer sumOfItemRatings;
    @SerializedName("Restaurant_RestaurantManagerID")
    @Expose
    private Object restaurantManagerID;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
    }

    public Object getHoursOfOperations() {
        return hoursOfOperations;
    }

    public void setHoursOfOperations(Object hoursOfOperations) {
        this.hoursOfOperations = hoursOfOperations;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCuisineTags() {
        return cuisineTags;
    }

    public void setCuisineTags(String cuisineTags) {
        this.cuisineTags = cuisineTags;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public Integer getNumberOfItemRatings() {
        return numberOfItemRatings;
    }

    public void setNumberOfItemRatings(Integer numberOfItemRatings) {
        this.numberOfItemRatings = numberOfItemRatings;
    }

    public Integer getSumOfItemRatings() {
        return sumOfItemRatings;
    }

    public void setSumOfItemRatings(Integer sumOfItemRatings) {
        this.sumOfItemRatings = sumOfItemRatings;
    }

    public Object getRestaurantManagerID() {
        return restaurantManagerID;
    }

    public void setRestaurantManagerID(Object restaurantManagerID) {
        this.restaurantManagerID = restaurantManagerID;
    }

}