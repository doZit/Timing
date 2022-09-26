package com.example.timing.publish;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.timing.R;
import com.example.timing.TabPagerAdapter;
import com.example.timing.entity.Photo;
import com.example.timing.entity.Video;
import com.example.timing.publish.StorageImageContainer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PicStorage extends Fragment implements View.OnClickListener {
    private View rootView;
    private Context m_context;
    private Button back_button;
    private TextView pics,views,photo;
    private ImageView pics_line,view_line,photo_line;
    private ViewPager2 viewPager2;
    private TabPagerAdapter tabPagerAdapter;
    private FragmentTransaction fragmentTransaction;

    private List<Photo> m_imageList=new ArrayList<>();
    private List<Video> m_videoList=new ArrayList<>();
    private List<Photo> m_imageList_empty=new ArrayList<>();
    private List<Video> m_videoList_empty=new ArrayList<>();

    private List<Fragment> fragmentList=new ArrayList<>();
    private List<TextView> textViewList=new ArrayList<>();
    private List<ImageView> imageViewList=new ArrayList<>();

    public PicStorage(Context context) {
        m_context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView == null){
            rootView=inflater.inflate(R.layout.picture_storage, container, false);
        }
        getAllPhotoInfo();
        getAllVideoInfos();
        setBackButton();
        init();
        return rootView;
    }

    /**
     * 读取手机中所有图片信息
     */
    private void getAllPhotoInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                List<Photo> photos = new ArrayList<>();
                HashMap<String,List<Photo>> allPhotosTemp = new HashMap<>();//所有照片
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                String[] projImage = { MediaStore.Images.Media._ID
                        , MediaStore.Images.Media.DATA
                        ,MediaStore.Images.Media.SIZE
                        ,MediaStore.Images.Media.DISPLAY_NAME};
                Cursor mCursor = m_context.getContentResolver().query(mImageUri,
                        projImage,
                        MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png", "image/jpg"},
                        MediaStore.Images.Media.DATE_MODIFIED+" desc");

                if(mCursor!=null){
                    while (mCursor.moveToNext()) {
                        Photo item = new Photo();
                        // 获取图片的路径
                        @SuppressLint("Range") String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        @SuppressLint("Range") int size = mCursor.getInt(mCursor.getColumnIndex(MediaStore.Images.Media.SIZE))/1024;
                        @SuppressLint("Range") String displayName = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                        item.setDate(size);
                        item.setName(displayName);
                        item.setPath(path);
                        //用于展示相册初始化界面
                        m_imageList.add(item);
//                        photo.add(new MediaBean(MediaBean.Type.Image,path,size,displayName));
                        // 获取该图片的父路径名
                        String dirPath = new File(path).getParentFile().getAbsolutePath();
                        //存储对应关系
//                        if (allPhotosTemp.containsKey(dirPath)) {
//                            Photo item1 = new Photo();
//                             = allPhotosTemp.get(dirPath);
//                            item1.setDate(size);
//                            item1.setName(displayName);
//                            item1.setPath(path);
//                            m_imageList.add(item1);
////                            data.add(new MediaBean(MediaBean.Type.Image,path,size,displayName));
//                            continue;
//                        } else {
////                            m_imageList = new ArrayList<>();
////                            data.add(new MediaBean(MediaBean.Type.Image,path,size,displayName));
//                            Photo item1 = new Photo();
//                            item1.setDate(size);
//                            item1.setName(displayName);
//                            item1.setPath(path);
//                            m_imageList.add(item1);
//                            allPhotosTemp.put(dirPath,m_imageList);
//                        }
                    }
                    mCursor.close();
                }
                //更新界面
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //...
                    }
                });
            }
        }).start();
    }

    /**
     * 获取手机中所有视频的信息
     */
    private void getAllVideoInfos(){
        new Thread(new Runnable() {
            @SuppressLint("Range")
            @Override
            public void run() {
                HashMap<String,List<Video>> allPhotosTemp = new HashMap<>();//所有照片
                Uri mImageUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] proj = { MediaStore.Video.Thumbnails._ID
                        , MediaStore.Video.Thumbnails.DATA
                        ,MediaStore.Video.Media.DURATION
                        ,MediaStore.Video.Media.SIZE
                        ,MediaStore.Video.Media.DISPLAY_NAME
                        ,MediaStore.Video.Media.DATE_MODIFIED};
                Cursor mCursor = m_context.getContentResolver().query(mImageUri,
                        proj,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                                + MediaStore.Video.Media.MIME_TYPE + "=? or "
                                + MediaStore.Video.Media.MIME_TYPE + "=?",
                        new String[]{"video/mp4","video/3gp", "video/aiv", "video/rmvb", "video/vob", "video/flv",
                                "video/mkv", "video/mov", "video/mpg"},
                        MediaStore.Video.Media.DATE_MODIFIED+" desc");
                if(mCursor!=null){
                    while (mCursor.moveToNext()) {
                        Video item= new Video();
                        // 获取视频的路径
                        @SuppressLint("Range") int videoId = mCursor.getInt(mCursor.getColumnIndex(MediaStore.Video.Media._ID));
                        @SuppressLint("Range") String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Video.Media.DATA));
                        @SuppressLint("Range") int duration = mCursor.getInt(mCursor.getColumnIndex(MediaStore.Video.Media.DURATION));
                        @SuppressLint("Range") long size = mCursor.getLong(mCursor.getColumnIndex(MediaStore.Video.Media.SIZE))/1024; //单位kb
                        if(size<0){
                            //某些设备获取size<0，直接计算
                            Log.e("dml","this video size < 0 " + path);
                            size = new File(path).length()/1024;
                        }
                        @SuppressLint("Range") String displayName = mCursor.getString(mCursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                        @SuppressLint("Range") long modifyTime = mCursor.getLong(mCursor.getColumnIndex(MediaStore.Video.Media.DATE_MODIFIED));//暂未用到

                        //提前生成缩略图，再获取：http://stackoverflow.com/questions/27903264/how-to-get-the-video-thumbnail-path-and-not-the-bitmap
                        MediaStore.Video.Thumbnails.getThumbnail(m_context.getContentResolver(), videoId, MediaStore.Video.Thumbnails.MICRO_KIND, null);
                        String[] projection = { MediaStore.Video.Thumbnails._ID, MediaStore.Video.Thumbnails.DATA};
                        Cursor cursor = m_context.getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI
                                , projection
                                , MediaStore.Video.Thumbnails.VIDEO_ID + "=? "
                                , new String[]{videoId+"",}
                                , null);
                        String thumbPath = "";
//                        while (cursor.moveToNext()){
//                            thumbPath = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA));
//                        }
                        BitmapFactory.Options options=new BitmapFactory.Options();
                        options.inDither = false;
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        item.setForeground(MediaStore.Video.Thumbnails.getThumbnail(m_context.getContentResolver(), videoId,  MediaStore.Images.Thumbnails.MICRO_KIND, options));
//                        MediaStore.Video.Thumbnails.getThumbnail(m_context.getContentResolver(), videoId,  MediaStore.Images.Thumbnails.MICRO_KIND, options);
                        //然后将其加入到videoList
//                        videoList.add(info);
                        cursor.close();
                        // 获取该视频的父路径名
                        String dirPath = new File(path).getParentFile().getAbsolutePath();
                        //存储对应关系
                        item.setSize(size);
                        item.setName(displayName);
                        item.setPath(path);
                        item.setThumbPath(thumbPath);
                        m_videoList.add(item);
//                        if (allPhotosTemp.containsKey(dirPath)) {
//                            m_videoList = allPhotosTemp.get(dirPath);
//                            item.setSize(size);
//                            item.setName(displayName);
//                            item.setPath(path);
//                            item.setThumbPath(thumbPath);
//                            m_videoList.add(item);
////                            List<Video> data = allPhotosTemp.get(dirPath);
////                            data.add(new MediaBean(MediaBean.Type.Video,path,thumbPath,duration,size,displayName));
//                            continue;
//                        } else {
//                            item.setSize(size);
//                            item.setName(displayName);
//                            item.setPath(path);
//                            item.setThumbPath(thumbPath);
//                            m_videoList.add(item);
////                            data.add(new MediaBean(MediaBean.Type.Video,path,thumbPath,duration,size,displayName));
//                            allPhotosTemp.put(dirPath,m_videoList);
//                        }
                    }
                    mCursor.close();
                }
                //更新界面
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //...
                    }
                });
            }
        }).start();
    }

    private void init() {
        StorageImageContainer storageImageContainer1=new StorageImageContainer(m_context);
        storageImageContainer1.setM_imageList(m_imageList);
        storageImageContainer1.setM_videoList(m_videoList);
        fragmentList.add(storageImageContainer1);

        StorageImageContainer storageImageContainer2=new StorageImageContainer(m_context);
        storageImageContainer2.setM_videoList(m_videoList);
        storageImageContainer2.setM_imageList(m_imageList_empty);
        fragmentList.add(storageImageContainer2);

        StorageImageContainer storageImageContainer3=new StorageImageContainer(m_context);
        storageImageContainer3.setM_videoList(m_videoList_empty);
        storageImageContainer3.setM_imageList(m_imageList);
        fragmentList.add(storageImageContainer3);

        pics=rootView.findViewById(R.id.pics);
        views=rootView.findViewById(R.id.take_view);
        photo=rootView.findViewById(R.id.take_photo);

        textViewList.add(pics);
        textViewList.add(views);
        textViewList.add(photo);

        for (int i=0;i<textViewList.size();i++){
            textViewList.get(i).setOnClickListener(this);
        }

        pics_line=rootView.findViewById(R.id.pics_line);
        view_line=rootView.findViewById(R.id.view_line);
        photo_line=rootView.findViewById(R.id.photo_line);

        imageViewList.add(pics_line);
        imageViewList.add(view_line);
        imageViewList.add(photo_line);

        viewPager2=rootView.findViewById(R.id.show_pic);
        tabPagerAdapter=new TabPagerAdapter(this.getParentFragmentManager(),getLifecycle(),fragmentList);
        viewPager2.setAdapter(tabPagerAdapter);
        viewPager2.setCurrentItem(0);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        setTextColor(R.id.pics);
                        setUnderLine(R.id.pics_line);
                        break;
                    case 1:
                        setTextColor(R.id.take_view);
                        setUnderLine(R.id.view_line);
                        break;
                    case 2:
                        setTextColor(R.id.take_photo);
                        setUnderLine(R.id.photo_line);
                        break;
                }
            }
        });
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

    private void setBackButton() {
        back_button=(Button)rootView.findViewById(R.id.publish_back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pics:
                setTextColor(R.id.pics);
                setUnderLine(R.id.pics_line);
                viewPager2.setCurrentItem(0);
                break;
            case R.id.take_view:
                setTextColor(R.id.take_view);
                setUnderLine(R.id.view_line);
                viewPager2.setCurrentItem(1);
                break;
            case R.id.take_photo:
                setTextColor(R.id.take_photo);
                setUnderLine(R.id.photo_line);
                viewPager2.setCurrentItem(2);
                break;
        }
    }
}
