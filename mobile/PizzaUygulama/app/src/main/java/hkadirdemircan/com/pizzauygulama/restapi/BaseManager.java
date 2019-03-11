package hkadirdemircan.com.pizzauygulama.restapi;

public class BaseManager {

    protected RestApi getRestApiClient(){
        RestApiClient restApiClient = new RestApiClient(BaseUrl.url);
        return restApiClient.getRestApi();
    }
}
