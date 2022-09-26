package com.example.timing.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timing.homeAdapter.HomePagerAdapter;
import com.example.timing.InsteadFragment;
import com.example.timing.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private TabLayout m_TabLayout;
    private ViewPager2 m_viewPager;
    private List<String> m_titles = new ArrayList<>();
    private List<Fragment> m_fragments = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_home, container, false);
        initFragment();
        initView(rootView);
        return rootView;
    }

    private void initView(View v) {
        m_TabLayout=v.findViewById(R.id.home_top_navigation);
        m_viewPager=v.findViewById(R.id.home_pages);
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
        m_titles.add("推荐");
        m_titles.add("关注");
        m_titles.add("附近");
        m_fragments.add(new HomePagerFragment());
        m_fragments.add(InsteadFragment.newInstance("关注"));
        m_fragments.add(InsteadFragment.newInstance("关注"));
    }

}