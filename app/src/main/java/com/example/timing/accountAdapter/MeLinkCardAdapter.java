package com.example.timing.accountAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timing.Detail_Card;
import com.example.timing.R;
import com.example.timing.entity.ShowCard;

import java.util.List;

public class MeLinkCardAdapter extends RecyclerView.Adapter<MeLinkCardAdapter.ViewHolder> {
    private Context m_context;
    private List<ShowCard> m_showCardList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        //        private ImageButton headPic;
        private TextView content,name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.link_name);
            content = (TextView) itemView.findViewById(R.id.link_content);
        }
    }

    public MeLinkCardAdapter (Context context, List<ShowCard> showCardList){
        m_context=context;
        m_showCardList = showCardList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(m_context).inflate(R.layout.link_card_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShowCard showCard = m_showCardList.get(position);
        holder.name.setText(showCard.getM_name());
        holder.content.setText(showCard.getM_content());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_context, Detail_Card.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("showcard",showCard);
                intent.putExtras(bundle);
                m_context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return m_showCardList.size();
    }
}
