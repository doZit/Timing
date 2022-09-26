package com.example.timing.account;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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

public class SelfPage extends AppCompatActivity {
    private Button back_button;
    private Account m_account;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private List<Fragment> fragmentList=new ArrayList<>();
    private TextView personal_name,personal_sign,concern_count,like_count,community_count,concern_button,message_button;

    public SelfPage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self_page);
        // Inflate the layout for this fragment
        setData();
        initPersonalInfo();
        initTabView();
        initLinkList();
        setBackButton();
    }

    private void initLinkList() {
        LinearLayout linearLayout1 = findViewById(R.id.concern);
        LinearLayout linearLayout2 = findViewById(R.id.like);
        LinearLayout linearLayout3 = findViewById(R.id.community);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelfPage.this, LinkList.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("account",m_account);
                bundle.putString("flag","concern");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelfPage.this,LinkList.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("account",m_account);
                bundle.putString("flag","like");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelfPage.this,LinkList.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("account",m_account);
                bundle.putString("flag","community");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initTabView() {
        List<String> m_tabTitle = new ArrayList<>(Arrays.asList("发布","喜欢","收藏"));
        tabLayout=(TabLayout) findViewById(R.id.me_navigation);
        viewPager2=(ViewPager2)findViewById(R.id.link_pages);
        TabAndViewP2Adapter tabAndViewP2Adapter=new TabAndViewP2Adapter(getSupportFragmentManager(),getLifecycle(),fragmentList);
        viewPager2.setAdapter(tabAndViewP2Adapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(m_tabTitle.get(position));
            }
        }).attach();
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
        fragmentList.add(new MeLinkFragment());
        fragmentList.add(new MeLinkFragment());
        fragmentList.add(new MeLinkFragment());
    }

    private void initPersonalInfo() {
        personal_name =(TextView) findViewById(R.id.personal_name);
        personal_sign = (TextView) findViewById(R.id.personal_sign);
        concern_count= (TextView) findViewById(R.id.concern_count);
        like_count=(TextView) findViewById(R.id.like_count);
        community_count = (TextView) findViewById(R.id.community_count);
        concern_button= (TextView) findViewById(R.id.concern_button);
        message_button= (TextView) findViewById(R.id.message_button);

        personal_name.setText(m_account.getM_name());
        personal_sign.setText(m_account.getM_personalSign());
        concern_count.setText(m_account.getM_concernAccountId().size()+"");
        like_count.setText(m_account.getM_likeAccountId().size()+"");
        community_count.setText(m_account.getM_joinCommunityId().size()+"");

        concern_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concern_button.setText("已关注");
                concern_button.setTextColor(Color.parseColor("#DCDCDC"));
            }
        });
    }
}
