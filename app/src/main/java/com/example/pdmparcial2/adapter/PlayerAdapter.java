package com.example.pdmparcial2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pdmparcial2.R;
import com.example.pdmparcial2.model.New;
import com.example.pdmparcial2.model.Player;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>{

    private List<Player> players;
    private Context context;

    public PlayerAdapter(Context context){
        this.context = context;
        players = new ArrayList<>();
    }

    public void setPlayers(List<Player> players){
        this.players = players;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_player, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = players.get(position);
        holder.playerNameTextView.setText(player.getName());

        try{
            if (!player.getAvatar().isEmpty()) {
                Picasso.get().load(player.getAvatar()).error(R.drawable.ic_image).into(holder.playerImageView);
            }else{
                Picasso.get().load(R.drawable.ic_image).error(R.drawable.ic_image).into(holder.playerImageView);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder{

        private ImageView playerImageView;
        private TextView playerNameTextView;

        public PlayerViewHolder(View view){
            super(view);
            playerImageView = view.findViewById(R.id.playerImageView);
            playerNameTextView = view.findViewById(R.id.playerNameTextView);
        }
    }
}