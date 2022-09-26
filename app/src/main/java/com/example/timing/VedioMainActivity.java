package com.example.timing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.timing.adapter.ViewPagerAdapter;
import com.example.timing.entity.ShortVideoInfo;
import com.example.timing.holder.RecyclerItemNormalHolder;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VedioMainActivity extends AppCompatActivity {

    @BindView(R.id.view_pager2)
    ViewPager2 viewPager2;

    private List<ShortVideoInfo> mList = new ArrayList<>();
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_video);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {

        setData();
        viewPagerAdapter = new ViewPagerAdapter(this, mList);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager2.setAdapter(viewPagerAdapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // 大于0说明有播放
                int playPosition = GSYVideoManager.instance().getPlayPosition();
                if (playPosition >= 0) {
                    // 对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(RecyclerItemNormalHolder.TAG)
                            && (position != playPosition)) {
                        playPosition(position);
                    }
                }
            }
        });
        viewPager2.post(new Runnable() {
            @Override
            public void run() {
                playPosition(0);
            }
        });

    }

    private void playPosition(int position) {
        RecyclerView.ViewHolder viewHolder = ((RecyclerView) viewPager2.getChildAt(0)).findViewHolderForAdapterPosition(position);
        if (viewHolder != null) {
            RecyclerItemNormalHolder recyclerItemNormalHolder = (RecyclerItemNormalHolder) viewHolder;
            recyclerItemNormalHolder.getPlayer().startPlayLogic();
        }
    }

    /**
     * 模拟数据
     */
    private void setData() {

        ShortVideoInfo data1 = new ShortVideoInfo();
        data1.setTextContent("已披星戴月援沪13次！江苏医护返程远远打卡迪士尼：希望下次来上海不用穿大白");
        data1.setVideoCover("https://mz.eastday.com/60918758.jpg");
        data1.setVideoUrl("https://mz.eastday.com/60918759.mp4");
        mList.add(data1);

        ShortVideoInfo data2 = new ShortVideoInfo();
        data2.setTextContent("上海市黄浦区2名干部因疫情防控履职不力被问责");
        data2.setVideoCover("https://mz.eastday.com/60918756.jpg");
        data2.setVideoUrl("https://mz.eastday.com/60918757.mp4");
        mList.add(data2);

        ShortVideoInfo data3 = new ShortVideoInfo();
        data3.setTextContent("90岁阿婆直播连线哽咽求助，居委会：多位工作人员确诊阳性，尽能力帮助老人");
        data3.setVideoCover("https://mz.eastday.com/60910522.jpghttps://mz.eastday.com/60910522.jpg");
        data3.setVideoUrl("https://mz.eastday.com/60910523.mp4");
        mList.add(data3);

        ShortVideoInfo data4 = new ShortVideoInfo();
        data4.setTextContent("重磅微视频丨我们正青春");
        data4.setVideoCover("https://nimg.ws.126.net/?url=http%3A%2F%2Fcms-bucket.ws.126.net%2F2022%2F0503%2Fac32b904j00rbb2xs001kc000m800cic.jpg&thumbnail=660x2147483647&quality=80&type=jpg");
        data4.setVideoUrl("http://flv.bn.netease.com/0e6a0335ec8457cb0310fa69a6ff77569a3e9d463d6a2fa50483d4b9d11ba3a31169ddfd2715b7136b4fa9804ad006b90b5f8dd75ccd05b8c8966325be14f7e19502f1f581935366e05a44c21aafcca9cfd20c544b49c4f8f908b1ba92c4605c6f3c60ebf568e7ecd7b0011ac8bdaa9e093d92042f311a1d.mp4");
        mList.add(data4);

        ShortVideoInfo data5 = new ShortVideoInfo();
        data5.setTextContent("湖南长沙居民自建房倒塌事故第9名被困人员被救出");
        data5.setVideoCover("http://tv.people.com.cn/NMediaFile/2022/0503/MAIN202205031341276075907623590.jpg");
        data5.setVideoUrl("http://flv3.people.com.cn/dev1/mvideo/vodfiles/2022/05/03/d2b20ce9e6f20b1cb69609e95c66214d_c.mp4");
        mList.add(data5);

    }

}