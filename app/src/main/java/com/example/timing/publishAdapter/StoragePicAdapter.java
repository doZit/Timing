package com.example.timing.publishAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timing.R;

import java.util.List;

public class StoragePicAdapter extends RecyclerView.Adapter<StoragePicAdapter.ViewHolder> {
    private List<Integer> m_imageViewList;

    public StoragePicAdapter(List<Integer> imageViewList) {
        m_imageViewList=imageViewList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
//        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.pic);
//            textView = (TextView) itemView.findViewById(R.id.select_num);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_pic_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setBackgroundResource(m_imageViewList.get(position));
//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                holder.textView.setBackgroundResource(R.drawable.select_number);
////                holder.textView.setText();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return m_imageViewList.size();
    }

}
