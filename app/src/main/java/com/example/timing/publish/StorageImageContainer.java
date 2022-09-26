package com.example.timing.publish;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.timing.R;
import com.example.timing.TabPagerAdapter;
import com.example.timing.entity.Photo;
import com.example.timing.entity.Video;
import com.example.timing.publishAdapter.ChoosePicAdapter;

import java.util.ArrayList;
import java.util.List;

public class StorageImageContainer extends Fragment {
    public static final int LOADER_ALL = 1;// 获取所有图片
    public static final int MAX_IMAGES = 9;// 最大图片数量
    private View rootView;
    private Context m_context;
    private int screenHeight;

    private RecyclerView recyclerView;
    private ChoosePicAdapter choosePicAdapter;
    private ViewPager2 viewPager2;

    private List<Photo> m_imageList;
    private List<Video> m_videoList;
    private List<Fragment> m_fragments;

    public StorageImageContainer(Context context) {
        m_context=context;
    }

    public void setM_imageList(List<Photo> m_imageList) {
        this.m_imageList = m_imageList;
    }

    public void setM_videoList(List<Video> m_videoList) {
        this.m_videoList = m_videoList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView == null){
            rootView=inflater.inflate(R.layout.storage_image_container, container, false);
        }
//        getLocalPhoto();
        init();
//        initBottom();
        if(!m_imageList.isEmpty() || !m_videoList.isEmpty()){
            init();
        }else{
            TextView textView=rootView.findViewById(R.id.no_pic);
            textView.setVisibility(View.VISIBLE);
            recyclerView=rootView.findViewById(R.id.image_container);
            recyclerView.setVisibility(View.INVISIBLE);
        }
        return rootView;
    }

    private void initBottom() {
//        viewPager2=rootView.findViewById(R.id.small_pic);
//        m_fragments=new ArrayList<>();
//        BottomShowImage bottomShowImage=new BottomShowImage(m_context);
//        bottomShowImage.setM_selectImageList(choosePicAdapter.getM_selectedImageList());
//        m_fragments.add(bottomShowImage);
//        TabPagerAdapter tabPagerAdapter=new TabPagerAdapter(getParentFragmentManager(),getLifecycle(),m_fragments);
//        viewPager2.setAdapter(tabPagerAdapter);

//        LinearLayout.LayoutParams layoutParams=(LinearLayout.LayoutParams) recyclerView.getLayoutParams();
//        LinearLayout.LayoutParams layoutParams1=(LinearLayout.LayoutParams) viewPager2.getLayoutParams();
//        layoutParams.height=screenHeight-layoutParams1.height;
//        recyclerView.setLayoutParams(layoutParams);
//        viewPager2.setVisibility(View.VISIBLE);
//        if(!choosePicAdapter.getM_selectedImageList().isEmpty()){
//            LinearLayout.LayoutParams layoutParams=(LinearLayout.LayoutParams) recyclerView.getLayoutParams();
//            LinearLayout.LayoutParams layoutParams1=(LinearLayout.LayoutParams) viewPager2.getLayoutParams();
//            layoutParams.height=screenHeight-layoutParams1.height;
//            recyclerView.setLayoutParams(layoutParams);
//            viewPager2.setVisibility(View.VISIBLE);
//        }else{
//            LinearLayout.LayoutParams layoutParams=(LinearLayout.LayoutParams) recyclerView.getLayoutParams();
//            layoutParams.height=screenHeight;
//            recyclerView.setLayoutParams(layoutParams);
//            viewPager2.setVisibility(View.GONE);
//        }
    }

    private void getLocalPhoto() {
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        loaderManager.initLoader(LOADER_ALL, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            private final String[] IMAGE_PROJECTION = {
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.DATE_ADDED,
                    MediaStore.Images.Media._ID,
            };

            @NonNull
            @Override
            public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
                if (i == LOADER_ALL) {
                    return new CursorLoader(m_context,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            IMAGE_PROJECTION, null, null, IMAGE_PROJECTION[2]+" DESC");
                }
                return null;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
                if (cursor != null) {
                    m_imageList.clear();
                    while (cursor.moveToNext()) {
                        Photo item = new Photo();
                        @SuppressLint("Range") String path = cursor.getString(cursor.getColumnIndex(IMAGE_PROJECTION[0]));
                        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(IMAGE_PROJECTION[1]));
                        @SuppressLint("Range") long date = cursor.getLong(cursor.getColumnIndex(IMAGE_PROJECTION[2]));
                        item.setDate(date);
                        item.setName(name);
                        item.setPath(path);
                        m_imageList.add(item);
                    }
                    choosePicAdapter.setData(m_imageList);
                    choosePicAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<Cursor> loader) {

            }
        });
    }

    private void init() {
        recyclerView=rootView.findViewById(R.id.image_container);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(manager);
        choosePicAdapter=new ChoosePicAdapter(getActivity(),m_imageList,m_videoList);
        recyclerView.setAdapter(choosePicAdapter);
        choosePicAdapter.setSelectMaxImages(MAX_IMAGES);
        changeImageSize();
    }

    private void changeImageSize(){
        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getRealMetrics(metric);
        int width = metric.widthPixels; // 宽度（PX）
        int changeSize=(width-(3+1)*8)/3;
        choosePicAdapter.setImage_size(changeSize);
        int height = metric.heightPixels;// 高度（PX）
        screenHeight=height;
    }
}