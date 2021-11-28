package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.myapplication.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            Fragment fragment =  HomeFragment.newInstance("All");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.FrameLayout, fragment)
                    .commit();
        }catch (Exception e){
            Log.e("AdminPage", e.getMessage());
        }

        binding.bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String menu;

                switch (item.getItemId()){
                    case R.id.menuHome:
                        menu = "Home";
                        break;
                    case R.id.menuFavorite:
                        menu = "Favorite";
                        break;
                    default:
                        menu = "Home";
                        break;
                }

                try {
                    Fragment fragment =  HomeFragment.newInstance(menu);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.FrameLayout, fragment)
                            .commit();
                }catch (Exception e){
                    Log.e("AdminPage", e.getMessage());
                }

                return true;
            }
        });
    }
}