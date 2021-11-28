package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SoundPool sp;
    Intent intent;
    private int sound1,sound2,sound3,sound4,sound5,sound6,sound7,sound8,sound9,sound10,sound11,sound12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
    public void play00(View v){
        sp.play(sound1,1.0f,1.0f,0,0,10f);
    }
    public void play01(View v){
        sp.play(sound2,1.0f,1.0f,0,0,10f);
    }
    public void play02(View v){
        sp.play(sound3,1.0f,1.0f,0,0,10f);
    }
    public void play10(View v){
        sp.play(sound4,1.0f,1.0f,0,0,10f);
    }
    public void play11(View v){
        sp.play(sound5,1.0f,1.0f,0,0,10f);
    }
    public void play12(View v){
        sp.play(sound6,1.0f,1.0f,0,0,10f);
    }
    public void play20(View v){
        sp.play(sound7,1.0f,1.0f,0,0,10f);
    }
    public void play21(View v){
        sp.play(sound8,1.0f,1.0f,0,0,10f);
    }
    public void play22(View v){
        sp.play(sound9,1.0f,1.0f,0,0,10f);
    }
    public void play30(View v){
        sp.play(sound10,1.0f,1.0f,0,0,10f);
    }
    public void play31(View v){
        sp.play(sound11,1.0f,1.0f,0,0,10f);
    }
    public void play32(View v){
        sp.play(sound12,1.0f,1.0f,0,0,10f);
    }
}