package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SoundPool sp;
    Intent intent;
    boolean isRecording = false;
    private String second = "";
    Map<String, String> record = new HashMap<>();
    private int sound1,sound2,sound3,sound4,sound5,sound6,sound7,sound8,sound9,sound10,sound11,sound12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        CountDownTimer timer = new CountDownTimer(300000,1)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                second = millisUntilFinished + "";
            }

            @Override
            public void onFinish() {

            }
        };

        binding.recordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isRecording)
                {
                    isRecording = true;
                    timer.start();
                }
                else
                {
                    isRecording = false;
                    timer.cancel();
                    System.out.println(record.toString());
                }
            }
        });

        binding.uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSong(getIntent().getStringExtra("instrument"), record.toString(), getIntent().getStringExtra("email"));
            }
        });

        CountDownTimer playrec = new CountDownTimer(300000,1)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                if(record.get(millisUntilFinished + "") != null)
                {
                    switch(record.get(millisUntilFinished + ""))
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
                    System.out.println(record.get(millisUntilFinished));
                }
            }

            @Override
            public void onFinish() {

            }
        };

        binding.showOtherSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayerSong.class);
                intent.putExtra("instrument", getIntent().getStringExtra("instrument"));
                System.out.println(getIntent().getStringExtra("instrument"));
                startActivity(intent);
            }
        });

        binding.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playrec.start();
            }
        });



        intent=getIntent();
        sp = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        sound1 = sp.load(getApplicationContext(),intent.getIntExtra("sound1",0),1);
        sound2 = sp.load(getApplicationContext(),intent.getIntExtra("sound2",0),1);
        sound3 = sp.load(getApplicationContext(),intent.getIntExtra("sound3",0),1);
        sound4 = sp.load(getApplicationContext(),intent.getIntExtra("sound4",0),1);
        sound5 = sp.load(getApplicationContext(),intent.getIntExtra("sound5",0),1);
        sound6 = sp.load(getApplicationContext(),intent.getIntExtra("sound6",0),1);
        sound7 = sp.load(getApplicationContext(),intent.getIntExtra("sound7",0),1);
        sound8 = sp.load(getApplicationContext(),intent.getIntExtra("sound8",0),1);
        sound9 = sp.load(getApplicationContext(),intent.getIntExtra("sound9",0),1);
        sound10 = sp.load(getApplicationContext(),intent.getIntExtra("sound10",0),1);
        sound11 = sp.load(getApplicationContext(),intent.getIntExtra("sound11",0),1);
        sound12 = sp.load(getApplicationContext(),intent.getIntExtra("sound12",0),1);
    }


    public void addSong(String instrument, String songmap, String email){
        StringRequest sr = new StringRequest(
                Request.Method.POST,
                getResources().getString(R.string.url),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response + "ez");
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");
                            String message = jsonObject.getString("message");
                            if (code == 1){
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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
                System.out.println(songmap);

                params.put("function","addsong");
                params.put("instrument",instrument);
                params.put("songmap", songmap);
                params.put("email", email);
                return params;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(sr);
    }

    public void play00(View v){
        sp.play(sound1,1.0f,1.0f,0,0,10f);
        if(isRecording)
        {
            record.put(second, "00");
        }
    }

    public void play01(View v){
        sp.play(sound2,1.0f,1.0f,0,0,10f);
        if(isRecording)
        {
            record.put(second, "01");

        }
    }
    public void play02(View v){
        sp.play(sound3,1.0f,1.0f,0,0,10f);
        if(isRecording)
        {
            record.put(second, "02");

        }
    }
    public void play10(View v){
        sp.play(sound4,1.0f,1.0f,0,0,10f);
        if(isRecording)
        {
            record.put(second, "10");

        }
    }
    public void play11(View v){
        sp.play(sound5,1.0f,1.0f,0,0,10f);
        if(isRecording)
        {
            record.put(second, "11");

        }
    }
    public void play12(View v){
        sp.play(sound6,1.0f,1.0f,0,0,10f);
        if(isRecording)
        {
            record.put(second, "12");

        }
    }
    public void play20(View v){
        sp.play(sound7,1.0f,1.0f,0,0,10f);
        if(isRecording)
        {
            record.put(second, "20");

        }
    }
    public void play21(View v){
        sp.play(sound8,1.0f,1.0f,0,0,10f);
        if(isRecording)
        {
            record.put(second, "21");

        }
    }
    public void play22(View v){
        sp.play(sound9,1.0f,1.0f,0,0,10f);
        if(isRecording)
        {
            record.put(second, "22");

        }
    }
    public void play30(View v){
        sp.play(sound10,1.0f,1.0f,0,0,10f);
        if(isRecording)
        {
            record.put(second, "30");

        }
    }
    public void play31(View v){
        sp.play(sound11,1.0f,1.0f,0,0,10f);
        if(isRecording)
        {
            record.put(second, "31");

        }
    }
    public void play32(View v){
        sp.play(sound12,1.0f,1.0f,0,0,10f);
        if(isRecording)
        {
            record.put(second, "32");

        }
    }
}