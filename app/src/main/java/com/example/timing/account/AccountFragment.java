package com.example.timing.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.timing.R;
import com.example.timing.entity.Account;
import com.example.timing.homeAdapter.HomePagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountFragment extends Fragment {
    View rootView;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private Button otherButton;
    private Account m_accounts ;
    private List<Fragment> m_fragments = new ArrayList<>();
//    private ImageView personal_head;
    private TextView personal_name,personal_sign,concern_count,like_count,community_count;

    private List<String> m_tabTitle = new ArrayList<>(Arrays.asList("发布","喜欢","收藏"));

    private SendBridge sendBridge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_account, container, false);
        initData();
        initPersonalInfo();
        initTabView();
        initLinkList();
        setLeftNavigation();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sendBridge=(SendBridge) getActivity();
    }

    public interface SendBridge{
        void openLeftNavigation();
    }

    private void setLeftNavigation() {
        otherButton=rootView.findViewById(R.id.left_navigation);
        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBridge.openLeftNavigation();
            }
        });
    }

    private void initLinkList() {
        LinearLayout linearLayout1 = rootView.findViewById(R.id.concern);
        LinearLayout linearLayout2 = rootView.findViewById(R.id.like);
        LinearLayout linearLayout3 = rootView.findViewById(R.id.community);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LinkList.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("account",m_accounts);
                bundle.putString("flag","concern");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),LinkList.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("account",m_accounts);
                bundle.putString("flag","like");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),LinkList.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("account",m_accounts);
                bundle.putString("flag","community");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        ArrayList<Integer> data = new ArrayList<>(Arrays.asList(1,2,3));
//        ShowCard card = new ShowCard("azx",1,data,"yesyu",m_tabTitle,m_tabTitle);
//        ShowCard card1 = new ShowCard("wwer",3,data,"yessssu",m_tabTitle,m_tabTitle);
//        List<ShowCard> data_card = new ArrayList<>(Arrays.asList(card,card1,card));
//        List<ShowCard> data_card1 = new ArrayList<>(Arrays.asList(card1,card,card1));
        m_accounts = new Account();
        m_accounts.setM_name("xxxxx");
        m_accounts.setM_personalSign("daydayup");
        m_accounts.setM_concernAccountId(data);
        m_accounts.setM_likeAccountId(data);
        m_accounts.setM_joinCommunityId(data);
        m_fragments.add(new MeLinkFragment());
        m_fragments.add(new MeLinkFragment());
        m_fragments.add(new MeLinkFragment());
    }

    private void initTabView() {
        tabLayout=(TabLayout) rootView.findViewById(R.id.me_navigation);
        viewPager2=(ViewPager2) rootView.findViewById(R.id.link_pages);
        HomePagerAdapter homePagerAdapter=new HomePagerAdapter(this.getActivity(), m_fragments);
        viewPager2.setAdapter(homePagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(m_tabTitle.get(position));
            }
        }).attach();
    }

    private void initPersonalInfo() {
        personal_name =(TextView) rootView.findViewById(R.id.personal_name);
        personal_sign = (TextView) rootView.findViewById(R.id.personal_sign);
        concern_count= (TextView) rootView.findViewById(R.id.concern_count);
        like_count=(TextView) rootView.findViewById(R.id.like_count);
        community_count = (TextView) rootView.findViewById(R.id.community_count);

        personal_name.setText(m_accounts.getM_name());
        personal_sign.setText(m_accounts.getM_personalSign());
        concern_count.setText(m_accounts.getM_concernAccountId().size()+"");
        like_count.setText(m_accounts.getM_likeAccountId().size()+"");
        community_count.setText(m_accounts.getM_joinCommunityId().size()+"");
    }
}