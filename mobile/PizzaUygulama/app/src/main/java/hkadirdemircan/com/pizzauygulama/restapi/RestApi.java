package hkadirdemircan.com.pizzauygulama.restapi;

import hkadirdemircan.com.pizzauygulama.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestApi {

    //istek atacagimiz pathi ve annotation u belirtiyoruz.
    //istekler buradan atilir.
    //Call ile bize donecek response u karsilariz.
    @POST("/api/users")
    Call<User> createUser(@Body User user);
}
