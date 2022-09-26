package com.example.timing.plant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.timing.R;
import com.example.timing.entity.Community;

public class Community_Page extends Activity {
    private Button back_button;
    private Community m_community;

    public Community_Page() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_page);
        // Inflate the layout for this fragment
        setData();
        initPage();
        setBackButton();
    }

    private void initPage() {
        TextView name = findViewById(R.id.name);
        TextView address = findViewById(R.id.address);
        name.setText(m_community.getM_name());
        address.setText("地址："+m_community.getM_address());
    }

    private void setData() {
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        m_community=(Community) bundle.getSerializable("community");
    }

    private void setBackButton() {
        back_button=(Button)findViewById(R.id.publish_back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
