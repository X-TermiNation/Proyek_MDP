package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerSong extends AppCompatActivity {

    ActivityPlayerSongBinding bind;
    String instrument;
    ArrayList<HashMap<String, String>> listMap = new ArrayList<>();
    ArrayList<String> listEmail = new ArrayList<>();
    ArrayList<Songs> listUserSong = new ArrayList<>();
    private int sound1,sound2,sound3,sound4,sound5,sound6,sound7,sound8,sound9,sound10,sound11,sound12;
    music curInstrument;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_song);
        bind = ActivityPlayerSongBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        instrument = getIntent().getStringExtra("instrument");

        if(instrument.equals("piano"))
        {
            sound1 = R.raw.key01;
            sound2 = R.raw.key02;
            sound3 = R.raw.key03;
            sound4 = R.raw.key04;
            sound5 = R.raw.key05;
            sound6 = R.raw.key06;
            sound7 = R.raw.key07;
            sound8 = R.raw.key08;
            sound9 = R.raw.key09;
            sound10 = R.raw.key10;
            sound11 = R.raw.key11;
            sound12 = R.raw.key12;
            curInstrument = new music("piano",sound1,sound2,sound3,sound4,sound5,sound6,sound7,sound8,sound9,sound10,sound11,sound12);
        }
        else if(instrument.equals("Drum"))
        {
            sound1 = R.raw.crash;
            sound2 = R.raw.destroy;
            sound3 = R.raw.gun;
            sound4 = R.raw.hihat;
            sound5 = R.raw.kick;
            sound6 = R.raw.ride;
            sound7 = R.raw.snare;
            sound8 = R.raw.tom1;
            sound9 = R.raw.tom2;
            sound10 = R.raw.tom3;
            sound11 = R.raw.key01;
            sound12 = R.raw.key02;
            curInstrument = new music("Drum",sound1,sound2,sound3,sound4,sound5,sound6,sound7,sound8,sound9,sound10,sound11,sound12);
        }
        else if(instrument.equals("DJ"))
        {
            sound1 = R.raw.sound00;
            sound2 = R.raw.sound1;
            sound3 = R.raw.sound2;
            sound4 = R.raw.sound3;
            sound5 = R.raw.sound4;
            sound6 = R.raw.sound5;
            sound7 = R.raw.sound6;
            sound8 = R.raw.sound7;
            sound9 = R.raw.sound8;
            sound10 = R.raw.sound9;
            sound11 = R.raw.sound1;
            sound12 = R.raw.sound2;
            curInstrument = new music("DJ",sound1,sound2,sound3,sound4,sound5,sound6,sound7,sound8,sound9,sound10,sound11,sound12);
        }

        bind.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadData(this);





    }

    public void loadData(Activity context)
    {
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

                                String[] emailtemp = emails.split("-");

                                for (String data:emailtemp)
                                {
                                    listEmail.add(data);
                                }
                                System.out.println(listEmail.size());

                                String[] temp = songmap.split("-");
                                for(String data:temp)
                                {
                                    HashMap<String,String> hash = new HashMap<>();
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
                                        hash.put(songtemp[0], songtemp[1]);

                                    }
                                    listMap.add(hash);
                                }
                                for(int i = 0; i < listEmail.size(); i++)
                                {
                                    listUserSong.add(new Songs(listEmail.get(i), curInstrument, listMap.get(i)));
                                }
                                System.out.println(listUserSong.size() + "s");

                                MyListAdapter adapter=new MyListAdapter(context,R.layout.custom_listview, listUserSong);
                                bind.listSong.setAdapter(adapter);
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

class MyListAdapter extends ArrayAdapter<Songs> {

    private final Activity context;
    ArrayList<Songs> listUserSong;
    int layout;


    public MyListAdapter(Activity context,int layout, ArrayList<Songs> listUserSong) {
        super(context, layout, listUserSong);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.listUserSong = listUserSong;
        this.layout = layout;


    }


