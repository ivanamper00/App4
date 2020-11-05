package com.boltu.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.boltu.myapplication.R;
import com.boltu.myapplication.model.players.PlayerDetailsModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder> {
    Context context;
    List<PlayerDetailsModel> playerList;
    PlayerDetailsModel player;
    public class PlayersViewHolder extends RecyclerView.ViewHolder {
        TextView playerName;
        TextView playerBatting;
        TextView playerBowling;
        TextView playerType;
        ImageView playerImage;
        public PlayersViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.player_name);
            playerBatting = itemView.findViewById(R.id.player_batting);
            playerBowling = itemView.findViewById(R.id.player_bowling);
            playerType = itemView.findViewById(R.id.player_type);
            playerImage = itemView.findViewById(R.id.player_image);
        }
    }

    public PlayersAdapter(Context context, List<PlayerDetailsModel> playerList){
        this.context = context;
        this.playerList = playerList;
    }

    @NonNull
    @Override
    public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_players,parent,false);
        return new PlayersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlayersViewHolder holder, int position) {
        player = playerList.get(position);

        holder.playerName.setText(player.getFullName());
        holder.playerBatting.setText("Batting: "+player.getBattingStyle());
        holder.playerBowling.setText("Bowling: "+player.getBowlingStyle());
        holder.playerType.setText("Player Type: "+player.getPlayerType());
        if(player.getImageURL() != null){
            Picasso.get().load(player.getImageURL()).into(holder.playerImage, new Callback.EmptyCallback() {
                @Override public void onSuccess() {
                }

                @Override public void onError(Exception e) {
                    holder.playerImage.setImageResource(R.drawable.sample);
                }
            });
        }else{
            holder.playerImage.setImageResource(R.drawable.sample);
        }

    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }


}
