package com.example.timing.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.timing.R;

public class PhotoShowArea extends Fragment {
    View rootView;
    int m_image;

    public PhotoShowArea(int image) {
        // Required empty public constructor
        m_image=image;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null){
            rootView=inflater.inflate(R.layout.photo_show_area, container, false);
        }
        initView();
        return rootView;
    }

    private void initView() {
        ImageView imageView=rootView.findViewById(R.id.show_pic);
        imageView.setBackgroundResource(m_image);
    }
}