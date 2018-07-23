package info.androidhive.jsonparsing;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import info.androidhive.jsonparsing.model.Checkout;
import info.androidhive.jsonparsing.model.Contact;
import info.androidhive.jsonparsing.model.Example;
import info.androidhive.jsonparsing.model.JsonExample;
import info.androidhive.jsonparsing.model.Phone;
import info.androidhive.jsonparsing.model.User;
import info.androidhive.jsonparsing.restapi.ApiClient;
import info.androidhive.jsonparsing.restapi.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;
    ExpandableListView expandablelistView;
    // URL to get contacts JSON
    private static String url = "https://api.androidhive.info/contacts/";

    ArrayList<HashMap<String, String>> contactList;

    ArrayList<Checkout> filter_items = new ArrayList<>();

    ArrayList<Checkout> checkoutList = new ArrayList<>();

    List<Contact> parentList = new ArrayList<>();
    HashMap<String, List<Phone>> listDataChild = new HashMap<String, List<Phone>>();

    String values;
    JsonExample jsonExample = new JsonExample();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prepareMovieData();

        contactList = new ArrayList<>();
        expandablelistView = (ExpandableListView) findViewById(R.id.expandableListView1);
        lv = (ListView) findViewById(R.id.list);

      /// new GetContacts().execute();
        retrofitServiceCall();


        jsonExample.setEmailid("phpdevelopermuhammed@gmail.com");

        /*{
            "checkout": [
            {
                "product_id": "804",
                    "product_price": "23600",
                    "product_quantity": 1,
                    "product_name": "DR-266"
            },
            {
                "product_id": "802",
                    "product_price": "13983",
                    "product_quantity": 1,
                    "product_name": "DR-256"
            }
            ],
            "emailid": "phpdevelopermuhammed@gmail.com"
        }*/




        for(int i =0; i<filter_items.size();i++){

            Checkout hh = new Checkout();

            hh.setProductId(filter_items.get(i).getProductId());

            hh.setProductName(filter_items.get(i).getProductName());

            hh.setProductPrice(filter_items.get(i).getProductPrice());

            hh.setProductQuantity(filter_items.get(i).getProductQuantity());

            checkoutList.add(hh);


        }
       // jsonExample.setCheckout(hh);
        jsonExample.setCheckout(checkoutList);


       // System.out.println("TTTTTTTTTTTTTTTT"+new Gson().toJson(jsonExample));


         values = new Gson().toJson(jsonExample);
        System.out.println("valuessssssssssssss"+values);

        testJsonformatsentBackend();
        JSONObject object=new JSONObject();
/*
        try {

        object.put("typeName","");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray array=new JSONArray();

        for(int i=0;i<filter_items.size();i++){
            JSONObject obj=new JSONObject();
            try {
                obj.put("filterId",filter_items.get(i));
                obj.put("typeName","CAT_ID");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            array.put(obj);
        }*/


// Encodes the JSONArray as a compact JSON string
        //String jsonText = jsonArray.toString();


        //System.out.println("jsonTextjsonText"+array);

     /*   JSONObject json = new JSONObject();
        try {
            json.put("store","amz");
            json.put("category","fashion");
            json.put("type","coupons");

            System.out.println("valuessssssssss"+json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/


    }



    public void retrofitServiceCall(){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Example> call = apiService.getTopRatedMovies();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                int statusCode = response.code();

                Log.e("TAG", "response 33: "+new Gson().toJson(response.body()) );



                List<Contact> movies = response.body().getContacts();



                for(int i =0; i<movies.size();i++){

                    String name = movies.get(i).getName();


                    Contact contact = new Contact();

                    contact.setName(name);

                    parentList.add(contact);



                    List<Phone> childList = new ArrayList<>();

                    String mobile = movies.get(i).getPhone().getMobile();




                    Phone phone = new Phone();

                    phone.setHome(mobile);

                    childList.add(phone);




                    listDataChild.put(parentList.get(i).getName(),childList);

                    System.out.println("NAMEEEEEEEEEEEEEEEE"+name+","+mobile);



                }



                ExpandCustomAdapter  listAdapter = new ExpandCustomAdapter(MainActivity.this, parentList, listDataChild);

                // setting list adapter
                expandablelistView.setAdapter(listAdapter);


                //recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }



    public  void  testJsonformatsentBackend(){

        ApiInterface apiService =
                ApiClient.getClientArrayParse().create(ApiInterface.class);

        System.out.println("yyyyyyyyyyyyyyyyy"+values);
        Call<User> call = apiService.getUser(jsonExample);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {


                Log.e("TAG", "responseTestsssss: "+new Gson().toJson(response.body()) );





                //recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Log error here since request failed
                Log.e("TAGggggggg","EEEEEEEEEEEEEEEe"+ t.toString());
            }
        });

    }


    private void prepareMovieData() {
        Checkout checkout = new Checkout("804", "23600", 1,"DR-266");
        filter_items.add(checkout);

        checkout = new Checkout("805", "13983",1,"DR-256");
        filter_items.add(checkout);



    }


    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("contacts");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");
                        String email = c.getString("email");
                        String address = c.getString("address");
                        String gender = c.getString("gender");

                        // Phone node is JSON Object
                        JSONObject phone = c.getJSONObject("phone");
                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");

                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("id", id);
                        contact.put("name", name);
                        contact.put("email", email);
                        contact.put("mobile", mobile);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, contactList,
                    R.layout.list_item, new String[]{"name", "email",
                    "mobile"}, new int[]{R.id.name,
                    R.id.email, R.id.mobile});

            lv.setAdapter(adapter);
        }

    }
}
