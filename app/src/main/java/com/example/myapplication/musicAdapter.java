package com.example.myapplication;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.LayoutMusicBinding;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class musicAdapter extends RecyclerView.Adapter<musicAdapter.ViewHolder> {

    Intent intent;
    private ArrayList<music> listmusic;
    private OnItemClickCallback onItemClickCallback;
    Boolean cekfav [] = new Boolean[100];
    private String email;

    public musicAdapter(ArrayList<music> listmusic, String email) {
        this.listmusic = listmusic;
        this.email = email;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutMusicBinding binding = LayoutMusicBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        music music = listmusic.get(position);
        holder.bind(music);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onItemClickCallback.onItemClicked(music);
                }catch (Exception E){}
            }
        });
    }

    @Override
    public int getItemCount() {
        return listmusic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final LayoutMusicBinding binding;
        public ViewHolder(@NonNull LayoutMusicBinding layoutFilmBinding) {
            super(layoutFilmBinding.getRoot());
            this.binding = layoutFilmBinding;
        }
        public void bind(music music) {
            binding.tvItemJudul.setText(music.getJudul());
            binding.btncomment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(),CommentActivity.class);
                    i.putExtra("loggedEmail",email);
                }
            });
            binding.btnloadpack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(v.getContext(), MainActivity.class);
                    intent.putExtra("sound1", music.getSound1());
                    intent.putExtra("sound2", music.getSound2());
                    intent.putExtra("sound3", music.getSound3());
                    intent.putExtra("sound4", music.getSound4());
                    intent.putExtra("sound5", music.getSound5());
                    intent.putExtra("sound6", music.getSound6());
                    intent.putExtra("sound7", music.getSound7());
                    intent.putExtra("sound8", music.getSound8());
                    intent.putExtra("sound9", music.getSound9());
                    intent.putExtra("sound10", music.getSound10());
                    intent.putExtra("sound11", music.getSound11());
                    intent.putExtra("sound12", music.getSound12());
                    v.getContext().startActivity(intent);

                }
            });
            Arrays.fill(cekfav,Boolean.FALSE);
            binding.btnfavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cekfav[0]==false){
                        binding.btnfavorite.setImageResource(R.drawable.ic_baseline_favorite2_24);
                        cekfav[0]=true;
                        new AddFavourite(email, music.getJudul(), view.getContext(), new AddFavourite.AddFavouriteCallBack() {
                            @Override
                            public void preExecute() {

                            }

                            @Override
                            public void postExecute(String message) {
                                System.out.println(message);
                            }
                        }).execute();
                    }else{
                        binding.btnfavorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                        cekfav[0]=false;
                    }
                }
            });
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(music music);
    }
}

class AddFavourite{
    private final WeakReference<Context> weakContext;
    private final WeakReference<AddFavouriteCallBack> weakCallback;
    private String user;
    private String music;

    public AddFavourite(String user,String music, Context context, AddFavouriteCallBack callback) {
        this.weakContext = new WeakReference<>(context);
        this.weakCallback = new WeakReference<>(callback);
        this.user = user;
        this.music = music;
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



                userDatabase.userDao().insertFavourite(new Favourite(music, user));
                handler.post(()->{
                    String succesMessage = "User berhasil terdaftar";
                    weakCallback.get().postExecute(succesMessage);
                });

            }
        });
    }


    interface AddFavouriteCallBack{
        void preExecute();
        void postExecute(String message);
    }
}


