package com.app.pocketvakil.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.pocketvakil.R;

public class FAQ_Question_Extend_Activity extends AppCompatActivity {
    private String question;
    private String answer;
    private TextView tv_question;
    private TextView tv_ans;
    private Toolbar toolbar;
    private TextView tittle;
    private ImageView menu;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq__question__extend_);
        Intent intent = getIntent();

        if(intent!=null)
        {
            question=intent.getStringExtra("question");
            answer=intent.getStringExtra("ans");
        }

        tv_ans=(TextView)findViewById(R.id.tv_ansExnd);
        tv_question=(TextView)findViewById(R.id.tv_quesExnd);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        tittle=(TextView)toolbar.findViewById(R.id.tv_tittle);
        back=(ImageView)toolbar.findViewById(R.id.toolbar_back);
        menu=(ImageView)toolbar.findViewById(R.id.menu);
        menu.setVisibility(View.INVISIBLE);
        back.setVisibility(View.GONE);
        tittle.setText("FAQ");
        tv_ans.setText(answer);
        tv_question.setText(question);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_faq__question__extend_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
