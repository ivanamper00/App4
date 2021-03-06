package com.boltu.myapplication.controller.activity.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boltu.myapplication.R;
import com.boltu.myapplication.adapter.GamesAdapter;
import com.boltu.myapplication.controller.GlobalController;
import com.boltu.myapplication.model.games.MatchListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SeriesGamesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeriesGamesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SeriesGamesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SeriesGames.
     */
    // TODO: Rename and change types and number of parameters
    public static SeriesGamesFragment newInstance(String param1, String param2) {
        SeriesGamesFragment fragment = new SeriesGamesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    GlobalController globalController;
    RecyclerView recyclerView;
    CardView noData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_series_games, container, false);
        globalController = new GlobalController(getContext());
        recyclerView = view.findViewById(R.id.games_recycler);
        noData = view.findViewById(R.id.card_no_data);
        noData.setVisibility(View.GONE);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);

        List<MatchListModel> matchList = globalController.retrieveGames();
        List<MatchListModel> filteredList = new ArrayList<>();

        for(int i = 0; i<matchList.size(); i++){
            if(matchList.get(i).getStatus().equalsIgnoreCase("UPCOMING") && !matchList.get(i).getHomeTeam().getName().equalsIgnoreCase("Unknown")){
                filteredList.add(matchList.get(i));
            }
        }

        if(filteredList.size() == 0){
            noData.setVisibility(View.VISIBLE);
        }else{
            GamesAdapter adapter = new GamesAdapter(getContext(), filteredList);
            recyclerView.setAdapter(adapter);
        }


        return view;
    }
}