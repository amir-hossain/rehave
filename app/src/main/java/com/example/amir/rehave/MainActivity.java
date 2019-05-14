package com.example.amir.rehave;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amir.rehave.database.DatabaseHelper;
import com.example.amir.rehave.database.Post;
import com.example.amir.rehave.database.RehubDao;
import com.example.amir.rehave.fragments.AboutFragment;
import com.example.amir.rehave.fragments.AddictionInformationFragment;
import com.example.amir.rehave.fragments.ArchiveFragment;
import com.example.amir.rehave.fragments.CommunityFragment;
import com.example.amir.rehave.fragments.ExpertOpinionFragment;
import com.example.amir.rehave.fragments.LoginFragment;
import com.example.amir.rehave.fragments.MainFragment;
import com.example.amir.rehave.fragments.NoInternetConnection;
import com.example.amir.rehave.fragments.RelapseProtectionFragment;
import com.example.amir.rehave.fragments.SingUpFragment;
import com.example.amir.rehave.link.LinkMethods;
import com.example.amir.rehave.manager.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SingUpFragment.RegistrationCompleteListener, LoginFragment.RegistrationButtonClickListenter{

    private TextView userNameView;

    private Fragment fragmentToRun;

    private DrawerLayout drawer;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        LinkMethods.getInstance().initializeDatabase(this);

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
        fragmentToRun=new MainFragment();
        runFragment();

        navigationView.setCheckedItem(R.id.nav_main);

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            fragmentToRun=new MainFragment();
            runFragment();

        } else if (id == R.id.nav_about) {
            fragmentToRun=AboutFragment.newInstance();
            runFragment();

        }else if (id == R.id.nav_registration) {
            fragmentToRun=SingUpFragment.newInstance(this);
            runFragment();

        } else if (id == R.id.nav_login) {
            fragmentToRun=LoginFragment.newInstance(this);
            runFragment();

        } else if (id == R.id.nav_addiction) {
            fragmentToRun=AddictionInformationFragment.newInstance();
            runFragment();


        } else if (id == R.id.nav_protection) {
            fragmentToRun=RelapseProtectionFragment.newInstance();
            runFragment();

        } else if (id == R.id.nav_archive) {
            fragmentToRun=ArchiveFragment.newInstance();
            runFragment();

        } else if (id == R.id.nav_forum) {
            fragmentToRun=CommunityFragment.newInstance();
            runFragment();

        }else if (id == R.id.nav_expert_opinion) {
        fragmentToRun=new ExpertOpinionFragment();
        runFragment();

    }else {
            SharedPrefManager.getInstance(this).clear();
            fragmentToRun=LoginFragment.newInstance(this);
            runFragment();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    @Override
    public void OnRegistrationComplete() {
        fragmentToRun=LoginFragment.newInstance(this);
        runFragment();
        navigationView.setCheckedItem(R.id.nav_login);
    }

    private void runFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_layout, fragmentToRun)
                .addToBackStack(null)
                .commit();
//        if(Utils.isNetworkConnected(this)){
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.container_layout, fragmentToRun)
//                    .addToBackStack(null)
//                    .commit();
//        }else {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.container_layout, NoInternetConnection.newInstance())
//                    .addToBackStack(null)
//                    .commit();
//        }

    }

    @Override
    public void wantToGoToRegistration() {
        fragmentToRun=SingUpFragment.newInstance(this);
        runFragment();
        navigationView.setCheckedItem(R.id.nav_registration);
    }


    public void retry(View v){
        runFragment();
    }
}