package com.example.myapplication;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentHomeBinding;

import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "status";
    private static final String ARG_PARAM2 = "email";


    // TODO: Rename and change types of parameters
    private String status;
    private String email;

    private musicAdapter musicAdapter;
    private FragmentHomeBinding binding;
    private int sound1,sound2,sound3,sound4,sound5,sound6,sound7,sound8,sound9,sound10,sound11,sound12;

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String status, String emailUser) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, status);
        args.putString(ARG_PARAM2, emailUser);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            status = getArguments().getString(ARG_PARAM1);
            email = getArguments().getString(ARG_PARAM2);
        }
    }

    ArrayList<music> listmusic = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
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
        listmusic.add(new music("DJ",sound1,sound2,sound3,sound4,sound5,sound6,sound7,sound8,sound9,sound10,sound11,sound12));
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvstatus.setText(status);

        if(status=="Home")
        {
            setUpRecyclerView();
        }
        else if(status == "Favorite")
        {
            System.out.println(status);
            setUpRecyclerViewFav();
        }


    }

    void setUpRecyclerView(){
        binding.rvmusicHome.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvmusicHome.setHasFixedSize(true);
        musicAdapter=new musicAdapter(listmusic, email);
        binding.rvmusicHome.setAdapter(musicAdapter);
    }

    void setUpRecyclerViewFav()
    {
        binding.rvmusicHome.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvmusicHome.setHasFixedSize(true);

        System.out.println(email);
        ArrayList<music> musfav = new ArrayList<>();
        new GetFavourite(email, getContext(), new GetFavourite.GetFavouriteCallBack() {
            @Override
            public void preExecute() {

            }

            @Override
            public void postExecute(List<Favourite> listFav) {
                for(Favourite data:listFav)
                {
                    for(music mus:listmusic)
                    {
                        if(data.getJudul().equals(mus.getJudul()))
                        {
                            if(musfav.add(mus))
                            {
                                System.out.println("masuk");
                            }


                        }
                    }
                }

                System.out.println(musfav.size() + "s");
                musicAdapter=new musicAdapter(musfav, email);
                binding.rvmusicHome.setAdapter(musicAdapter);
            }
        }).execute();


    }
}

class GetFavourite{
    private final WeakReference<Context> weakContext;
    private final WeakReference<GetFavouriteCallBack> weakCallback;
    private String user;

    public GetFavourite(String user, Context context, GetFavouriteCallBack callback) {
        this.weakContext = new WeakReference<>(context);
        this.weakCallback = new WeakReference<>(callback);
        this.user = user;
    }
    Boolean found = false;
    void execute(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        weakCallback.get().preExecute();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Context context = weakContext.get();
                UserDatabase userDatabase = UserDatabase.getUserDatabase(context);
                List<Favourite> listFav;
                listFav = userDatabase.userDao().getAllFavourite(user);
                handler.post(()->{

                    weakCallback.get().postExecute(listFav);
                });

            }
        });
    }


    interface GetFavouriteCallBack{
        void preExecute();
        void postExecute(List<Favourite> listFav);
    }
}