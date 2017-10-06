package com.abijayana.user.hutkrla;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static View rootview1a;
    public static View rootview2a;
    public static View rootview3a;
    public static View rootview4a;
    SharedPreferences sf1,sf2,sf3,key,nde;int s;
    TabLayout tabLayout;
    public SectionsPagerAdapter mSectionsPagerAdapter;ConnectivityManager cm;
    public ViewPager mViewPager;
    Firebase fr;LayoutInflater lf1,lf2,lf3;foodadapter fdp,fdp1,fdp2,fdp3;ArrayList<food> ls1,ls2,ls3,ls4;GridView gv,gv2,gv3,gv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);int s;
        setSupportActionBar(toolbar);
        cm=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni=cm.getActiveNetworkInfo();
        if(ni==null) Toast.makeText(MainActivity.this,"Please Connect to Network",Toast.LENGTH_LONG).show();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.container12);
        fr=new Firebase("https://lllsstfffuuddd.firebaseio.com/KERALAHUT/food");

        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = (TabLayout)findViewById(R.id.tabs12);
        tabLayout.setupWithViewPager(mViewPager);
        lf1=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        lf2=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        lf3=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        rootview1a=lf1.inflate(R.layout.f1,null);
        rootview2a=lf2.inflate(R.layout.f2,null);
        rootview3a=lf3.inflate(R.layout.f3,null);

        sf1=this.getSharedPreferences("FNME",MODE_PRIVATE);
        sf2=this.getSharedPreferences("FPRCE",MODE_PRIVATE);
        sf3=this.getSharedPreferences("URRRL",MODE_PRIVATE);
        key=this.getSharedPreferences("KKY",MODE_PRIVATE);
        nde=this.getSharedPreferences("NDDDEE",MODE_PRIVATE);

        gv=(GridView)rootview1a.findViewById(R.id.gridView1);
        gv1=(GridView)rootview2a.findViewById(R.id.gridView2) ;
        gv2=(GridView)rootview3a.findViewById(R.id.gridView3) ;

        RelativeLayout layout1=(RelativeLayout)rootview1a.findViewById(R.id.loadingPanel1);
        RelativeLayout layout2=(RelativeLayout)rootview2a.findViewById(R.id.loadingPanel2);
        RelativeLayout layout3=(RelativeLayout)rootview3a.findViewById(R.id.loadingPanel3);



        ls1=new ArrayList<>();ls2=new ArrayList<>();ls3=new ArrayList<>();
        fdp=new foodadapter(MainActivity.this,R.layout.grid_food,ls1);
        fdp1=new foodadapter(MainActivity.this,R.layout.grid_food,ls2);
        fdp2=new foodadapter(MainActivity.this,R.layout.grid_food,ls3);

        gv.setAdapter(fdp);
        gv1.setAdapter(fdp1);
        gv2.setAdapter(fdp2);

        initialize("veg",ls1,fdp,layout1);position(gv,ls1);position(gv1,ls2);position(gv2,ls3);

        initialize("non",ls2,fdp1,layout2);
        initialize("others",ls3,fdp2,layout3);



    }
    public  static class abi1a extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater1, ViewGroup container,
                                 Bundle savedInstanceState) {



            return rootview1a;
        }

    }
    public  static class abi2a extends Fragment{

        @Override
        public View onCreateView(LayoutInflater inflater2, ViewGroup container,
                                 Bundle savedInstanceState) {


            return rootview2a;
        }

    }
    public static class abi3a extends Fragment{


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            return rootview3a;

        }
    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:

                    return new abi1a();


                case 1:

                    return new abi2a();
                case 2:
                    return new abi3a();


                default:
                    //this page does not exists
                    return null;
            }
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

        }

        @Override
        public int getCount() {

            return 3;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "VEG";
                case 1:
                    return "NON VEG";
                case 2:
                    return "OTHERS";





            }
            return null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void initialize(final String sf, final ArrayList<food> lst, final foodadapter f, final RelativeLayout loi){
        Query cv=fr.child(sf).orderByKey();
        cv.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                lst.clear();   loi.setVisibility(View.GONE);
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    food f1=new food();
                    f1.setKey(String.valueOf(dataSnapshot1.getKey()));
                    f1.setNme(String.valueOf(dataSnapshot1.child("name").getValue()));
                    f1.setPrice(String.valueOf(dataSnapshot1.child("price").getValue()));
                    f1.setUrl(String.valueOf(dataSnapshot1.child("url").getValue()));
                    f1.setNode(sf);
                    lst.add(f1);


                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        fr.child(sf).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               f.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                f.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                f.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                f.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });





    }

    void position(GridView gvs, final ArrayList<food> lists){

        gvs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                savesf(sf1,"NAME",lists.get(position).getNme());
                savesf(sf2,"PRICE",lists.get(position).getPrice());
                savesf(sf3,"URL",lists.get(position).getUrl());
                savesf(key,"KEY",lists.get(position).getKey());
                savesf(nde,"NODE",lists.get(position).getNode());

                try {


                    Intent i=new Intent(MainActivity.this,Class.forName("com.abijayana.user.hutkrla.singlefood"));
                    startActivity(i);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });




    }
    void savesf(SharedPreferences sff,String kkey,String strg){
        SharedPreferences.Editor ed=sff.edit();
        ed.putString(kkey,strg);
        ed.commit();


    }


}
