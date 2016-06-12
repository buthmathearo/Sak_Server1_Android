package com.buthmathearo.sak_server1.ui;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.buthmathearo.sak_server1.R;
import com.buthmathearo.sak_server1.adapter.UserAdapter;
import com.buthmathearo.sak_server1.databinding.ActivityMainBinding;
import com.buthmathearo.sak_server1.intent.UserIntent;
import com.buthmathearo.sak_server1.listener.UserListener;
import com.buthmathearo.sak_server1.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserListener {

    private List<User> mList;
    private ActivityMainBinding mBinding;
    private LinearLayoutManager mLinearLayout;
    private GridLayoutManager mGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLinearLayout = new LinearLayoutManager(this);
        mGridLayout = new GridLayoutManager(this, 3);
        mList = new ArrayList<User>();
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://api.github.com/users";

        final JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i=0; i<response.length(); i++){
                        JSONObject jObj = response.getJSONObject(i);
                        mList.add(new User(
                                jObj.getString("login"),
                                jObj.getString("avatar_url"),
                                jObj.getString("type")
                        ));
                    }
                }catch (JSONException ex){
                    ex.printStackTrace();
                }

                mBinding.progressBar.setVisibility(View.GONE);
                UserAdapter adapter = new UserAdapter(mList, MainActivity.this);
                mBinding.recyclerView.setLayoutManager(mLinearLayout);
                mBinding.recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        queue.add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ( item.getItemId() == R.id.menu_action_arrange ) {

            if ( mBinding.recyclerView.getLayoutManager() == mLinearLayout) {
                mBinding.recyclerView.setLayoutManager(mGridLayout);
            } else {
                mBinding.recyclerView.setLayoutManager(mLinearLayout);
            }
         }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(User user) {
        startActivity(new UserIntent(this, user));
    }
}
