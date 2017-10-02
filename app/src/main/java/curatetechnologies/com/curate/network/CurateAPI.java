package curatetechnologies.com.curate.network;

import curatetechnologies.com.curate.models.Menu;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mremondi on 10/2/17.
 */

public interface CurateAPI {


    @GET("menus/{id}")
    Call<Menu> getMenuById(@Path("id") String id);

}
