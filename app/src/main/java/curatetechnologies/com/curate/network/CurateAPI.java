package curatetechnologies.com.curate.network;

import java.util.ArrayList;
import java.util.List;

import curatetechnologies.com.curate.models.Menu;
import curatetechnologies.com.curate.models.Restaurant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mremondi on 10/2/17.
 */

public interface CurateAPI {


    @GET("menus")
    Call<Menu> getMenuById(@Query("menuId") String menuId);

    @GET("menus/forRestaurantId")
    Call<ArrayList<Menu>> getMenusForRestaurant(@Query("restaurantId") String restaurantId);

    @GET("restaurants")
    Call<Restaurant> getRestaurantById(@Query("ids") String ids);

}
