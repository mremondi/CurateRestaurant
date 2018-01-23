package curatetechnologies.com.curate.network;

import java.util.ArrayList;

import curatetechnologies.com.curate.models.Curate.CurateMenu;
import curatetechnologies.com.curate.models.Curate.CurateRestaurant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by mremondi on 10/2/17.
 */

public interface CurateAPI {


    @GET("menus")
    Call<CurateMenu> getMenuById(@Query("menuId") int menuId);

    @GET("menuWithItems")
    Call<CurateMenu[]> getMenuItemsBySection(@Query("menuId") int menuId);

    @POST("itemAvailability")
    Call<String> setItemAvailability(@Query("itemId") int itemId, @Query("available") boolean available);

    @GET("menus/forRestaurantId")
    Call<ArrayList<CurateMenu>> getMenusForRestaurant(@Query("restaurantId") String restaurantId);

    @GET("restaurants")
    Call<CurateRestaurant[]> getRestaurantById(@Query("ids") String ids);

    @POST("restaurant/setAvailability")
    Call<Integer> setRestaurantAvailability(@Query("restaurantId") String restaurantId, @Query("isOpen") Boolean isOpen);

}
