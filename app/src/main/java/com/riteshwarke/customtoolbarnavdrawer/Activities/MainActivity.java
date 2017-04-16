package com.riteshwarke.customtoolbarnavdrawer.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.riteshwarke.customtoolbarnavdrawer.Fragments.AppConstants;
import com.riteshwarke.customtoolbarnavdrawer.Fragments.GalaryFragment;
import com.riteshwarke.customtoolbarnavdrawer.Fragments.ImportFragment;
import com.riteshwarke.customtoolbarnavdrawer.Fragments.MainFragment;
import com.riteshwarke.customtoolbarnavdrawer.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageButton search;
    private ImageButton mic;
    private ImageButton delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle("Welcome!!");
        getSupportActionBar().setTitle("Welcome!!");

        search = (ImageButton) findViewById(R.id.search);
        delete = (ImageButton) findViewById(R.id.delete);
        mic = (ImageButton) findViewById(R.id.mic);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        init();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(AppConstants.ImportFlag || AppConstants.GalleryFlag){
             AppConstants.ImportFlag = false;
            AppConstants.GalleryFlag = false;
            Intent inten = new Intent(this, MainActivity.class);
            inten.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(inten);
        }

        else {
            System.exit(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            ImportFragment importFragment = new ImportFragment();
            loadFragment(importFragment, null, R.id.fragmentContainer, "null");
            AppConstants.ImportFlag = true;
            getSupportActionBar().setTitle("Import!!");
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            GalaryFragment galaryFragment = new GalaryFragment();
            loadFragment(galaryFragment, null, R.id.fragmentContainer, "null");
            AppConstants.GalleryFlag = true;
            getSupportActionBar().setTitle("Gallery!!");

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void init(){
        MainFragment mainFragment = new MainFragment();
        loadFragment(mainFragment, null, R.id.fragmentContainer, "null");

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Search clicked", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "delete clicked", Toast.LENGTH_SHORT).show();
            }
        });
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "mic clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void loadFragment(Fragment fragment, Bundle args, int containerId, String title)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        //fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        //set arguments
        fragment.setArguments(args);
        //load fragment
        //FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(title);
        fragmentTransaction.replace(containerId, fragment);
        fragmentTransaction.commitAllowingStateLoss();




    }

}
