package com.example.amir.rehave;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.amir.rehave.fragments.AboutFragment;
import com.example.amir.rehave.fragments.AddictionInformationFragment;
import com.example.amir.rehave.fragments.ArchiveFragment;
import com.example.amir.rehave.fragments.ForumFragment;
import com.example.amir.rehave.fragments.LoginFragment;
import com.example.amir.rehave.fragments.MainFragment;
import com.example.amir.rehave.fragments.RelapseProtectionFragment;
import com.example.amir.rehave.fragments.SingUpFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initializeNavDrawer();
    }


    private void initializeNavDrawer() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(

                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, MainFragment.newInstance()).commit();

        navigationView.setCheckedItem(R.id.nav_main);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_main) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, MainFragment.newInstance()).addToBackStack(null).commit();

        } else if (id == R.id.nav_about) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, AboutFragment.newInstance()).addToBackStack(null).commit();
        }else if (id == R.id.nav_registration) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, SingUpFragment.newInstance()).addToBackStack(null).commit();

        } else if (id == R.id.nav_login) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, LoginFragment.newInstance()).addToBackStack(null).commit();

        } else if (id == R.id.nav_addiction) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, AddictionInformationFragment.newInstance()).addToBackStack(null).commit();


        } else if (id == R.id.nav_protection) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, RelapseProtectionFragment.newInstance()).addToBackStack(null).commit();

        } else if (id == R.id.nav_archive) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, ArchiveFragment.newInstance()).addToBackStack(null).commit();

        } else if (id == R.id.nav_forum) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, ForumFragment.newInstance()).addToBackStack(null).commit();

        }else {
            Intent intent=new Intent(this,LoginFragment.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


}