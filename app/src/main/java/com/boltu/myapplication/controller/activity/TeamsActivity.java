package com.boltu.myapplication.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.boltu.myapplication.R;
import com.boltu.myapplication.adapter.TeamsAdapter;
import com.boltu.myapplication.controller.GlobalController;
import com.boltu.myapplication.model.teams.TeamsListModel;

import java.util.List;

public class TeamsActivity extends AppCompatActivity {
    GlobalController globalController;
    RecyclerView recyclerView;
    CardView noData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        globalController = new GlobalController(this);
        noData = findViewById(R.id.card_no_data);
        noData.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.teams_recycler);
        GridLayoutManager grid = new GridLayoutManager(TeamsActivity.this,1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(grid);

        List<TeamsListModel> teamsListModelList = globalController.retrieveTeams();
        if(teamsListModelList.size() == 0){
            noData.setVisibility(View.VISIBLE);
        }else{
            TeamsAdapter adapter = new TeamsAdapter(this, teamsListModelList);
            recyclerView.setAdapter(adapter);
        }
    }
    @Override
    public void onBackPressed() {
        globalController.NextIntent(MainActivity.class);
        finish();
    }
}