package com.app.pocketvakil.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.pocketvakil.R;
import com.app.pocketvakil.adapters.CaseTypeAdapter;
import com.app.pocketvakil.asynctask.FaqAsyncTask;
import com.app.pocketvakil.asynctask.LogOutAsyncTask;
import com.app.pocketvakil.asynctask.QuaryAsyncTask;
import com.app.pocketvakil.bean.FaqResponseBean;
import com.app.pocketvakil.utils.AppSharedPreference;
import com.app.pocketvakil.utils.AppUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by appinventiv on 10/5/16.
 */
public class QueryFormActivity extends AppCompatActivity  {

    private Spinner spinner;
    private CaseTypeAdapter caseTypeAdapter;
    private EditText et_query;
    private ImageView menu;
    private TextView tittle;
    private Toolbar toolbar;
    private TextView tv_logout, tv_faq;
    private RelativeLayout rel_img,relmain;
    private ScrollView scrollView_main;
    private TextView send;
    private RelativeLayout rel_quary;
    private EditText query_title;
    private String blank="";
    private HashMap postDataParams;
    private View another_field_view;
    private EditText another_field;
    private String another;
    private String switch_text;
    private CheckBox checkBox;
    private TextView tv_tc;
    private ImageView back;


   /* private String[] strings = {"Case Type","Commercial/Contract Law", "Constitutional Law",
            "Criminal Law", "Family Law", "Intellectual Property Rights", "International Law", "Matrimonial Dispute", "Property Law", "Tax Law", "Any other, please specify"};
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_form);
         String[] strings={getResources().getString(R.string.quary_Case_Type),
                getResources().getString(R.string.quary_Commercial_Contract_Law),
                getResources().getString(R.string.quary_Constitutional_Law),
                getResources().getString(R.string.quary_Criminal_Law),
                getResources().getString(R.string.quary_Family_Law),
                getResources().getString(R.string.quary_Intellectual_Property_Rights),
                getResources().getString(R.string.quary_International_Law),
                getResources().getString(R.string.quary_Matrimonial_Dispute),
                getResources().getString(R.string.quary_Property_Law),
                getResources().getString(R.string.quary_Tax_Law),
                getResources().getString(R.string.quary_another)};

        Intent intent=getIntent();
        if (intent!=null)
        {
            switch_text=intent.getStringExtra("switch");
        }


        initView(strings,switch_text);
    }

    private void initView(String[] strings, final String switch_text) {

        spinner = (Spinner) findViewById(R.id.spinner);
        et_query = (EditText) findViewById(R.id.et_query);
        rel_quary=(RelativeLayout)findViewById(R.id.rel_quary);
        et_query.setMovementMethod(new ScrollingMovementMethod());
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        back=(ImageView)toolbar.findViewById(R.id.toolbar_back);
        scrollView_main=(ScrollView)findViewById(R.id.scrollView_main);
        another_field=(EditText)findViewById(R.id.tv_another_field);
        another_field_view=(View)findViewById(R.id.another_field_view);
        query_title=(EditText)findViewById(R.id.tv_query_title);
        another_field.setVisibility(View.GONE);
        another_field_view.setVisibility(View.GONE);
        relmain=(RelativeLayout)findViewById(R.id.relmain);
        tv_tc=(TextView)findViewById(R.id.tv_tc);
        rel_img=(RelativeLayout)toolbar.findViewById(R.id.rel_img);
        tittle = (TextView) toolbar.findViewById(R.id.tv_tittle);
        menu = (ImageView) toolbar.findViewById(R.id.menu);
        send=(TextView)findViewById(R.id.tv_quary_send);
        tittle.setText("Pocket Vakil");
        back.setVisibility(View.GONE);
        tv_logout = (TextView) findViewById(R.id.tv_logout);
        tv_faq = (TextView) findViewById(R.id.tv_faq);
        caseTypeAdapter = new CaseTypeAdapter(QueryFormActivity.this, 1, strings);
        spinner.setAdapter(caseTypeAdapter);

        try {
            Field popup = Spinner .class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner);
/*
            Window window = popupWindow.getWindow();
            window.setLayout(1300,900);
*/

            // Set popupWindow height to 500px

