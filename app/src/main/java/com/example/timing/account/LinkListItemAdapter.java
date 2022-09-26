package com.example.timing.account;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timing.R;
import com.example.timing.entity.Account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkListItemAdapter extends RecyclerView.Adapter<LinkListItemAdapter.ViewHolder> {
    private Context m_context;
    private List<Integer> m_item_list;
    private String m_type;

    static class ViewHolder extends RecyclerView.ViewHolder{
        //        private ImageButton headPic;
        private TextView name,button,concern_button;
        private ImageView head_pic;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            button = (TextView) itemView.findViewById(R.id.concern_button);
            head_pic = (ImageView) itemView.findViewById(R.id.head);
            concern_button= (TextView) itemView.findViewById(R.id.concern_button);
        }
    }

    public LinkListItemAdapter (Context context, List<Integer> item_list, String type){
        m_context=context;
        m_item_list = item_list;
        m_type=type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(m_context).inflate(R.layout.link_account_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(m_item_list.get(position)+"");
        if(m_type == "community"){
            holder.button.setText("加入");
        }

        holder.concern_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.concern_button.setText("已关注");
                holder.concern_button.setTextColor(Color.parseColor("#DCDCDC"));
            }
        });

        //测试数据
        ArrayList<Integer> data =new ArrayList<>(Arrays.asList(1,2,3));
        Account account = new Account();
        account.setM_name("xxxxx");
        account.setM_personalSign("daydayup");
        account.setM_concernAccountId(data);
        account.setM_likeAccountId(data);
        account.setM_joinCommunityId(data);
        holder.head_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_context, SelfPage.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("account",account);
                intent.putExtras(bundle);
                m_context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return m_item_list.size();
    }

}
