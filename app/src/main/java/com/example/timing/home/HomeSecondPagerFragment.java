package com.example.timing.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timing.R;
import com.example.timing.entity.ShowCard;
import com.example.timing.homeAdapter.ShowCardAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeSecondPagerFragment extends Fragment {

    private List<ShowCard> showCardList=new ArrayList<>();
    private View rootView;
    public RecyclerView recyclerView;
    private ShowCardAdapter showCardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView == null){
            rootView=inflater.inflate(R.layout.fragment_home_second_pager, container, false);
        }
        initView();
        initCard();
        return rootView;
    }


    private void initCard() {
        recyclerView =  rootView.findViewById(R.id.home_card_show);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        showCardAdapter=new ShowCardAdapter(this.getActivity(),getLifecycle(), showCardList,this.getParentFragmentManager());
        recyclerView.setAdapter(showCardAdapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(),DividerItemDecoration.VERTICAL));
    }

    private void initView() {
        List<String> lovers = new ArrayList<>(Arrays.asList("1","2","3","4"));
        List<String> collector = new ArrayList<>(Arrays.asList("1","2","3","4"));
        List<Integer> photo = new ArrayList<>(Arrays.asList(R.drawable.one,R.drawable.one,R.drawable.one));
        List<Integer> photo1 = new ArrayList<>(Arrays.asList(R.drawable.one,R.drawable.one,R.drawable.one,R.drawable.one));
        ShowCard one = new ShowCard("Lay",1,photo,"Today is a happy day,wish you like me!",lovers,collector);
        ShowCard two = new ShowCard("SeHun",1,photo1,"I love you like you love me!",lovers,collector);
        showCardList.add(one);
        showCardList.add(two);
    }
}