package com.example.emedcom_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference mDbUSer;

    Button med_sell;
    Button med_buy, donate;
    String tet,type;
    userData usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        med_sell = (Button) findViewById(R.id.buttonsell);
        med_buy = (Button) findViewById(R.id.buttonbuy);
        donate = (Button)  findViewById(R.id.donate_med);

        //redirecting to sell page
        med_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sellIntent = new Intent(getApplicationContext(), Sell.class);
                startActivity(sellIntent);
            }
        });

        //redirecting to buy page
        med_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent buyIntent = new Intent(getApplicationContext(), Buy.class);
                startActivity(buyIntent);
            }
        });

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent donateIntent = new Intent(getApplicationContext(), UserDonate.class);
                startActivity(donateIntent);
            }
        });

        mDbUSer= FirebaseDatabase.getInstance().getReference("registration");
        mDbUSer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for( DataSnapshot ds: dataSnapshot.getChildren()){
                    usr = ds.getValue(userData.class);
                    //userDist = ds.getValue().toString();
                    if(usr.getEmail().equals(tet)){
                        type=usr.getUser_type();
                        break;}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        //ini
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        tet=currentUser.getEmail();


        //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      /*  fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });  */


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        updateNavHeader();

        //default fragment for home
     /*   FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,new HomeFragment());
        ft.commit();    */

      /*  Intent homeIntent = new Intent(getApplicationContext(), Home.class);
        startActivity(homeIntent);
        finish();   */

        navigationView.setCheckedItem(R.id.nav_home);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       /* if(type.equals("Collection Centre")){
        invalidateOptionsMenu();
        menu.findItem(R.id.action_track).setVisible(false);}*/
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            case R.id.action_rewards:
                Toast.makeText(this, "My Rewards", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home.this,RewardsActivity.class);
                startActivity(intent);
        }
        switch (item.getItemId()) {

            case R.id.action_orders:
                Toast.makeText(this, "My Orders", Toast.LENGTH_SHORT).show();

        }
        switch (item.getItemId()) {

            case R.id.action_history:
                Toast.makeText(this, "User History", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home.this,UserHistory.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);}

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            getSupportActionBar().setTitle("Home");
            Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show();
            //  getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();

          /*  FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container,new HomeFragment());
            ft.commit();   */

            Intent homeIntent = new Intent(getApplicationContext(), Home.class);
            startActivity(homeIntent);
            finish();


        } else if (id == R.id.nav_profile) {

            getSupportActionBar().setTitle("Profile");
            Toast.makeText(this, "PROFILE", Toast.LENGTH_SHORT).show();
            //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
          /*  Intent i = new Intent(Home.this, ChangePassword.class);
            startActivity(i);
            finish();  */


            Intent profileIntent = new Intent(getApplicationContext(), Profile.class);
            startActivity(profileIntent);
            finish();


            // getSupportFragmentManager().beginTransaction().replace(R.id.container,new ProfileFragment()).commit();

        }
        else if (id == R.id.nav_settings) {


         /*   getSupportActionBar().setTitle("Settings");
            Toast.makeText(this, "SETTINGS", Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new SettingsFragment()).commit();
*/
        }
        else if(id == R.id.nav_signout) {

            FirebaseAuth.getInstance().signOut();
            Intent loginActivity = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(loginActivity);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void updateNavHeader() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        TextView navUsername = headerView.findViewById(R.id.nav_username);
        TextView navUserMail = headerView.findViewById(R.id.nav_user_mail);
        ImageView navUserPhot = headerView.findViewById(R.id.nav_user_photo);

        navUserMail.setText(currentUser.getEmail());
        navUsername.setText(currentUser.getDisplayName());
        //now we will use glide to load user image
        //first we need ti import the library
        Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhot);
    }




}
