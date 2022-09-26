package com.example.timing.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.timing.R;
import com.example.timing.TabAndViewP2Adapter;
import com.example.timing.entity.Account;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkList extends AppCompatActivity {
    private Button back_button;
    private Account m_account;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private List<Fragment> fragmentList=new ArrayList<>();
    private String flag="";

    public LinkList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link_common1);
        // Inflate the layout for this fragment
        setData();
        initCard();
        setBackButton();
    }

    private void setBackButton() {
        back_button=(Button)findViewById(R.id.publish_back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setData() {
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        m_account=(Account) bundle.getSerializable("account");
        flag=(String)bundle.getString("flag");
        fragmentList.add(ConcernList.newInstance(m_account.getM_concernAccountId()));
        fragmentList.add(LikeList.newInstance(m_account.getM_likeAccountId()));
        fragmentList.add(CommunityList.newInstance(m_account.getM_joinCommunityId()));
    }

    private void initCard() {
        TextView name = findViewById(R.id.name);
        name.setText(m_account.getM_name());
        List<String> m_titles = new ArrayList<>(Arrays.asList("关注","粉丝","社区"));

        tabLayout=findViewById(R.id.link_navigation);
        viewPager2=findViewById(R.id.list);
        TabAndViewP2Adapter tabAndViewP2Adapter=new TabAndViewP2Adapter(getSupportFragmentManager(),getLifecycle(),fragmentList);
        viewPager2.setAdapter(tabAndViewP2Adapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(m_titles.get(position));
            }
        }).attach();
        if(flag.equals("like")){
            tabLayout.getTabAt(1).select();
        }else if(flag.equals("community")){
            tabLayout.getTabAt(2).select();
        }
    }
}
