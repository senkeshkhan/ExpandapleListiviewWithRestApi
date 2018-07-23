package info.androidhive.jsonparsing;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExpandListviewActivity extends Activity {
	
	List<String> ChildList;

	ExpandableListView expandablelistView;







	ExpandListAdapter listAdapter;

	List<String> listDataHeader= new ArrayList<String>();
	HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);

		expandablelistView = (ExpandableListView) findViewById(R.id.expandableListView1);






//                Hashmap for child data key = header title and value = Arraylist (child data)
		//listDataChild = new HashMap<String, List<String>>();


		jsonParser();




        expandablelistView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub

					final String selected = (String) listAdapter.getChild(
							groupPosition, childPosition);
					Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
							.show();



				return true;
			}
        });
    }
 





    public void jsonParser(){




		try {

/*

			JSONObject object = new JSONObject(loadJSONFromAsset());


			JSONArray jsonArray = object.getJSONArray("contacts");

			for(int i = 0;i<jsonArray.length();i++){

				JSONObject jsonObject = jsonArray.getJSONObject(i);



				ChildList = new ArrayList<String>();

				ChildList.add(jsonObject.getString("name"));


				JSONObject jsonObject1 = jsonObject.getJSONObject("phone");



				listDataHeader.add(jsonObject1.getString("mobile"));



				listDataChild.put(listDataHeader.get(i), ChildList);


			}

			listAdapter = new ExpandListAdapter(ExpandListviewActivity.this, listDataHeader, listDataChild);

			// setting list adapter
			expandablelistView.setAdapter(listAdapter);
*/


			JSONArray jsonArray = new JSONArray(loadJSONFromAsset());


//                Hashmap for child data key = header title and value = Arraylist (child data)


			for(int i = 0; i<jsonArray.length();i++){


				JSONObject   jsonObject = jsonArray.getJSONObject(i);


				listDataHeader.add(jsonObject.getString("category"));
				//ParentList.add(jsonObject.getString("category"));

				System.out.println("categorycategorycategory"+jsonObject.getString("category"));


				JSONArray jsonArray1 =jsonObject.getJSONArray("data");

				ChildList = new ArrayList<String>();
				for(int j =0; j<jsonArray1.length();j++){

					JSONObject jsonObject1 =jsonArray1.getJSONObject(j);

					ChildList.add(jsonObject1.getString("title"));



					//ChildList.add(jsonObject1.getString("title"));
				}
				listDataChild.put(listDataHeader.get(i), ChildList);
               // System.out.println("jsonObjectjsonObject"+jsonObject.getString("category"));


			}
				listAdapter = new ExpandListAdapter(ExpandListviewActivity.this, listDataHeader, listDataChild);

			// setting list adapter
			expandablelistView.setAdapter(listAdapter);






		} catch (JSONException e) {
			e.printStackTrace();
		}
	}




	public String loadJSONFromAsset() {
		String json = null;
		try {
			InputStream is = getApplicationContext().getAssets().open("news.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;
	}

}