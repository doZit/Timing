package com.example.timing.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.timing.R;
import com.example.timing.homeAdapter.HomePagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePagerFragment extends Fragment {
    private TabLayout m_TabLayout;
    private ViewPager2 m_viewPager;
    private List<String> m_titles = new ArrayList<>(
            Arrays.asList("热门","视频","体育","娱乐","摄影","学习","游戏","新闻","情感","减肥","美妆"));
    private List<Fragment> m_fragments = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.home_pager, container, false);
        initFragment();
        initView(rootView);
        return rootView;
    }

    private void initView(View v) {
        m_TabLayout=v.findViewById(R.id.home_second_navigation);
        m_viewPager=v.findViewById(R.id.home_second_pages);
        HomePagerAdapter homePagerAdapter=new HomePagerAdapter(getActivity(), m_fragments);
        m_viewPager.setAdapter(homePagerAdapter);
        new TabLayoutMediator(m_TabLayout, m_viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(m_titles.get(position));
            }
        }).attach();
    }

    private void initFragment() {
        for (int i=0 ;i<m_titles.size();i++){
            m_fragments.add(new HomeSecondPagerFragment());
        }
    }
}
