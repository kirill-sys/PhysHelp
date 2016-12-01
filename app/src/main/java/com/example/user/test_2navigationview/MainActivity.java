package com.example.user.test_2navigationview;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView.Adapter mAdapter;
    private ArrayList<Data_of_cv> dataofcvs;
    private RecyclerView recList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //  recyclerView
        recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        recList.setNestedScrollingEnabled(false);

        recList.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        mAdapter = new MyAdapterForMain(initializeData());
        recList.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyAdapterForMain) mAdapter).setOnItemClickListener(new MyAdapterForMain
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                switch (position) {
                    case 0 :{
                        Toast.makeText(MainActivity.this, " Clicked on Item " + position, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 1 :{
                        startActivity(new Intent(MainActivity.this, WantEat.class));
                        break;
                    }
                    case 2 :{
                        Toast.makeText(MainActivity.this, " Clicked on Item " + position, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 3 :{
                        Toast.makeText(MainActivity.this, " Clicked on Item " + position, Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        });
    }

    private ArrayList<Data_of_cv> initializeData(){
        dataofcvs = new ArrayList<Data_of_cv>();
        dataofcvs.add(new Data_of_cv("Иду готовить", R.mipmap.food));
        dataofcvs.add(new Data_of_cv("Хочу есть",  R.mipmap.want_food));
        dataofcvs.add(new Data_of_cv("Нужна помощь",  R.mipmap.need_help));
        dataofcvs.add(new Data_of_cv("Хочу помочь",  R.mipmap.need_money));
        return dataofcvs;
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
        // Inflate the menu; this adds dataofcvs to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /*@Override
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
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_help) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Помощь")
                    .setMessage("Все действия тривиальны")
                    .setIcon(R.mipmap.nav_view_help)
                    .setCancelable(false)
                    .setNegativeButton("ОК",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).show();
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_dev) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("О нас")
                    .setMessage("- Сысоев Кирилл (Android, ФИВТ, 1курс)\n"+
                            "- Морковкин Василий (Json-парщик, ФИВТ, 1 курс)\n"+
                            "- Валентинов Александр (Frontend Web, ФИВТ, 1 курс)\n" +
                            "- Антон Ковальков (Backend, ФУПМ, 2 курс)")
                    .setIcon(R.mipmap.nav_view_dev)
                    .setCancelable(false)
                    .setNegativeButton("ОК",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
