package hkadirdemircan.com.pizzauygulama;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hkadirdemircan.com.pizzauygulama.model.PideCesitleri;
import hkadirdemircan.com.pizzauygulama.model.PideKaydet;
import hkadirdemircan.com.pizzauygulama.restapi.ManagerAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //EDIT
    SharedPreferences sharedPreferences;
    String navHeaderText;
    TextView navHeaderTextView;
    SharedPreferences.Editor editor; //cikis yapma islemi icin.
    TextView pideSecimTextView;
    Spinner spinner;
    List<PideCesitleri> pideCesitleri = new ArrayList<>();
    List<String> pideler = new ArrayList<>();
    ArrayAdapter arrayAdapter; //spinner icin.
    String secilenPide;
    Button siparisVerButton;
    EditText pideAdetEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Pide Cesitlerini iste.

        //EDIT
        sharedPreferences = getApplicationContext().getSharedPreferences("session",0);
        navHeaderText = sharedPreferences.getString("email",null);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //EDİT
        //nav_header_xml icin ayarladik.
        View headerView = navigationView.getHeaderView(0);
        navHeaderTextView = (TextView) headerView.findViewById(R.id.navTextView);
        navHeaderTextView.setText(navHeaderText);

        pideCesitleriniGetir();// pide cesitlerini sunucudan cekti.
        //Spinner icine verileri giriyoruz.

    }

    public void tanimlamalar(){

//        //pideleri goster.
        spinnerTanimla();
        spinnerAdapterOlustur();
        spinnerAdapterEkle();
        spinnerTiklamaListener();

        buttonOnclick();


    }

    public void buttonOnclick(){
        siparisVerButton = (Button) findViewById(R.id.siparisVerButton);
        pideAdetEditText = (EditText) findViewById(R.id.pideAdetEditText);
        siparisVerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != pideAdetEditText.getText().toString() && null != secilenPide){

                    PideKaydet pideKaydet = new PideKaydet();
                    pideKaydet.setPideCesit(secilenPide);
                    pideKaydet.setAdet(pideAdetEditText.getText().toString());
                    pideKaydet.setEmail(navHeaderText);
                    //todo: tutari hesapla.
                    int tutar = 0;
                    int adet = Integer.parseInt(pideKaydet.getAdet().toString());
                    if("kıymalı".equals(secilenPide)){
                        tutar = 12 * adet;
                    }else if("kaşarlı".equals(secilenPide)){
                        tutar = 10* adet;
                    }else{
                        tutar = 14 * adet;
                    }
                    pideKaydet.setTutar(Integer.toString(tutar));
                    Call <PideKaydet> requset = ManagerAll.getInstance().savePideKaydet(pideKaydet);
                    requset.enqueue(new Callback<PideKaydet>() {
                        @Override
                        public void onResponse(Call<PideKaydet> call, Response<PideKaydet> response) {
                            Toast.makeText(getApplicationContext(),"Siparişiniz Alındı.",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<PideKaydet> call, Throwable t) {

                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(),"Bilgileri Eksik Girdiniz.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void spinnerTanimla(){
        spinner = (Spinner) findViewById(R.id.spinner);
    }
    public void spinnerAdapterOlustur(){
        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, pideler);
    }
    public void spinnerAdapterEkle(){
        spinner.setAdapter(arrayAdapter);
    }

    public void spinnerTiklamaListener(){
     spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             Toast.makeText(getApplicationContext(),"seçildi", Toast.LENGTH_LONG).show();
             secilenPide = spinner.getSelectedItem().toString();
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });
    }
//spinner'a ekleniyor.
    public void pideleriEkle(){

        for (PideCesitleri item : pideCesitleri){
            pideler.add(item.getCesit());
        }

    }
    //pide cesitlerinin web servisten istenildigi yer.
    public List<PideCesitleri> pideCesitleriniGetir(){
        Call <List<PideCesitleri>> request = ManagerAll.getInstance().getPideCesitleri();
        request.enqueue(new Callback<List<PideCesitleri>>() {
            @Override
            public void onResponse(Call<List<PideCesitleri>> call, Response<List<PideCesitleri>> response) {
                pideCesitleri = response.body();
                pideleriEkle();
                tanimlamalar();
            }

            @Override
            public void onFailure(Call<List<PideCesitleri>> call, Throwable t) {

            }
        });
        return pideCesitleri;
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
        getMenuInflater().inflate(R.menu.main, menu);
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
            // Handle the camera action
        } else if (id == R.id.siparislerim) {
            //todo: siparislerim icin baska activity'e gonder.
            Intent ıntent = new Intent(MainActivity.this, SiparislerimActivity.class);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.cikisYap) {

            editor = sharedPreferences.edit();
            editor.clear(); // verileri shared'dan siliyoruz.
            editor.commit();//cikis yapma islemi tamamlandi.
            Intent ıntent = new Intent(MainActivity.this, LoginActivity.class);//login ekranina donmesi icin.
            startActivity(ıntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
