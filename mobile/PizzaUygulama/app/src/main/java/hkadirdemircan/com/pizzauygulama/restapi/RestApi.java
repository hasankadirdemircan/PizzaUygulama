package hkadirdemircan.com.pizzauygulama.restapi;

import java.util.List;

import hkadirdemircan.com.pizzauygulama.model.PideCesitleri;
import hkadirdemircan.com.pizzauygulama.model.PideKaydet;
import hkadirdemircan.com.pizzauygulama.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestApi {

    //istek atacagimiz pathi ve annotation u belirtiyoruz.
    //istekler buradan atilir.
    //Call ile bize donecek response u karsilariz.
    @POST("/api/users")
    Call<User> createUser(@Body User user);
    //Call<User> createUser(@Body User user);

    //User'i silmek icin.
    @DELETE("/api/users/{id}")
    Call<User> deleteUser(@Path("id") Long id);

    @GET("/api/users/{email}")
    Call<User> getUser(@Path("email") String email);

    @GET("/pide/all")
    Call <List<PideCesitleri>> getPideCesitleri();

    @POST("/pide/save")
    Call <PideKaydet> savePideKaydet(@Body PideKaydet pideKaydet);

    @GET("/pide/all/{email}")
    Call <List<PideKaydet>> getPideSiparislerim(@Path("email") String email);

    @GET("/pide/first/{id}")
    Call <PideKaydet> getPideSiparisi(@Path("id") int id);
}
