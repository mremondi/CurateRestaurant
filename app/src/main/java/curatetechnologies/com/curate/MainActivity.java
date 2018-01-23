package curatetechnologies.com.curate;

import android.app.FragmentManager;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import curatetechnologies.com.curate.controllers.AboutUs;
import curatetechnologies.com.curate.controllers.CloseShop;
import curatetechnologies.com.curate.controllers.CompletedOrders;
import curatetechnologies.com.curate.controllers.CurrentOrderQueue;
import curatetechnologies.com.curate.controllers.LoginActivity;
import curatetechnologies.com.curate.controllers.SelectMenu;
import curatetechnologies.com.curate.controllers.NewOrderQueue;
import curatetechnologies.com.curate.controllers.Support;
import curatetechnologies.com.curate.models.Curate.CurateRestaurant;
import curatetechnologies.com.curate.network.CurateAPI;
import curatetechnologies.com.curate.network.CurateConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    String restaurantID;

    private final CurateAPI curateAPI = CurateConnection.setUpRetrofit();

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_layout);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        configureNavView();

        Fragment orderQueue = new NewOrderQueue();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.content_frame, orderQueue);
        transaction.commit();
    }

    private void configureNavView() {

        View navHeader = navigationView.getHeaderView(0);

        final ImageView ivRestaurantLogo = navHeader.findViewById(R.id.restaurant_logo);
        final TextView restaurantName = navHeader.findViewById(R.id.restaurant_name);
        final TextView username = navHeader.findViewById(R.id.username);

        navigationView.setNavigationItemSelectedListener(this);

        final AppCompatActivity activity = this;

        SharedPreferences prefs = getSharedPreferences("RESTAURANT_PREFS", MODE_PRIVATE);
        restaurantID = prefs.getString("restaurantID", "");

        Call<CurateRestaurant[]> restaurantQuery = curateAPI.getRestaurantById(restaurantID);
        restaurantQuery.enqueue(new Callback<CurateRestaurant[]>() {
            @Override
            public void onResponse(Call<CurateRestaurant[]> call, final Response<CurateRestaurant[]> response) {
                if (response.body() != null) {
                    CurateRestaurant restaurant = response.body()[0];
                    restaurantName.setText(restaurant.getName());
                    username.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    Glide.with(activity).load(restaurant.getLogoURL()).into(ivRestaurantLogo);
                }
            }

            @Override
            public void onFailure(Call<CurateRestaurant[]> call, Throwable t) {
                Log.d("FAILURE", t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.order_queue){
            fragment =  new NewOrderQueue();
        }
        else if (id == R.id.current_orders){
            fragment = new CurrentOrderQueue();
        }
        else if (id == R.id.completed_orders){
            fragment = new CompletedOrders();
        }
        else if (id == R.id.manage_menu){
            fragment = new SelectMenu();
        }
        else if (id == R.id.close_shop){
            fragment = new CloseShop();
        }
        else if (id == R.id.contact_us){
            fragment = new Support();
        }
        else if (id == R.id.about_us) {
            fragment = new AboutUs();
        }
        else if (id == R.id.logout){

            SharedPreferences.Editor editor = getSharedPreferences("RESTAURANT_PREFS", MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();

            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        if (fragment != null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
