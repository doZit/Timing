package com.example.timing.publish;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.timing.R;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.M)
public class SelectPhoto extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_READ_SD = 1;
    public static final String TAG = "SelectPhoto";

    private TextView pics,views,photo;
    private FragmentTransaction fragmentTransaction;
    private ImageView pics_line,view_line,photo_line;

    private List<TextView> textViewList=new ArrayList<>();
    private List<ImageView> imageViewList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic_select_page);
        checkPermission();
        initTabNavigation();
        initFragment();
    }

    private void checkPermission() {
        int readExSD = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        if (readExSD != PackageManager.PERMISSION_GRANTED) {
            // 请求获取权限
            Log.d("SelectPhoto", "request permission...");
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_SD);
        } else {
            Log.d("SelectPhoto", "permission has granted");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_SD && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "request permission success");
        } else {
            Log.d(TAG, "request permission fail");
            finish();
        }
    }

    private void initFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.pages, new PicStorage(this));
        fragmentTransaction.commit();
    }

    private void initTabNavigation() {
        pics=findViewById(R.id.pics);
        views=findViewById(R.id.take_view);
        photo=findViewById(R.id.take_photo);

        textViewList.add(pics);
        textViewList.add(views);
        textViewList.add(photo);

        for (int i=0;i<textViewList.size();i++){
            textViewList.get(i).setOnClickListener(this);
        }

        pics_line=findViewById(R.id.pics_line);
        view_line=findViewById(R.id.view_line);
        photo_line=findViewById(R.id.photo_line);

        imageViewList.add(pics_line);
        imageViewList.add(view_line);
        imageViewList.add(photo_line);
    }

    @Override
    public void onClick(View v){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.pics:
                setTextColor(R.id.pics);
                setUnderLine(R.id.pics_line);
                fragmentTransaction.replace(R.id.pages,new PicStorage(this));
                break;
            case R.id.take_view:
                setTextColor(R.id.take_view);
                setUnderLine(R.id.view_line);
//                fragmentTransaction.replace(R.id.pages,new PicStorage(this));
                break;
            case R.id.take_photo:
                setTextColor(R.id.take_photo);
                setUnderLine(R.id.photo_line);
//                fragmentTransaction.replace(R.id.pages,new PicStorage(this));
                break;
        }
        fragmentTransaction.commit();
    }

    private void setTextColor(int id){
        for (TextView b:textViewList){
            if(b.getId()==id){
                b.setTextColor(Color.parseColor("#000000"));
            }else{
                b.setTextColor(Color.parseColor("#DCDCDC"));
            }
        }
    }

    private void setUnderLine(int id){
        for (ImageView b:imageViewList){
            if(b.getId()==id){
                b.setVisibility(View.VISIBLE);
            }else{
                b.setVisibility(View.INVISIBLE);
            }
        }
    }
}
