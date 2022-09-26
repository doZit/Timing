package com.example.timing.message;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.timing.LatestMessageListAdapter;
import com.example.timing.R;
import com.example.timing.plant.Community_Page;
import com.example.timing.plantAdapter.CommunityListAdapter;

import java.util.ArrayList;
import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link MessageFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class MessageFragment extends Fragment {
    private List<LinkMessage> linkMessageList = new ArrayList<>();
    private View rootView;
    private LatestMessageListAdapter latestMessageListAdapter;
    private RecyclerView recyclerView;
    private TextView createMessage;

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView == null){
            rootView=inflater.inflate(R.layout.fragment_message, container, false);
        }
        initLatestMassage();
        initDate();
        setCreateMessage();
        return rootView;
    }

    private void setCreateMessage() {
        createMessage = rootView.findViewById(R.id.create_talk);
        createMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), CreateTalk.class);
                startActivity(intent);
            }
        });
    }

    private void initLatestMassage() {
        recyclerView=(RecyclerView) rootView.findViewById(R.id.resent_massage_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        latestMessageListAdapter = new LatestMessageListAdapter(this.getActivity(),linkMessageList);
        recyclerView.setAdapter(latestMessageListAdapter);

        latestMessageListAdapter.setOnItemClickListener(new LatestMessageListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), TalkActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("message",linkMessageList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initDate() {
        linkMessageList.add(new LinkMessage("Jonny","good night","2022-01-01"));
        linkMessageList.add(new LinkMessage("Jeson","Hello","13:15"));
    }
}