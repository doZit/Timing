package com.example.timing.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timing.R;

import java.util.ArrayList;
import java.util.List;

public class CommunityList extends Fragment {
    private List<Integer> community_idList;
    private View rootView;
    public RecyclerView recyclerView;
    private LinkListItemAdapter linkListItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.link_concern_account, container, false);
        }
        Bundle bundle = getArguments();
        community_idList=bundle.getIntegerArrayList("CommunityList");
        initCard();
        return rootView;
    }

    static CommunityList newInstance(ArrayList<Integer> community_id_list){
        CommunityList communityList=new CommunityList();
        Bundle bundle=new Bundle();
        bundle.putIntegerArrayList("CommunityList",community_id_list);
        communityList.setArguments(bundle);
        return communityList;
    }

    private void initCard() {
        recyclerView =  rootView.findViewById(R.id.item_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        linkListItemAdapter = new LinkListItemAdapter(this.getActivity(),community_idList,"community");
        recyclerView.setAdapter(linkListItemAdapter);
    }
}
