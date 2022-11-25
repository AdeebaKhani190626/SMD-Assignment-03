package com.ass2.i190626_190438;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class HomePage extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        replaceFragment(new MessagesTabLayout());

        /* Tab Layout - Bottom Bar */
        viewPager = findViewById(R.id.viewPager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new MessagesTabLayout(),"");
        adapter.addFragment(new CallTabLayout(),"");
        adapter.addFragment(new AddTabLayout(),"");
        adapter.addFragment(new UserTabLayout(),"");
        adapter.addFragment(new SettingsTabLayout(),"");

        viewPager.setAdapter(adapter);
        tabLayout = findViewById(R.id.tabLayout_id);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_mode_comment_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_call_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_add_24);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_baseline_supervised_user_circle_24);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_baseline_settings_24);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                if (position == 0) {
                    replaceFragment(new MessagesTabLayout());
                } if (position == 1) {
                    replaceFragment(new CallTabLayout());
                } if (position == 2) {
                    replaceFragment(new AddTabLayout());
                } if (position == 3) {
                    replaceFragment(new UserTabLayout());
                } if (position == 4) {
                    replaceFragment(new SettingsTabLayout());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /* Drawer - Navigation Top Bar */
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);

                switch(id) {
                    case R.id.nav_profile:
                        //replaceFragment(new EditProfilePage());
                        Intent intent02 = new Intent(HomePage.this, Profile.class);
                        startActivity(intent02);
                        break;
                    case R.id.nav_logout:
                    {
                        SharedPreferences sharedPreferences = getSharedPreferences(SignIn.PREFS_NAME,0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("hasLoggedIn",false);
                        editor.commit();
                        Intent intent = new Intent(HomePage.this, SelectingAccount.class);
                        startActivity(intent);
                        break;
                    }
                    default:
                        return true;
                }
                return true;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}