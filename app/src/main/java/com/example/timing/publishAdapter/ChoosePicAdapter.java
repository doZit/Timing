package com.example.timing.publishAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timing.R;
import com.example.timing.entity.Photo;
import com.example.timing.entity.Video;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChoosePicAdapter extends RecyclerView.Adapter<ChoosePicAdapter.ViewHolder> {
    private Context m_context;
    private static List<Photo> m_imageList=new ArrayList<>();
    private static List<Video> m_videoList=new ArrayList<>();
    private static List<TextView> checkedList=new ArrayList<>();

    private static List<Photo> m_selectedImageList=new ArrayList<>();
    public List<Photo> getM_selectedImageList() {
        return m_selectedImageList;
    }

    private int selectMaxImages;
    public void setSelectMaxImages(int selectMaxImages) {
        this.selectMaxImages = selectMaxImages;
    }

    private int image_size=0;

    public void setImage_size(int image_size) {
        this.image_size = image_size;
    }

    private String showTag;

    public void setShowTag(String showTag) {
        this.showTag = showTag;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView check;
        private View cover;
        //        private ImageButton headPic;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.pic);
            check=(TextView) itemView.findViewById(R.id.select_num);
            cover=(View)itemView.findViewById(R.id.image_cover);
        }
    }

    public ChoosePicAdapter(Context context){
        m_context=context;
    }

    public ChoosePicAdapter(Context context,List<Photo> imageList,List<Video> videoList){
        m_context=context;
        m_imageList=imageList;
        m_videoList=videoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(m_context).inflate(R.layout.choose_pic_item,parent,false);
        release();
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        Glide.with(holder.imageView.getContext()).load(m_imageList.get(position).getPath()).into(holder.imageView);
//        holder.imageView.setImageURI(Uri.fromFile(new File(m_videoList.get(position).getThumbPath())));
//        holder.imageView.setImageBitmap(getImageThumbnail(m_videoList.get(position).getPath(),image_size,image_size));

        if(!m_imageList.isEmpty() && m_videoList.isEmpty()){
            holder.imageView.setImageBitmap(getImageThumbnail(m_imageList.get(position).getPath(),image_size,image_size));
        }else if(m_imageList.isEmpty() && !m_videoList.isEmpty()){

        }else{
            holder.imageView.setImageBitmap(getImageThumbnail(m_imageList.get(position).getPath(),image_size,image_size));
        }
        if (m_imageList.get(position).isSelected()) {
            holder.check.setText(""+(m_selectedImageList.indexOf(m_imageList.get(position))+1));
            holder.check.setBackgroundResource(R.drawable.select_number);

        } else {
            holder.check.setText("");
            holder.check.setBackgroundResource(R.drawable.select);
        }
        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_imageList.get(position).isSelected()){
                    if(m_selectedImageList.size()>=selectMaxImages){
                        Toast.makeText(holder.check.getContext(), "你最多只能选择" + selectMaxImages + "个图片或视频", Toast.LENGTH_SHORT).show();
                        // 结束
                        return;
                    }
                    m_imageList.get(position).setSelected(true);
                    m_selectedImageList.add(m_imageList.get(position));
                    holder.check.setText(""+(m_selectedImageList.indexOf(m_imageList.get(position))+1));
                    holder.check.setBackgroundResource(R.drawable.select_number);
                    holder.cover.setVisibility(View.VISIBLE);
                    checkedList.add(holder.check);
                    if (mItemSelectChanged!=null){
                        mItemSelectChanged.changeSelectedCount(m_selectedImageList);
                    }
                }else {
                    holder.check.setText("");
                    holder.check.setBackgroundResource(R.drawable.select);
                    holder.cover.setVisibility(View.GONE);
                    Iterator<TextView> iterator = checkedList.iterator();
                    while (iterator.hasNext()) {
                        if (iterator.next().getText().toString().equals(holder.check.getText().toString())) {
                            if (!checkedList.isEmpty()) {
                                iterator.remove();
                                m_selectedImageList.remove(m_imageList.get(position));
                                m_imageList.get(position).setSelected(false);
                                if (!checkedList.isEmpty()) {
                                    ChoosePicAdapter.reSequence();
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    private static void reSequence(){
        for(int i=1;i<=checkedList.size();i++){
            checkedList.get(i-1).setText(""+i);
        }
    }

    @Override
    public int getItemCount() {
        return m_imageList.size();
    }

    private OnItemSelectChanged mItemSelectChanged;
    // 暴露接口给外部
    public void setOnItemSelectChanged(OnItemSelectChanged itemSelectChanged) {
        this.mItemSelectChanged = itemSelectChanged;
    }

    public void release() {
        m_selectedImageList.clear();
        checkedList.clear();
    }

    public interface OnItemSelectChanged {
        void changeSelectedCount(List<Photo> selectedImageList);
    }

    public void setData(List<Photo> imageList) {
        m_imageList.clear();
        m_imageList.addAll(imageList);
        notifyDataSetChanged();
    }

    private Bitmap getImageThumbnail(String imagePath, int width, int height) {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高，注意此处的bitmap为null
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        options.inJustDecodeBounds = false; // 设为 false
        // 计算缩放比
        int h = options.outHeight;
        int w = options.outWidth;
        int beWidth = w / width;
        int beHeight = h / height;
        int be = 1;
        if (beWidth < beHeight) {
            be = beWidth;
        } else {
            be = beHeight;
        }
        if (be <= 0) {
            be = 1;
        }
        options.inSampleSize = be;
        // 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        // 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }

    public static Bitmap getVideoThumbnail(String filePath, int width_, int height_) {
        Bitmap bitmap = null;
        try {
            bitmap = ThumbnailUtils.createVideoThumbnail(filePath,MediaStore.Video.Thumbnails.MICRO_KIND);
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width_, height_,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (bitmap == null) return null;
        return bitmap;
    }
}
