package com.example.timing;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.timing.account.AccountFragment;
import com.example.timing.home.HomeFragment;
import com.example.timing.message.MessageFragment;
import com.example.timing.plant.CycleFragment;
import com.example.timing.publish.SelectPhoto;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AccountFragment.SendBridge{
    private Button bHome,bCycle,bAdd,bMessage,bAccount;
    private List<Button> buttonList = new ArrayList<>();
    private TextView tvHome,tvCycle,tvAdd,tvMessage,tvAccount;
    private List<TextView> textViewList = new ArrayList<>();
    private int pic_press[];
    private int pic_release[];
    private FragmentTransaction fragmentTransaction;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addPicList();
        initTabNavigation();
        initFragment();
        initLeftNavigation();
    }

    private void initLeftNavigation() {
        drawerLayout=findViewById(R.id.left_navigation);
        navigationView=findViewById(R.id.navigation_view);
    }

    @Override
    public void openLeftNavigation() {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    private void addPicList(){
        pic_press = new int[]{R.drawable.home_press,R.drawable.cycle_press,-1,R.drawable.message_press,R.drawable.me_press};
        pic_release = new int[]{R.drawable.home_release,R.drawable.cycle_release,-1,R.drawable.message_release,R.drawable.me_release};
    }

    private void initTabNavigation() {
        bHome=(Button) this.findViewById(R.id.icon_home);
        bCycle=(Button) this.findViewById(R.id.icon_cycle);
        bAdd=(Button) this.findViewById(R.id.icon_add);
        bMessage=(Button) this.findViewById(R.id.icon_message);
        bAccount=(Button) this.findViewById(R.id.icon_account);

        bHome.setOnClickListener(this);
        bCycle.setOnClickListener(this);
        bAdd.setOnClickListener(this);
        bMessage.setOnClickListener(this);
        bAccount.setOnClickListener(this);

        buttonList.add(bHome);
        buttonList.add(bCycle);
        buttonList.add(bAdd);
        buttonList.add(bMessage);
        buttonList.add(bAccount);

        tvHome=(TextView) this.findViewById(R.id.text_home);
        tvCycle=(TextView) this.findViewById(R.id.text_cycle);
        tvAdd=(TextView) this.findViewById(R.id.text_add);
        tvMessage=(TextView) this.findViewById(R.id.text_message);
        tvAccount=(TextView) this.findViewById(R.id.text_account);

        textViewList.add(tvHome);
        textViewList.add(tvCycle);
        textViewList.add(tvAdd);
        textViewList.add(tvMessage);
        textViewList.add(tvAccount);
    }

    private void initFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        buttonList.get(0).setBackgroundResource(pic_press[0]);
        TextView textView=findViewById(R.id.text_home);
        textView.setTextColor(Color.parseColor("#515151"));
        fragmentTransaction.replace(R.id.pages, new HomeFragment());
        fragmentTransaction.commit();
    }

    private void setButtonBackgroundPic(int buttonId){
        int i=0;
        for (Button b:buttonList){
            if(b.getId()==buttonId && i!=2){
                b.setBackgroundResource(pic_press[i]);
            }else if (b.getId()!=buttonId && i!=2){
                b.setBackgroundResource(pic_release[i]);
            }
            i+=1;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.icon_home:
                setButtonBackgroundPic(R.id.icon_home);
                changeTabTextSize(R.id.text_home);
                fragmentTransaction.replace(R.id.pages,new HomeFragment());
                break;
            case R.id.icon_cycle:
                setButtonBackgroundPic(R.id.icon_cycle);
                changeTabTextSize(R.id.text_cycle);
                fragmentTransaction.replace(R.id.pages,new CycleFragment());
                break;
            case R.id.icon_add:
//                openCamara();
                Intent intent= new Intent(MainActivity.this, SelectPhoto.class);
                startActivity(intent);
                break;
            case R.id.icon_message:
                setButtonBackgroundPic(R.id.icon_message);
                changeTabTextSize(R.id.text_message);
                fragmentTransaction.replace(R.id.pages,new MessageFragment());
                break;
            case R.id.icon_account:
                setButtonBackgroundPic(R.id.icon_account);
                changeTabTextSize(R.id.text_account);
                fragmentTransaction.replace(R.id.pages,new AccountFragment());
                break;
        }
        fragmentTransaction.commit();
    }
//
//    private void openCamara() {
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case 100:
//                boolean writeExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                boolean readExternalStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//                Log.e("MainActivity", Arrays.toString(grantResults));
//                if (grantResults.length > 0 && writeExternalStorage && readExternalStorage) {
//                    getImage();
//                } else {
//                    Toast.makeText(this, "请设置必要权限", Toast.LENGTH_SHORT).show();
//                }
//
//                break;
//        }
//    }
//
//    private void getImage() {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
//            startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"),
//                    REQUEST_PICK_IMAGE);
//        } else {
//            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
//            intent.setType("image/*");
//            startActivityForResult(intent, REQUEST_PICK_IMAGE);
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            switch (requestCode) {
//                case REQUEST_PICK_IMAGE:
//                    if (data != null) {
//                        String realPathFromUri = RealPathFromUriUtils.getRealPathFromUri(this, data.getData());
//                        Log.e("MainActivity", realPathFromUri);
//                        showImg(realPathFromUri);
//                    } else {
//                        Toast.makeText(this, "图片损坏，请重新选择", Toast.LENGTH_SHORT).show();
//                    }
//
//                    break;
//            }
//        }
//    }
//
//
//    public void openCamera(View view) {
//        ActivityCompat.requestPermissions(MainActivity.this, mPermissionList, 100);
//    }
//
//
//    public void showImg(String path){
//        Bitmap bitmap = BitmapFactory.decodeFile(path);
//        mShowImg.setImageBitmap(bitmap);
//    }

    private void changeTabTextSize(int textId){
        int i=0;
        for (TextView t: textViewList){
            if(t.getId()==textId && i!=2){
                t.setTextColor(Color.parseColor("#515151"));
            }else if (t.getId()!=textId && i!=2){
                t.setTextColor(Color.parseColor("#DCDCDC"));
            }
            i+=1;
        }
    }
}