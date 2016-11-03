package com.edddge.maps.mapmyjourney;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ThirdScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter mAdapter;
    private int bundleVal = 0;
    private BottomBar mBottomBar;

    List<DetailsModel> d = new ArrayList<>();
    private final String LOG_TAG = "infovision_log";
    DetailsModel fishData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_distance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //XXX
       /* ActionBar action=getActionBar();
        action.setDisplayShowCustomEnabled(true);
        action.setHomeButtonEnabled(true);*/

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

                int[] r = {R.drawable.car,R.drawable.bus,R.drawable.train,R.drawable.walk};
        String strJson1 = " {\"user\" : [" +
                "{\"transport\" : \"Car\", " +
                "\"distance\" : \"40 km\"," +
                " \"time\" : \"1 hr\"," +
                " \"route\" : \"blocked\"}," +
                "{\"transport\" : \"Bus\", " +
                " \"distance\" : \"40km\"," +
                " \"time\" : \"1/2 hr\"," +
                " \"route\" : \"clear\"}," +
                "{\"transport\" : \"Train\"," +
                "                \"distance\" : \"40km\"," +
                "                \"time\" : \"15 min\"," +
                "                \"route\" : \"clear\"},"+
                "{\"transport\" : \"Walk\"," +
            "                \"distance\" : \"40km\"," +
                    "                \"time\" : \"3 min\"," +
                    "                \"route\" : \"clear\"} ]}";
try{

            JSONObject jsonRootObject1 = new JSONObject(strJson1);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject1.optJSONArray("user");

            //Iterate the jsonArray and print the info of JSONObjects
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject json_data = jsonArray.getJSONObject(i);
                fishData = new DetailsModel();
                fishData.setTransport(json_data.getString("transport"));
                fishData.setDistance(json_data.getString("distance"));
                fishData.setTime(json_data.getString("time"));
                fishData.setRoute(json_data.getString("route"));

                d.add(fishData);
            }
            recyclerView = (RecyclerView) findViewById(R.id.listview1);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            mAdapter = new Adapter(getApplicationContext(), d,r);
            recyclerView.setAdapter(mAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mAdapter.setOnItemClickListener(new Adapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(getApplicationContext(),
                        FouthScreen.class);

                startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position, View v) {
            }
        });
        mBottomBar = BottomBar.attach(this, savedInstanceState);

        mBottomBar.setItems(R.menu.botto);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                //switch between tabs
                switch (menuItemId) {
                    case R.id.recent_item:
                        break;
                    case R.id.location_item:
                        break;
                    case R.id.favorite_item:
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }

        });

    }

}
