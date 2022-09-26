package com.example.timing;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsteadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsteadFragment extends Fragment {

    private static final String ARG_TEXT = "param1";

    private String mTextString;
    private View rootView;

    public InsteadFragment() {
        // Required empty public constructor

    }

    public static InsteadFragment newInstance(String param1) {    //构建BlankFragment
        InsteadFragment fragment = new InsteadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTextString = getArguments().getString(ARG_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView == null){
            rootView=inflater.inflate(R.layout.fragment_instead, container, false);
        }
        initView();
        return rootView;
    }

    private void initView() {
        TextView textView=rootView.findViewById(R.id.home_second_text);
        textView.setText(mTextString);
    }
}