package com.app.pocketvakil.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.pocketvakil.R;

public class Terms_Condition_Activity extends AppCompatActivity {


    private Toolbar toolbar;
    private TextView title;
    private ImageView menu;
    private  ImageView back;
    private LinearLayout lin_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms__condition_);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        title=(TextView)toolbar.findViewById(R.id.tv_tittle);
        menu=(ImageView)toolbar.findViewById(R.id.menu);
        back=(ImageView)toolbar.findViewById(R.id.toolbar_back);
        lin_back=(LinearLayout)toolbar.findViewById(R.id.lin_back);
        title.setVisibility(View.GONE);
        menu.setVisibility(View.GONE);
        lin_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Terms_Condition_Activity.this,QueryFormActivity.class);
                startActivity(intent);
            }
        });


    }







}
