package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.databinding.ActivityPlayerSongBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerSong extends AppCompatActivity {

    ActivityPlayerSongBinding bind;
    String instrument;
    ArrayList<HashMap<Integer, String>> listMap = new ArrayList<>();
    ArrayList<String> listEmail = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_song);
        bind = ActivityPlayerSongBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        instrument = getIntent().getStringExtra("instrument");

        bind.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        StringRequest sr = new StringRequest(
                Request.Method.POST,
                getResources().getString(R.string.url),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");
                            String songmap = jsonObject.getString("songmap");
                            String emails = jsonObject.getString("email");
                            if (code == 1){
                                System.out.println(songmap);
                                System.out.println("done");
                                songmap = songmap.replace("},{", "-");
                                songmap = songmap.replace("[", "");
                                songmap = songmap.replace("]", "");
                                songmap = songmap.replace("\"", "");
                                songmap = songmap.replace(":", "");
                                songmap = songmap.substring(1);
                                songmap = songmap.substring(0, songmap.length() - 1);


                                emails = emails.replace("},{", "-");
                                emails = emails.replace("[", "");
                                emails = emails.replace("]", "");
                                emails = emails.replace("EMAIL\":", "");
                                emails = emails.replace("\"", "");
                                emails = emails.substring(1);
                                emails = emails.substring(0, emails.length() - 1);
                                System.out.println(emails);

                                String[] emailtemp = emails.split("-");

                                for (String data:emailtemp)
                                {
                                    listEmail.add(data);
                                }
                                System.out.println(listEmail.size());

                                String[] temp = songmap.split("-");
                                for(String data:temp)
                                {
                                    HashMap<Integer,String> hash = new HashMap<>();
                                    data = data.replace("SONGMAP", "");
                                    data = data.replace("\"\":\"", "");
                                    data = data.replace("\"", "");
                                    data = data.replace("{", "");
                                    data = data.replace("}", "");
                                    data = data.replace(" ", "");
                                    String[] map = data.split(",");
                                    for(String song:map)
                                    {
                                        String[] songtemp = song.split("=");
                                        hash.put(Integer.parseInt(songtemp[0]), songtemp[1]);

                                    }
                                    listMap.add(hash);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();

                params.put("function","getsong");
                params.put("instrument",instrument);
                return params;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(sr);

    }
}