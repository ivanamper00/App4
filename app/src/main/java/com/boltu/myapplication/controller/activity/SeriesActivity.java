package com.boltu.myapplication.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.boltu.myapplication.R;
import com.boltu.myapplication.adapter.SeriesAdapter;
import com.boltu.myapplication.controller.GlobalController;
import com.boltu.myapplication.model.series.SeriesListModel;

import java.util.List;

public class SeriesActivity extends AppCompatActivity {
    GlobalController globalController;
    RecyclerView recyclerView;
//    CardView noData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);

        globalController = new GlobalController(this);
        recyclerView = findViewById(R.id.series_recycler);
//        noData = findViewById(R.id.card_no_data);
//        noData.setVisibility(View.GONE);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        List<SeriesListModel> seriesListModels = globalController.retrieveSeries();

        if(seriesListModels.size() == 0){
//            noData.setVisibility(View.VISIBLE);
        }else{
            SeriesAdapter adapter = new SeriesAdapter(this, seriesListModels);
            recyclerView.setAdapter(adapter);
        }

    }
    @Override
    public void onBackPressed() {
        globalController.NextIntent(MainActivity.class);
        finish();
    }
}