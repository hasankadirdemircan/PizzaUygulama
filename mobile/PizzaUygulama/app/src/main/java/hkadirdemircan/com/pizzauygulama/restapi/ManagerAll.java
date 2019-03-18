package hkadirdemircan.com.pizzauygulama.restapi;

import java.util.List;

import hkadirdemircan.com.pizzauygulama.model.PideCesitleri;
import hkadirdemircan.com.pizzauygulama.model.PideKaydet;
import hkadirdemircan.com.pizzauygulama.model.User;
import retrofit2.Call;

public class ManagerAll  extends BaseManager {
    private static ManagerAll ourInstance  = new ManagerAll();

    public static synchronized ManagerAll getInstance(){
        return  ourInstance;
    }
    //model class i veriyoruz. burada istegi atmak icin restapi'yi tetikliyoruz. MainActivity burayi tetiklemeli.
    //bilgi getir http get istegini ManagerAll classindan kontrol ediyoruz.
    public Call<User> createUser(User user){
        Call<User> call = getRestApiClient().createUser(user);
        return call;
    }

    //kullaniciyi silmek icin.
    public Call<User> deleteUser(Long id){
        Call<User> call = getRestApiClient().deleteUser(id);
        return  call;
    }

    public Call<User> getUser(String email){
        Call<User> call = getRestApiClient().getUser(email);
        return call;
    }

    public Call <List<PideCesitleri>> getPideCesitleri(){
        Call <List<PideCesitleri>> call = getRestApiClient().getPideCesitleri();
        return call;
    }

    public Call <PideKaydet> savePideKaydet(PideKaydet pideKaydet){
        Call <PideKaydet> call = getRestApiClient().savePideKaydet(pideKaydet);
        return call;
    }
}
