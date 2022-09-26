package com.example.timing.homeAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.timing.Detail_Card;
import com.example.timing.entity.Account;
import com.example.timing.home.PhotoShowArea;
import com.example.timing.R;
import com.example.timing.account.SelfPage;
import com.example.timing.TabPagerAdapter;
import com.example.timing.entity.ShowCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowCardAdapter extends RecyclerView.Adapter<ShowCardAdapter.ViewHolder>{
    private Context m_context;
    private Lifecycle m_lifecycle;
    private FragmentManager m_fragmentManager;
    private List<ShowCard> m_showCardList;
    private int m_image;
    private List<Fragment> m_fragments;

    static class ViewHolder extends RecyclerView.ViewHolder{
//        private ImageButton headPic;
        private TextView nickname,likes,content;
        private ViewPager2 viewPager2;
        private RecyclerView recyclerView;
        private LinearLayout linearLayout;
        private ImageView head_pic;

        public ViewHolder(View itemView) {
            super(itemView);
            nickname = (TextView) itemView.findViewById(R.id.nickname);
            likes = (TextView) itemView.findViewById(R.id.likes_count);
            content = (TextView) itemView.findViewById(R.id.text_content);
            viewPager2 = (ViewPager2) itemView.findViewById(R.id.photo_area);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.pic_dot);
            linearLayout = itemView.findViewById(R.id.mark_area);
            head_pic = itemView.findViewById(R.id.head_pic);
        }
    }

    public ShowCardAdapter (Context context, Lifecycle lifecycle, List<ShowCard> showCardList,FragmentManager fragmentManager){
        m_context=context;
        m_lifecycle=lifecycle;
        m_showCardList = showCardList;
        m_fragmentManager=fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(m_context).inflate(R.layout.show_card,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String nor_string="人点赞";
        ShowCard showCard = m_showCardList.get(i);
//        viewHolder.headPic.setBackground((Drawable) showCard.getM_headPic());
        viewHolder.nickname.setText(showCard.getM_name());
        viewHolder.likes.setText(showCard.getM_lover().size()+nor_string);
        viewHolder.content.setText(showCard.getM_content());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_context, Detail_Card.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("showcard",showCard);
                intent.putExtras(bundle);
                m_context.startActivity(intent);
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

        viewHolder.head_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_context, SelfPage.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("account",account);
                intent.putExtras(bundle);
                m_context.startActivity(intent);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(m_context,LinearLayoutManager.HORIZONTAL,false);
        viewHolder.recyclerView.setLayoutManager(manager);
        PhotoShowAdapter adapter=new PhotoShowAdapter(showCard.getM_picPath());
        viewHolder.recyclerView.setAdapter(adapter);

        m_fragments=new ArrayList<>();
        for(int j=0;j<showCard.getM_picPath().size();j++){
            m_fragments.add(new PhotoShowArea(showCard.getM_picPath().get(j)));
        }
        TabPagerAdapter tabPagerAdapter=new TabPagerAdapter(m_fragmentManager,m_lifecycle,m_fragments);
        viewHolder.viewPager2.setAdapter(tabPagerAdapter);
        viewHolder.viewPager2.setCurrentItem(0);
        viewHolder.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        setDotColor(0);
                        break;
                    case 1:
                        setDotColor(1);
                        break;
                    case 2:
                        setDotColor(2);
                        break;
                    case 3:
                        setDotColor(3);
                        break;
                    case 4:
                        setDotColor(4);
                        break;
                    case 5:
                        setDotColor(5);
                        break;
                    case 6:
                        setDotColor(6);
                        break;
                    case 7:
                        setDotColor(7);
                        break;
                    case 8:
                        setDotColor(8);
                        break;
                }
            }

            private void setDotColor(int i) {
                for(int j=0;j<manager.getChildCount();j++){
                    View view=manager.getChildAt(j);
                    PhotoShowAdapter.ViewHolder holder=(PhotoShowAdapter.ViewHolder) viewHolder.recyclerView.getChildViewHolder(view);
                    if(j==i){
                        holder.imageView.setBackgroundResource(R.drawable.pic_dot_select);
                    }else{
                        holder.imageView.setBackgroundResource(R.drawable.pic_dot_unselct);
                    }
                }
            }
        });

        adapter.setOnItemClickListener(new PhotoShowAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                viewHolder.viewPager2.setCurrentItem(position);
                adapter.setPosition(position);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return m_showCardList.size();
    }

}