    public View getView(int position, View view, ViewGroup parent) {


        View rowView=view;
        userHolder holder;

        if(rowView == null)
        {
            LayoutInflater vi = ((Activity) context).getLayoutInflater();
            rowView = vi.inflate(layout, parent, false);
            holder = new userHolder();

            holder.emailText = rowView.findViewById(R.id.emailku);
            holder.btnplay = rowView.findViewById(R.id.playlah);
            holder.emailText.setText(listUserSong.get(position).getEmail());

            holder.btnplay.setOnClickListener(new View.OnClickListener() {
            private SoundPool sp;

            HashMap<String, String> songmap = listUserSong.get(position).getSongMap();
            music cur = listUserSong.get(position).getInst();
                @Override
                public void onClick(View v) {
                    int sound1,sound2,sound3,sound4,sound5,sound6,sound7,sound8,sound9,sound10,sound11,sound12;

                    sp = new SoundPool(2,AudioManager.STREAM_MUSIC,0);
                    sound1 = sp.load(v.getContext(),cur.getSound1(),1);
                    sound2 = sp.load(v.getContext(),cur.getSound2(),1);
                    sound3 = sp.load(v.getContext(),cur.getSound3(),1);
                    sound4 = sp.load(v.getContext(),cur.getSound4(),1);
                    sound5 = sp.load(v.getContext(),cur.getSound5(),1);
                    sound6 = sp.load(v.getContext(),cur.getSound6(),1);
                    sound7 = sp.load(v.getContext(),cur.getSound7(),1);
                    sound8 = sp.load(v.getContext(),cur.getSound8(),1);
                    sound9 = sp.load(v.getContext(),cur.getSound9(),1);
                    sound10 = sp.load(v.getContext(),cur.getSound10(),1);
                    sound11 = sp.load(v.getContext(),cur.getSound11(),1);
                    sound12 = sp.load(v.getContext(),cur.getSound12(),1);
                    System.out.println(songmap.toString());
                    new CountDownTimer(300000, 1)
                    {

                        @Override
                        public void onTick(long millisUntilFinished) {
                            if(songmap.get(millisUntilFinished + "") != null)
                            {
                                switch(songmap.get(millisUntilFinished + ""))
                                {
                                    case "00":
                                        sp.play(sound1,1.0f,1.0f,0,0,10f);
                                        break;
                                    case "01":
                                        sp.play(sound2,1.0f,1.0f,0,0,10f);
                                        break;
                                    case "02":
                                        sp.play(sound3,1.0f,1.0f,0,0,10f);
                                        break;
                                    case "10":
                                        sp.play(sound4,1.0f,1.0f,0,0,10f);
                                        break;
                                    case "11":
                                        sp.play(sound5,1.0f,1.0f,0,0,10f);
                                        break;
                                    case "12":
                                        sp.play(sound6,1.0f,1.0f,0,0,10f);
                                        break;
                                    case "20":
                                        sp.play(sound7,1.0f,1.0f,0,0,10f);
                                        break;
                                    case "21":
                                        sp.play(sound8,1.0f,1.0f,0,0,10f);
                                        break;
                                    case "22":
                                        sp.play(sound9,1.0f,1.0f,0,0,10f);
                                        break;
                                    case "30":
                                        sp.play(sound10,1.0f,1.0f,0,0,10f);
                                        break;
                                    case "31":
                                        sp.play(sound11,1.0f,1.0f,0,0,10f);
                                        break;
                                    case "32":
                                        sp.play(sound12,1.0f,1.0f,0,0,10f);
                                        break;
                                    default:
                                        break;

                                }
                            }
                        }

                        @Override
                        public void onFinish() {

                        }
                    }.start();
                }
            });
        }









        return rowView;

    };
    static class userHolder{
        TextView emailText;
        Button btnplay;
    }
}