            popupWindow.setHeight(1000);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }
        tv_tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QueryFormActivity.this,Terms_Condition_Activity.class);
                startActivity(intent);
                QueryFormActivity.this.finish();
            }
        });

        rel_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(QueryFormActivity.this);
                //setting custom layout to dialog
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.popup);
                WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
                //dialog.show();
                wmlp.gravity = Gravity.TOP | Gravity.RIGHT;

                TextView logout = (TextView) dialog.findViewById(R.id.tv_logout);
                final TextView faq = (TextView) dialog.findViewById(R.id.tv_faq);

                final TextView switch_hindi = (TextView) dialog.findViewById(R.id.tv_switch_to_hindi);
                if (switch_text == null) {
                    switch_hindi.setText("Switch to Hindi");


                } else if (switch_text.toString().equals("Switch to English")) {
                    switch_hindi.setText("Switch to English");
                } else if (switch_text.toString().equals("Switch to Hindi")) {
                    switch_hindi.setText("Switch to Hindi");
                }


                switch_hindi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (switch_hindi.getText().toString().equals("Switch to Hindi")) {
                            switchToHindi();
                            dialog.dismiss();
                        } else if (switch_hindi.getText().toString().equals("Switch to English")) {
                            switchToEnglish();
                            dialog.dismiss();
                        }

                    }
                });

                faq.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String eng=faq.getText().toString();

                        if(eng.equals("FAQs"))
                        {
                            FaqAsyncTask task = new FaqAsyncTask(QueryFormActivity.this,1);
                            task.execute();

                            dialog.dismiss();
                        }
                        else if(eng.equals("पूछे जाने वाले प्रश्न"))
                        {
                            FaqAsyncTask task = new FaqAsyncTask(QueryFormActivity.this,0);
                            task.execute();

                            dialog.dismiss();

                        }

                    }
                });

                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        postDataParams = new HashMap<String, String>();
                        postDataParams.put("user_id", String.valueOf(AppSharedPreference.getInt(QueryFormActivity.this, AppSharedPreference.PREF_KEY.UserId)));
                        LogOutAsyncTask task = new LogOutAsyncTask(QueryFormActivity.this, postDataParams, AppSharedPreference.getString(QueryFormActivity.this, AppSharedPreference.PREF_KEY.Access_token));
                        AppSharedPreference.clearAllPrefs(QueryFormActivity.this);
                        task.execute();

                        dialog.dismiss();


                    }
                });
                dialog.show();
                //Toast.makeText(QueryFormActivity.this,"test",Toast.LENGTH_LONG).show();
                /*final PopupWindow popupWindow = new PopupWindow(QueryFormActivity.this);
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.popup, null);

                TextView logout = (TextView) view.findViewById(R.id.tv_logout);
                TextView faq = (TextView) view.findViewById(R.id.tv_faq);
                faq.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FaqAsyncTask task = new FaqAsyncTask(QueryFormActivity.this);
                        task.execute();

                        popupWindow.dismiss();
                    }
                });
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        postDataParams = new HashMap<String, String>();
                        postDataParams.put("user_id", String.valueOf(AppSharedPreference.getInt(QueryFormActivity.this, AppSharedPreference.PREF_KEY.UserId)));
                        LogOutAsyncTask task = new LogOutAsyncTask(QueryFormActivity.this, postDataParams, AppSharedPreference.getString(QueryFormActivity.this, AppSharedPreference.PREF_KEY.Access_token));
                        AppSharedPreference.clearAllPrefs(QueryFormActivity.this);
                        task.execute();

                        popupWindow.dismiss();


                    }
                });
                //popupWindow.setFocusable(true);
                popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.pop_up_background));
                //popupWindow.setBackgroundDrawable((new ColorDrawable(Color.WHITE)));
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                popupWindow.setContentView(view);
                popupWindow.setElevation(10f);

                popupWindow.showAsDropDown(toolbar, 80, -120);*/
               /* PopupWindow popupwindow_obj = popupDisplay();
                popupwindow_obj.showAsDropDown(btnDismiss, -40, 18);*/

            }


        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                another = spinner.getItemAtPosition(position).toString();

                another = spinner.getSelectedItem().toString();

                if (position == 10) {
                    another_field.setVisibility(View.VISIBLE);
                    another_field_view.setVisibility(View.VISIBLE);
                } else if (position != 10) {
                    another_field.setVisibility(View.GONE);
                    another_field_view.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataParams = new HashMap<String, String>();
                String case_type=spinner.getSelectedItem().toString();
                String query_tittle=query_title.getText().toString();
                String question=et_query.getText().toString();
                int pos=spinner.getSelectedItemPosition();



                if( !case_type.equals("Case Type") && query_tittle!="" && question!="" && checkBox.isChecked())
                {





                    if(pos!=10)
                    {
                        postDataParams.put("case_type", spinner.getSelectedItem().toString());
                        postDataParams.put("query_title", query_title.getText().toString());
                        postDataParams.put("question",et_query.getText().toString());
                        QuaryAsyncTask task = new QuaryAsyncTask(QueryFormActivity.this, postDataParams, blank);
                        task.execute();
                    }
                    else if(pos==10)
                    {
                        String specify_field=another_field.getText().toString();
                        postDataParams.put("case_type", specify_field);
                        postDataParams.put("query_title", query_title.getText().toString());
                        postDataParams.put("question",et_query.getText().toString());
                        QuaryAsyncTask task = new QuaryAsyncTask(QueryFormActivity.this, postDataParams, blank);
                        task.execute();
                    }

                }
                else
                {
                    Snackbar snack = Snackbar.make(rel_quary, "Please enter all field", Snackbar.LENGTH_LONG);
                    View view = snack.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    snack.show();



                   // Toast.makeText(QueryFormActivity.this,"Please enter all field",Toast.LENGTH_SHORT).show();
                }

            }
        });

        /*another=spinner.getSelectedItem().toString();
        if(another.equals("Any other, please specify"))
        {
            another_field.setVisibility(View.VISIBLE);
            another_field_view.setVisibility(View.VISIBLE);
        }
        else
        {
            another_field.setVisibility(View.GONE);
            another_field_view.setVisibility(View.GONE);
        }

*/

    }

    public  void switchToHindi()
    {
        String languageToLoad = "hi"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        Intent intent=new Intent(QueryFormActivity.this,QueryFormActivity.class);
        intent.putExtra("switch","Switch to English");
        startActivity(intent);
        this.finish();

    }

    public  void switchToEnglish()
    {
        String languageToLoad = "en"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        Intent intent=new Intent(QueryFormActivity.this,QueryFormActivity.class);
        intent.putExtra("switch","Switch to Hindi");
        startActivity(intent);
        this.finish();

    }



    public void onSuccessfulLogOut(String responseString) {
        Intent intent = new Intent(QueryFormActivity.this, LoginActivity.class);
        this.finish();
        startActivity(intent);
    }

    public void onFAQSucess(FaqResponseBean faqResponseBean) {

        Intent intent=new Intent(QueryFormActivity.this,Faq_Question.class);
        intent.putExtra("faqlist", faqResponseBean);
        //intent.putStringArrayListExtra("ques", (ArrayList<String>) faqResponseBean.getFaqList());
        startActivity(intent);
    }

    public void onLoginFailed(String responseString) {

        Snackbar snack = Snackbar.make(rel_quary, responseString, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(getResources().getColor(R.color.white));
        snack.show();


       // Toast.makeText(QueryFormActivity.this,responseString,Toast.LENGTH_SHORT).show();
    }

    public void onSuccessfulSendQuary(String responseString) {
        Snackbar snack = Snackbar.make(rel_quary, responseString, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(getResources().getColor(R.color.white));
        snack.show();
       // Toast.makeText(QueryFormActivity.this,responseString,Toast.LENGTH_SHORT).show();

        spinner.setSelection(0);
        query_title.setText("");
        et_query.setText("");


    }

    public void onQuaryFailed(String responseString) {

        Snackbar snack = Snackbar.make(rel_quary, "please enter all field", Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(getResources().getColor(R.color.white));
        snack.show();

       // Toast.makeText(QueryFormActivity.this,"please enter all field",Toast.LENGTH_SHORT).show();
        query_title.setText("");
        et_query.setText("");

        spinner.setSelection(0);
    }

    public void onQuaryNetWorkFailed(String s) {

        Snackbar snack = Snackbar.make(rel_quary, s, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(getResources().getColor(R.color.white));
        snack.show();
       // Toast.makeText(QueryFormActivity.this,s,Toast.LENGTH_SHORT).show();

    }

    public void onFAQFail(String s) {

        Snackbar snack = Snackbar.make(rel_quary, s, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(getResources().getColor(R.color.white));
        snack.show();
        //Toast.makeText(QueryFormActivity.this,s,Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        AppUtils.hideKeyBoard(QueryFormActivity.this);
        return true;
    }


}
