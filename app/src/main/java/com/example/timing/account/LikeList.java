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

public class LikeList extends Fragment {
    private List<Integer> like_idList;
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
        like_idList=bundle.getIntegerArrayList("LikeList");
        initCard();
        return rootView;
    }

    static LikeList newInstance(ArrayList<Integer> like_id_list){
        LikeList likeList=new LikeList();
        Bundle bundle=new Bundle();
        bundle.putIntegerArrayList("LikeList",like_id_list);
        likeList.setArguments(bundle);
        return likeList;
    }

    private void initCard() {
        recyclerView =  rootView.findViewById(R.id.item_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        linkListItemAdapter = new LinkListItemAdapter(this.getActivity(),like_idList,"like");
        recyclerView.setAdapter(linkListItemAdapter);
    }
}
