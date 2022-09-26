package com.example.timing.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timing.accountAdapter.MeLinkCardAdapter;
import com.example.timing.R;
import com.example.timing.entity.ShowCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeLinkFragment extends Fragment {

    private List<ShowCard> m_showCardList=new ArrayList<>();
    private View rootView;
    private RecyclerView recyclerView;
    private MeLinkCardAdapter meLinkCardAdapter;

    public MeLinkFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView == null){
            rootView=inflater.inflate(R.layout.me_link_card, container, false);
        }
        setData();
        initView();
        return rootView;
    }

    private void initView() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.me_cards);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        meLinkCardAdapter = new MeLinkCardAdapter(this.getActivity(),m_showCardList);
        recyclerView.setAdapter(meLinkCardAdapter);
    }

    public void setData(){
        List<Integer> data = new ArrayList<>(Arrays.asList(1,2,3));
        List<String> m_tabTitle = new ArrayList<>(Arrays.asList("发布","喜欢","收藏"));
        ShowCard card = new ShowCard("azx",1,data,"yesyu",m_tabTitle,m_tabTitle);
        ShowCard card1 = new ShowCard("wwer",3,data,"yessssu",m_tabTitle,m_tabTitle);
        m_showCardList.add(card);
        m_showCardList.add(card1);
        m_showCardList.add(card);
    }
}
