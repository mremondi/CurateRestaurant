package curatetechnologies.com.curate.network;

import java.util.ArrayList;

import curatetechnologies.com.curate.models.Curate.CurateMenu;
import curatetechnologies.com.curate.models.Curate.CurateRestaurant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by mremondi on 10/2/17.
 */

public interface CurateAPI {

    @Headers("api_authorization: 613f1d29-0dc9-428a-b636-794d1ce2f1a3")
    @GET("menuWithItems")
    Call<CurateMenu[]> getMenuItemsBySection(@Query("menuId") int menuId);

    @Headers("api_authorization: 613f1d29-0dc9-428a-b636-794d1ce2f1a3")
    @POST("itemAvailability")
    Call<String> setItemAvailability(@Header("authorization") String authId, @Query("itemId") int itemId, @Query("available") boolean available);

    @Headers("api_authorization: 613f1d29-0dc9-428a-b636-794d1ce2f1a3")
    @GET("menus/forRestaurantId")
    Call<ArrayList<CurateMenu>> getMenusForRestaurant(@Query("restaurantId") String restaurantId);

    @Headers("api_authorization: 613f1d29-0dc9-428a-b636-794d1ce2f1a3")
    @GET("restaurants")
    Call<CurateRestaurant[]> getRestaurantById(@Query("ids") String ids);

    @Headers("api_authorization: 613f1d29-0dc9-428a-b636-794d1ce2f1a3")
    @POST("restaurant/setAvailability")
    Call<Integer> setRestaurantAvailability(@Header("authorization") String authId, @Query("restaurantId") String restaurantId, @Query("isOpen") Boolean isOpen);

}
