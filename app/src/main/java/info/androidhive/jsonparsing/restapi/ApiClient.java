package info.androidhive.jsonparsing.restapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static String URL_BASE = "http://boolfox.com/rest/index.php/";
   public static final String BASE_URL = "https://api.androidhive.info/";
    private static Retrofit retrofit = null;

    private static Retrofit retrofitArrayParse = null;

    ///http://boolfox.com/rest/index.php/htc/checkout_cart
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static Retrofit getClientArrayParse() {
        if (retrofitArrayParse==null) {
            retrofitArrayParse = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitArrayParse;
    }


}