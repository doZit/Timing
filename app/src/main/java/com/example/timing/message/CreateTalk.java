package com.example.timing.message;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timing.LatestMessageListAdapter;
import com.example.timing.R;
import com.example.timing.entity.Account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateTalk extends Activity {
    private Button back_button;
    private RecyclerView recyclerView;
    private TalkListAdapter talkListAdapter;
    private List<Integer> concern_idList;
    private List<Account> accounts=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_talk);
        // Inflate the layout for this fragment
        setData();
        initList();
        setBackButton();
    }

    private void initList() {
        recyclerView =  findViewById(R.id.talk_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        talkListAdapter = new TalkListAdapter(this,concern_idList,"like");
        recyclerView.setAdapter(talkListAdapter);

        talkListAdapter.setOnItemClickListener(new TalkListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(CreateTalk.this, TalkActivity.class);
                Bundle bundle=new Bundle();
                //报错原因为可能数据accounts为空，添加测试再测试
                bundle.putSerializable("account",accounts.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void setData() {
        //测试数据
        ArrayList<Integer> data =new ArrayList<>(Arrays.asList(1,2,3));
        Account account = new Account();
        account.setM_name("xxxxx");
        account.setM_personalSign("daydayup");
        account.setM_concernAccountId(data);
        account.setM_likeAccountId(data);
        account.setM_joinCommunityId(data);
        concern_idList=account.getM_concernAccountId();
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
}
