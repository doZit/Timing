package com.example.timing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.timing.entity.ShowCard;

public class Detail_Card extends Activity {
    private Button back_button;
    private ShowCard m_showCard;

    public Detail_Card() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_detail);
        // Inflate the layout for this fragment
        setData();
        initCard();
        setBackButton();
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

    private void setData() {
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        m_showCard=(ShowCard) bundle.getSerializable("showcard");
    }

    private void initCard() {
        TextView name = findViewById(R.id.nickname);
        TextView content=findViewById(R.id.text_content);
        name.setText(m_showCard.getM_name());
        content.setText(m_showCard.getM_content());
    }
}
