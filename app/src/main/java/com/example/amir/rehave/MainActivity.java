package com.example.amir.rehave;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.amir.rehave.fragments.AboutFragment;
import com.example.amir.rehave.fragments.AddictionInformationFragment;
import com.example.amir.rehave.fragments.ArchiveFragment;
import com.example.amir.rehave.fragments.CommunityFragment;
import com.example.amir.rehave.fragments.ExpertOpinionFragment;
import com.example.amir.rehave.fragments.LoginFragment;
import com.example.amir.rehave.fragments.MainFragment;
import com.example.amir.rehave.fragments.PathFragment;
import com.example.amir.rehave.fragments.RelapseProtectionFragment;
import com.example.amir.rehave.fragments.SingUpFragment;
import com.example.amir.rehave.fragments.StatusFragment;
import com.example.amir.rehave.link.LinkMethods;
import com.example.amir.rehave.manager.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SingUpFragment.RegistrationCompleteListener, LoginFragment.RegistrationButtonClickListenter {

    private TextView userNameView;

    private Fragment fragmentToRun;

    private DrawerLayout drawer;

    private NavigationView navigationView;

    @BindView(R.id.title)
    public TextView title;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

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
        title.setText(R.string.main);
        fragmentToRun = new MainFragment();
        runFragment();

        navigationView.setCheckedItem(R.id.nav_main);

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            title.setText(R.string.main);
            fragmentToRun = new MainFragment();
            runFragment();

        } else if (id == R.id.nav_about) {
            title.setText(R.string.about);
            fragmentToRun = AboutFragment.newInstance();
            runFragment();

        }else if (id == R.id.nav_status) {
            title.setText(R.string.status_of_today);
            fragmentToRun = StatusFragment.newInstance();
            runFragment();

        } else if (id == R.id.nav_registration) {
            title.setText(R.string.registration);
            fragmentToRun = SingUpFragment.newInstance(this);
            runFragment();

        } else if (id == R.id.nav_login) {
            title.setText(R.string.login);
            fragmentToRun = LoginFragment.newInstance(this);
            runFragment();

        } else if (id == R.id.nav_path) {
            title.setText(R.string.overcome_addiction);
            fragmentToRun = new PathFragment();
            runFragment();


        }else if (id == R.id.nav_archive) {
            title.setText(R.string.archive);
            fragmentToRun = ArchiveFragment.newInstance();
            runFragment();

        } else if (id == R.id.nav_forum) {
            title.setText(R.string.forum);
            fragmentToRun = CommunityFragment.newInstance();
            runFragment();

        } else if (id == R.id.nav_expert_opinion) {
            title.setText(R.string.expert_opinion);
            fragmentToRun = new ExpertOpinionFragment();
            runFragment();

        } else {
            SharedPrefManager.getInstance(this).clear();
            fragmentToRun = LoginFragment.newInstance(this);
            runFragment();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    @Override
    public void OnRegistrationComplete() {
        title.setText(R.string.login);
        navigationView.setCheckedItem(R.id.nav_login);
        fragmentToRun = LoginFragment.newInstance(this);
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
        title.setText(R.string.registration);
        navigationView.setCheckedItem(R.id.nav_registration);
        fragmentToRun = SingUpFragment.newInstance(this);
        runFragment();
        navigationView.setCheckedItem(R.id.nav_registration);
    }


    public void retry(View v) {
        runFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.new_post_menu, menu);
        return true;
    }

    FrameLayout redCircle;
    TextView countTextView;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem alertMenuItem = menu.findItem(R.id.action_notificaion);
        FrameLayout rootView = (FrameLayout) alertMenuItem.getActionView();

        redCircle = (FrameLayout) rootView.findViewById(R.id.view_alert_red_circle);
        countTextView = (TextView) rootView.findViewById(R.id.view_alert_count_textview);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(alertMenuItem);
            }
        });

        return super.onPrepareOptionsMenu(menu);
    }

    public void setCartNotiCount(int productCounter) {
        countTextView.setText(String.valueOf(productCounter));
        redCircle.setVisibility((productCounter > 0) ? VISIBLE : GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Utils.notification > 0) {
            setCartNotiCount(Utils.notification);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_notificaion:
                int previousTotal = SharedPrefManager.getInstance(getApplicationContext())
                        .getint(SharedPrefManager.TOTAL_PREF);
                SharedPrefManager.getInstance(getApplicationContext())
                        .setInt(SharedPrefManager.TOTAL_PREF, previousTotal + Utils.notification);
                Utils.notification = 0;
                setCartNotiCount(Utils.notification);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}