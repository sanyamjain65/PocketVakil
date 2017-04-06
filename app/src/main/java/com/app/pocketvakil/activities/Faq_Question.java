package com.app.pocketvakil.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.pocketvakil.R;
import com.app.pocketvakil.adapters.ListViewCustomAdapter;
import com.app.pocketvakil.bean.FaqResponseBean;

public class Faq_Question extends AppCompatActivity {
    private ListView ques;
    private TextView tittle;
    private ImageView menu;
    private ImageView back;
    private FaqResponseBean faqResponseBean;
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq__question);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        back=(ImageView)toolbar.findViewById(R.id.toolbar_back);
        tittle=(TextView)toolbar.findViewById(R.id.tv_tittle);
        menu=(ImageView)toolbar.findViewById(R.id.menu);
        menu.setVisibility(View.INVISIBLE);
        tittle.setText("FAQ");
        back.setVisibility(View.GONE);

        Intent intent = getIntent();

        if (null != intent) {
            faqResponseBean=intent.getParcelableExtra("faqlist");



        }
        ques = (ListView) findViewById(R.id.lt_ques);
        ListViewCustomAdapter adapter = new ListViewCustomAdapter(Faq_Question.this,faqResponseBean.getFaqList());
        ques.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_faq__question, menu);
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
