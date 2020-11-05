package com.boltu.myapplication.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boltu.myapplication.R;
import com.boltu.myapplication.controller.activity.TeamsActivity;
import com.boltu.myapplication.database.CricketApi;
import com.boltu.myapplication.model.MatchDetailsModel;
import com.boltu.myapplication.model.players.PlayerDetailsModel;
import com.boltu.myapplication.model.players.PlayersModel;
import com.boltu.myapplication.model.teams.TeamsListModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder> {
    Context context;
    List<TeamsListModel> teamsListModelList;
    TeamsListModel teamsListModel;
    Dialog dialog;
    public class TeamsViewHolder extends RecyclerView.ViewHolder {
        TextView teamName;
        TextView teamId;
        ImageView teamLogo;
        public TeamsViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.team_name);
            teamId = itemView.findViewById(R.id.team_id);
            teamLogo = itemView.findViewById(R.id.team_logo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = new Dialog(context);
                    dialog.setContentView(R.layout.team_player_dialog);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                    getData(String.valueOf(teamId.getText()));
//                    Toast.makeText(context, teamId.getText(),Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
    public TeamsAdapter(Context context, List<TeamsListModel> teamsListModelList){
        this.context = context;
        this.teamsListModelList = teamsListModelList;
    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teams_list,parent,false);
        return new TeamsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder holder, int position) {
        teamsListModel = teamsListModelList.get(position);
        holder.teamName.setText(teamsListModel.getName() + "\n(" + teamsListModel.getShortName() +")");
        holder.teamId.setText(teamsListModel.getId().toString());
        Picasso.get().load(teamsListModel.getLogoUrl()).into(holder.teamLogo);

    }

    @Override
    public int getItemCount() {
        return teamsListModelList.size();
    }

    public void getData(String teamId){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CricketApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CricketApi api = retrofit.create(CricketApi.class);

        Call<PlayersModel> call = api.getTeamPlayers(teamId);

        call.enqueue(new Callback<PlayersModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<PlayersModel> call, retrofit2.Response<PlayersModel> response) {

                RecyclerView recyclerView = dialog.findViewById(R.id.teams_players_dialog);
                LinearLayoutManager llm = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(llm);

                List<PlayerDetailsModel> teamPlayerList = response.body().getTeamPlayers().getPlayers();
                PlayersAdapter adapter = new PlayersAdapter(context,teamPlayerList);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<PlayersModel> call, Throwable t) {
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + t.getMessage());
            }
        });
    }
}
