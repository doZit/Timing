package com.example.timing.message;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timing.LatestMessageListAdapter;
import com.example.timing.R;

import java.util.List;

public class TalkListAdapter extends RecyclerView.Adapter<TalkListAdapter.ViewHolder> {
    private Context m_context;
    private List<Integer> m_item_list;
    private String m_type;

    static class ViewHolder extends RecyclerView.ViewHolder{
        //        private ImageButton headPic;
        private TextView name;
        private ImageView head_pic;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            head_pic = (ImageView) itemView.findViewById(R.id.head);
        }
    }

    public TalkListAdapter (Context context, List<Integer> item_list, String type){
        m_context=context;
        m_item_list = item_list;
        m_type=type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(m_context).inflate(R.layout.talk_list,parent,false);
        TalkListAdapter.ViewHolder holder = new TalkListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(m_item_list.get(position) + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return m_item_list.size();
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    public interface OnItemLongClickListener{
        void onClick(int position);
    }

    private OnItemClickListener longClickListener;

    public void setOnItemLongClickListener(OnItemClickListener longClickListener){
        this.longClickListener=longClickListener;
    }
}
