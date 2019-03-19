package hkadirdemircan.com.pizzauygulama;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import hkadirdemircan.com.pizzauygulama.model.PideKaydet;
import hkadirdemircan.com.pizzauygulama.restapi.ManagerAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SiparislerimDetayActivity extends AppCompatActivity {

    private TextView pideCesitTextView, pideAdetTextview, pideTutarTextView;
    Bundle bundle;
    String id;
    int idInt;
    PideKaydet pideKaydet;
    private SlidrInterface slidr; // kaydirinca geri gelmek icin.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siparislerim_detay);

        bundle = getIntent().getExtras();
        id =String.valueOf(bundle.getLong("id"));
        idInt = Integer.parseInt(id);
        idInt++;
        tanimla();

        ilanDetayGetir();
        slidr = Slidr.attach(this);
    }

    public void lockSlide(View v){
        slidr.lock();
    }

    public void unlockSlide(View v){
        slidr.unlock();
    }

    public void tanimla(){
        pideCesitTextView = (TextView)findViewById(R.id.pideCesitTextView);
        pideAdetTextview = (TextView)findViewById(R.id.pideAdetTextview);
        pideTutarTextView = (TextView)findViewById(R.id.pideTutarTextView);
    }

    public void ilanDetayGetir(){
        final Call<PideKaydet> request = ManagerAll.getInstance().getPideSiparisi(idInt);
        request.enqueue(new Callback<PideKaydet>() {
            @Override
            public void onResponse(Call<PideKaydet> call, Response<PideKaydet> response) {
                if (response.isSuccessful()){
                    pideKaydet = response.body();
                    pideCesitTextView.setText(pideKaydet.getPideCesit());
                    pideAdetTextview.setText(pideKaydet.getAdet());
                    pideTutarTextView.setText(pideKaydet.getTutar());
                }
            }

            @Override
            public void onFailure(Call<PideKaydet> call, Throwable t) {

            }
        });
    }
}
