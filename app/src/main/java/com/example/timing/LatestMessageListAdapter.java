package com.example.timing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timing.message.LinkMessage;

import java.util.List;

public class LatestMessageListAdapter extends RecyclerView.Adapter<LatestMessageListAdapter.ViewHolder>{
    private Context m_context;
    private List<LinkMessage> linkMessageList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        //        private ImageView sender_head;
        private TextView sender_name,latest_massage,send_time;
//        public TextView delete_text;
//        public LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sender_name = (TextView) itemView.findViewById(R.id.sender_name);
            latest_massage = (TextView) itemView.findViewById(R.id.latest_massage);
            send_time = (TextView) itemView.findViewById(R.id.send_time);
//            delete_text = (TextView) itemView.findViewById(R.id.delete_button);
//            linearLayout = (LinearLayout) itemView.findViewById(R.id.massage_container);
        }
    }

    public LatestMessageListAdapter(@NonNull Context context, List<LinkMessage> linkMessages) {
        m_context=context;
        linkMessageList = linkMessages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(m_context).inflate(R.layout.message_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LinkMessage linkMessage = linkMessageList.get(position);
        holder.sender_name.setText(linkMessage.getM_sender());
        holder.latest_massage.setText(linkMessage.getM_latestMessage());
        holder.send_time.setText(linkMessage.getM_send_time());

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
        return linkMessageList.size();
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
