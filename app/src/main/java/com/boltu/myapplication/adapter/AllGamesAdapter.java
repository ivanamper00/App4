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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.boltu.myapplication.R;
import com.boltu.myapplication.controller.GlobalController;
import com.boltu.myapplication.database.CricketApi;
import com.boltu.myapplication.model.MatchDetailsModel;
import com.boltu.myapplication.model.games.GamesModel;
import com.boltu.myapplication.model.games.MatchListModel;
import com.boltu.myapplication.model.players.PlayersModel;
import com.boltu.myapplication.model.teams.TeamsListModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllGamesAdapter extends RecyclerView.Adapter<AllGamesAdapter.AllGamesViewHolder> {
    Context context;
    List<MatchListModel> matchList;
    MatchListModel match;
    Dialog dialog;
    GlobalController controller;
    public class AllGamesViewHolder extends RecyclerView.ViewHolder {

        TextView series;
        TextView venue;
        TextView homeName;
        TextView homeScore;
        TextView awayName;
        TextView awayScore;
        TextView date;
        TextView status;
//        ImageView homeLogo;
//        ImageView awayLogo;
        public AllGamesViewHolder(@NonNull View itemView) {
            super(itemView);
            series = itemView.findViewById(R.id.all_games_series);
            venue = itemView.findViewById(R.id.all_games_venue);
            homeName = itemView.findViewById(R.id.all_games_home_name);
            homeScore = itemView.findViewById(R.id.all_games_home_score);
            awayName = itemView.findViewById(R.id.all_games_away_name);
            awayScore = itemView.findViewById(R.id.all_games_away_score);
            date = itemView.findViewById(R.id.all_games_date);
            status = itemView.findViewById(R.id.all_games_status);
//            homeLogo = itemView.findViewById(R.id.games_home_logo);
//            awayLogo = itemView.findViewById(R.id.games_away_logo);
        }
    }

    public  AllGamesAdapter(Context context, List<MatchListModel> matchList){
        this.context = context;
        this.matchList = matchList;
        this.controller = new GlobalController(this.context);
    }
    @NonNull
    @Override
    public AllGamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_games_list,parent,false);
        return new AllGamesViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AllGamesViewHolder holder, final int position) {
        match = matchList.get(position);

        holder.series.setText("Series: " + match.getSeries().getName());
        holder.venue.setText("Venue: " + match.getVenue().getName());
        holder.homeName.setText(match.getHomeTeam().getName());
        holder.homeScore.setText(match.getScores().getHomeScore());
        holder.awayScore.setText(match.getScores().getAwayScore());
        holder.awayName.setText(match.getAwayTeam().getName());
        holder.date.setText(match.getStartDateTime().substring(0,10));
        holder.status.setText(match.getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.match_details);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                showDialog(matchList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public void showDialog(final MatchListModel match){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CricketApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CricketApi api = retrofit.create(CricketApi.class);

        Call<MatchDetailsModel> call = api.getMatchDetails(controller.getDefaultSeries(),String.valueOf(match.getId()));

        call.enqueue(new Callback<MatchDetailsModel>() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<MatchDetailsModel> call, retrofit2.Response<MatchDetailsModel> response) {
                MatchDetailsModel.Matches matchDetails = response.body().getMatches();

                TextView series = dialog.findViewById(R.id.match_details_series);
                TextView status = dialog.findViewById(R.id.match_details_status);
                TextView venue = dialog.findViewById(R.id.match_details_venue);
                TextView summary = dialog.findViewById(R.id.match_details_summary);
                TextView batting = dialog.findViewById(R.id.match_details_batting);
                TextView homeName = dialog.findViewById(R.id.match_details_home_name);
                TextView homeScore = dialog.findViewById(R.id.match_details_home_score);
                TextView homeOvers = dialog.findViewById(R.id.match_details_home_overs);
                TextView awayName = dialog.findViewById(R.id.match_details_away_name);
                TextView awayScore = dialog.findViewById(R.id.match_details_away_score);
                TextView awayOvers = dialog.findViewById(R.id.match_details_away_overs);
                TextView date = dialog.findViewById(R.id.match_details_date);
                ImageView homeLogo = dialog.findViewById(R.id.match_details_home_logo);
                ImageView awayLogo = dialog.findViewById(R.id.match_details_away_logo);
                RelativeLayout loading = dialog.findViewById(R.id.relative_loading);

                series.setText("Series: "+matchDetails.getSeries().getName());
                status.setText(matchDetails.getStatus());
                venue.setText("Venue: "+matchDetails.getVenue().getName());
                summary.setText(matchDetails.getMatchSummaryText());
                if(matchDetails.getHomeTeam().getBatting()){
                    batting.setText("Batting Team: "+matchDetails.getHomeTeam().getName());
                }else{
                    batting.setText("Batting Team: "+matchDetails.getAwayTeam().getName());
                }

                homeName.setText(matchDetails.getHomeTeam().getName());
                homeScore.setText("Score: "+matchDetails.getScores().getHomeScore());
                homeOvers.setText("Overs: "+matchDetails.getScores().getHomeOvers());
                awayName.setText(matchDetails.getAwayTeam().getName());
                awayScore.setText("Score: "+matchDetails.getScores().getAwayScore());
                awayOvers.setText("Overs: "+matchDetails.getScores().getAwayOvers());
                date.setText(matchDetails.getCmsMatchStartDate().substring(0,10));

                if(matchDetails.getHomeTeam().getLogoUrl() != null){
                    Picasso.get().load(matchDetails.getHomeTeam().getLogoUrl()).into(homeLogo);
                }else{
                    homeLogo.setImageResource(R.drawable.no_image);
                }
                if(matchDetails.getAwayTeam().getLogoUrl() != null){
                    Picasso.get().load(matchDetails.getAwayTeam().getLogoUrl()).into(awayLogo);
                }else{
                    awayLogo.setImageResource(R.drawable.no_image);
                }
                loading.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<MatchDetailsModel> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }

}
