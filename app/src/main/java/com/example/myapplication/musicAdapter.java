package com.example.myapplication;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.LayoutMusicBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class musicAdapter extends RecyclerView.Adapter<musicAdapter.ViewHolder> {

    Intent intent;
    private ArrayList<music> listmusic;
    private OnItemClickCallback onItemClickCallback;
    Boolean cekfav [] = new Boolean[100];

    public musicAdapter(ArrayList<music> listmusic) {
        this.listmusic = listmusic;
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
