package com.example.myapplication;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "status";

    // TODO: Rename and change types of parameters
    private String status;
    private musicAdapter musicAdapter;
    private FragmentHomeBinding binding;
    private int sound1,sound2,sound3,sound4,sound5,sound6,sound7,sound8,sound9,sound10,sound11,sound12;

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String status) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            status = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvstatus.setText(status);
        if(status=="Home")
            setUpRecyclerView();
    }

    void setUpRecyclerView(){
        binding.rvmusicHome.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvmusicHome.setHasFixedSize(true);

        ArrayList<music> listmusic = new ArrayList<>();
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
        listmusic.add(new music("piano",sound1,sound2,sound3,sound4,sound5,sound6,sound7,sound8,sound9,sound10,sound11,sound12));

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
        listmusic.add(new music("Drum",sound1,sound2,sound3,sound4,sound5,sound6,sound7,sound8,sound9,sound10,sound11,sound12));

        musicAdapter=new musicAdapter(listmusic);
        binding.rvmusicHome.setAdapter(musicAdapter);
    }
}