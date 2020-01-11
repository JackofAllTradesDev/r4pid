package com.rivera.rapid.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rivera.rapid.R;

public class DashboardActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()){
                case R.id.homeMenu :
                    InitializeFragment(homeFragment);
                    return true;
                case R.id.ScanMenu :
                    InitializeFragment(scanFragment);
                    return true;
                case R.id.mainMenu :
                    InitializeFragment(porceleinFragment);
                    return true;
                case R.id.profileMenu :
                    InitializeFragment(profileFragment);
                    return true;
                case R.id.shopMenu :
                    InitializeFragment(shopFragment);
                    return true;
                default:
                    return false;

            }

        }
    };
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayouts;

    private HomeFragment homeFragment;
    private ScanFragment scanFragment;
    private PorceleinFragment porceleinFragment;
    private ProfileFragment profileFragment;
    private ShopFragment shopFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        frameLayouts = findViewById(R.id.frameLayout);

        homeFragment = new HomeFragment();
        scanFragment = new ScanFragment();
        porceleinFragment = new PorceleinFragment();
        profileFragment = new ProfileFragment();
        shopFragment = new ShopFragment();

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        mOnNavigationItemSelectedListener.onNavigationItemSelected(bottomNavigationView.getMenu().getItem(0));



    }

    private void InitializeFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();


    }
}
