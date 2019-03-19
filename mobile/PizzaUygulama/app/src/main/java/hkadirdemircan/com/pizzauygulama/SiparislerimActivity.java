package hkadirdemircan.com.pizzauygulama;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.List;

import hkadirdemircan.com.pizzauygulama.adapters.AdapterSiparislerim;
import hkadirdemircan.com.pizzauygulama.model.PideKaydet;
import hkadirdemircan.com.pizzauygulama.restapi.ManagerAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiparislerimActivity extends AppCompatActivity{

    private SlidrInterface slidr; // kaydirinca geri gelmek icin.

    List<PideKaydet> listPideKaydet;
    ListView listView;//servisden donen bilgileri listView'e basmak icin.
    Bundle bundle;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siparislerim);
        listView = (ListView) findViewById(R.id.list_view_siparislerim); //activity_siparislerim.xml ' de tanimli.
        bundle = getIntent().getExtras();
        email = bundle.getString("email");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ıntent = new Intent(SiparislerimActivity.this, SiparislerimDetayActivity.class);
                ıntent.putExtra("id", listPideKaydet.get(position).getId());
                startActivity(ıntent);
            }
        });
        request();
      slidr = Slidr.attach(this);
    }

    public void lockSlide(View v){
            slidr.lock();
    }

    public void unlockSlide(View v){
        slidr.unlock();
    }

    public void request(){
        //intent ile mainActivity'den gonderdigimiz email bilgisini aliyoruz.

        Call<List<PideKaydet>> siparisler = ManagerAll.getInstance().getPideSiparislerim(email);
        siparisler.enqueue(new Callback<List<PideKaydet>>() {
            @Override
            public void onResponse(Call<List<PideKaydet>> call, Response<List<PideKaydet>> response) {
                if(response.isSuccessful()){
                    listPideKaydet = response.body();  //gelen sonucun body'sini list'e attik.
                    //donen bilgileri adapter'a set ediyoruz.
                    AdapterSiparislerim adapterSiparislerim = new AdapterSiparislerim(listPideKaydet,getApplicationContext()); //adapter'e set edilecek bilgiler attik.
                    listView.setAdapter(adapterSiparislerim);
                }
            }

            @Override
            public void onFailure(Call<List<PideKaydet>> call, Throwable t) {

            }
        });
    }
}
