package com.example.timing.publish;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.timing.publishAdapter.BottomPicAdapter;
import com.example.timing.R;
import com.example.timing.entity.Photo;

import java.io.Serializable;
import java.util.List;

public class BottomShowImage extends Fragment {
    private View rootView;
    private Context m_context;
    private RecyclerView recyclerView;
    private BottomPicAdapter bottomPicAdapter;
    private TextView textView;

    private List<Photo> m_selectImageList;

    public void setM_selectImageList(List<Photo> m_selectImageList) {
        this.m_selectImageList = m_selectImageList;
    }

    public BottomShowImage(Context context) {
        m_context=context;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView == null){
            rootView=inflater.inflate(R.layout.bottom_show_image, container, false);
        }
        init();
        enterEditPage();
        return rootView;
    }

    private void enterEditPage() {
        textView=rootView.findViewById(R.id.delete);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), PublishActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("photo/video", (Serializable) m_selectImageList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void init() {
        recyclerView=rootView.findViewById(R.id.bottom_pic);
        LinearLayoutManager manager = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(manager);
        bottomPicAdapter=new BottomPicAdapter(m_context);
        bottomPicAdapter.setM_selectImageList(m_selectImageList);
        bottomPicAdapter.setImage_size(50);
        recyclerView.setAdapter(bottomPicAdapter);
    }
}