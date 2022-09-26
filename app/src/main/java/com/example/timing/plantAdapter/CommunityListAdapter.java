package com.example.timing.plantAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timing.R;
import com.example.timing.entity.Community;

import java.util.List;

public class CommunityListAdapter extends RecyclerView.Adapter<CommunityListAdapter.ViewHolder> {
    private Context m_context;
    private List<Community> communityList;

    public class ViewHolder extends RecyclerView.ViewHolder{
//        private ImageView community_head;
        private TextView community_name,community_address;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            community_name = (TextView) itemView.findViewById(R.id.community_name);
            community_address = (TextView) itemView.findViewById(R.id.community_address);
        }
    }

    public CommunityListAdapter(@NonNull Context context, List<Community> communities) {
        m_context = context;
        communityList = communities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(m_context).inflate(R.layout.cycle_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Community community = communityList.get(position);
        holder.community_name.setText(community.getM_name());
        holder.community_address.setText("地址："+community.getM_address());

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
        return communityList.size();
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
