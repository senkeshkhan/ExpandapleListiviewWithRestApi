package info.androidhive.jsonparsing.restapi;


import android.support.annotation.RawRes;

import info.androidhive.jsonparsing.model.Example;
import info.androidhive.jsonparsing.model.JsonExample;
import info.androidhive.jsonparsing.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("contacts/")
    Call<Example> getTopRatedMovies();

   /* @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })*/
    //http://boolfox.com/rest/index.php/htc/checkout_cart

    @POST("htc/checkout_cart")
    Call<User> getUser(@Body JsonExample body);



}