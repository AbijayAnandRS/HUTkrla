package com.abijayana.user.hutkrla;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by user on 17-03-2017.
 */

public class Mainpage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        View ju= navigationView.inflateHeaderView(R.layout.navigation_header);

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.usersas){

        }
        if(id==R.id.foodite){
            goclass(MainActivity.class);

        }
        if(id==R.id.usersas)goclass(contactus.class);
        if(id==R.id.videokann){
            try {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/a/iitbbs.ac.in/file/d/0BySyx-Tl4xjaUnBsbjE3WXNGOEU/view?usp=docslist_api"));
                startActivity(i);}catch (ActivityNotFoundException e){
                Toast.makeText(Mainpage.this,"Install WebBrowser",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    public void goclass(Class g){
        Intent i=new Intent(Mainpage.this,g);
        startActivity(i);

    }

}
