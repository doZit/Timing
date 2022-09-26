package com.example.timing.plant;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timing.R;
import com.example.timing.VedioMainActivity;
import com.example.timing.entity.Community;
import com.example.timing.plant.Community_Page;
import com.example.timing.plantAdapter.CommunityListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CycleFragment extends Fragment {
    private List<Community> communityList = new ArrayList<>();
    private View rootView;
    private RecyclerView recyclerView;
    private CommunityListAdapter communityListAdapter;

    public CycleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView == null){
            rootView=inflater.inflate(R.layout.fragment_cycle, container, false);
        }
        initCommunityList();
        initDate();
        return rootView;
    }

    private void initDate() {
        communityList.add(new Community("奥运社区","重庆市江北区"));
        communityList.add(new Community("风景社区","重庆市九龙坡区"));
        communityList.add(new Community("幸福社区","重庆市渝北区"));
        communityList.add(new Community("向往社区","重庆市沙坪坝区"));
    }

    private void initCommunityList() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.communities);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        communityListAdapter=new CommunityListAdapter(this.getActivity() , communityList);
        recyclerView.setAdapter(communityListAdapter);
        communityListAdapter.setOnItemClickListener(new CommunityListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
//                Intent intent = new Intent(getActivity(), Community_Page.class);
//                Bundle bundle=new Bundle();
//                bundle.putSerializable("community",communityList.get(position));
//                intent.putExtras(bundle);
                Intent intent = new Intent(getActivity(), VedioMainActivity.class);
                startActivity(intent);
            }
        });
    }
}