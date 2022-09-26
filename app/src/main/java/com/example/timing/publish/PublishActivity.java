package com.example.timing.publish;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.timing.publishAdapter.PublishImageAdapter;
import com.example.timing.R;
import com.example.timing.entity.Photo;

import java.util.List;

public class PublishActivity extends Activity {
    private View rootView;
    private RecyclerView recyclerView;
    private PublishImageAdapter publishImageAdapter;
    private List<Photo> imageViews;
    private Button back_button;

    public PublishActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_publish);
        // Inflate the layout for this fragment
        initImageView();
        setData();
        setBackButton();
    }

    private void setBackButton() {
        back_button=(Button)findViewById(R.id.publish_back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askWhetherBack();
            }

            private void askWhetherBack() {
                final AlertDialog.Builder askDialog=new AlertDialog.Builder(PublishActivity.this);
                askDialog.setMessage("你要退出当前的编辑吗");
                askDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                askDialog.setNegativeButton("关闭",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { }
                        });
                askDialog.show();
            }
        });
    }

    private void setData() {
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        imageViews= (List<Photo>) bundle.getSerializable("photo/video");
    }

    private void initImageView() {
        recyclerView = (RecyclerView) findViewById(R.id.images_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,5);
        recyclerView.setLayoutManager(layoutManager);
        publishImageAdapter = new PublishImageAdapter(this,imageViews);
        recyclerView.setAdapter(publishImageAdapter);
        changeImageSize();
    }

    private void changeImageSize(){
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metric);
        int width = metric.widthPixels; // 宽度（PX）
        int changeSize=(width-(5+1)*8)/5;
        publishImageAdapter.setImage_size(changeSize);
        int height = metric.heightPixels; // 高度（PX）
    }
}