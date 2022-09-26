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

public class ConcernList extends Fragment {

    private List<Integer> concern_idList;
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
        concern_idList=bundle.getIntegerArrayList("concernList");
        initCard();
        return rootView;
    }

    static ConcernList newInstance(ArrayList<Integer> concern_id_list){
        ConcernList concernList=new ConcernList();
        Bundle bundle=new Bundle();
        bundle.putIntegerArrayList("concernList",concern_id_list);
        concernList.setArguments(bundle);
        return concernList;
    }

    private void initCard() {
        recyclerView =  rootView.findViewById(R.id.item_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        linkListItemAdapter = new LinkListItemAdapter(this.getActivity(),concern_idList,"concern");
        recyclerView.setAdapter(linkListItemAdapter);
    }

}
