package com.example.amir.rehave;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.amir.rehave.fragments.AboutFragment;
import com.example.amir.rehave.fragments.AddictionInformationFragment;
import com.example.amir.rehave.fragments.ArchiveFragment;
import com.example.amir.rehave.fragments.CommunityFragment;
import com.example.amir.rehave.fragments.LoginFragment;
import com.example.amir.rehave.fragments.MainFragment;
import com.example.amir.rehave.fragments.RelapseProtectionFragment;
import com.example.amir.rehave.fragments.SingUpFragment;
import com.example.amir.rehave.manager.SharedPrefManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SingUpFragment.RegistrationCompleteListener, LoginFragment.RegistrationButtonClickListenter{

    private TextView userNameView;

    private DrawerLayout drawer;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initializeNavDrawer();

        View headerView = navigationView.getHeaderView(0);

        userNameView = headerView.findViewById(R.id.user_name);

        userNameView.setText(SharedPrefManager.getInstance(getApplicationContext()).getString(SharedPrefManager.NAME_PREF));
    }


    private void initializeNavDrawer() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(

                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, MainFragment.newInstance()).commit();

        navigationView.setCheckedItem(R.id.nav_main);

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_main) {

            runFragment(MainFragment.newInstance());

        } else if (id == R.id.nav_about) {

            runFragment(AboutFragment.newInstance());

        }else if (id == R.id.nav_registration) {

            runFragment(SingUpFragment.newInstance(this));

        } else if (id == R.id.nav_login) {

            runFragment(LoginFragment.newInstance(this));

        } else if (id == R.id.nav_addiction) {

            runFragment(AddictionInformationFragment.newInstance());


        } else if (id == R.id.nav_protection) {

            runFragment(RelapseProtectionFragment.newInstance());

        } else if (id == R.id.nav_archive) {

            runFragment(ArchiveFragment.newInstance());

        } else if (id == R.id.nav_forum) {

            runFragment(CommunityFragment.newInstance());

        }else {
            SharedPrefManager.getInstance(this).clear();
            runFragment(LoginFragment.newInstance(this));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    @Override
    public void OnRegistrationComplete() {
        runFragment(LoginFragment.newInstance(this));
        navigationView.setCheckedItem(R.id.nav_login);
    }

    private void runFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_layout, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void wantToGoToRegistration() {
        runFragment(SingUpFragment.newInstance(this));
        navigationView.setCheckedItem(R.id.nav_registration);
    }

}