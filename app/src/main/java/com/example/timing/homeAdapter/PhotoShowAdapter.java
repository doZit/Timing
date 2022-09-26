package com.example.timing.homeAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timing.R;

import java.util.List;

public class PhotoShowAdapter extends RecyclerView.Adapter<PhotoShowAdapter.ViewHolder> {
    private List<Integer> m_photos;
    private int currentPosition=0;

    static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.dot);
        }
    }

    public PhotoShowAdapter(List<Integer> photos){
        m_photos=photos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_pic_dot,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                    notifyDataSetChanged();
                }
            }
        });
        if(position==getPosition()){
            holder.imageView.setBackgroundResource(R.drawable.pic_dot_select);
        }else{
            holder.imageView.setBackgroundResource(R.drawable.pic_dot_unselct);
        }
    }

    public int getPosition() {
        return currentPosition;
    }

    public void setPosition(int i){
        currentPosition=i;
    }

    @Override
    public int getItemCount() {
        return m_photos.size();
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
