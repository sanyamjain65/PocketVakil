package com.app.pocketvakil.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.pocketvakil.R;
import com.app.pocketvakil.asynctask.SignUpAsyncTask;
import com.app.pocketvakil.utils.AppUtils;

import java.util.HashMap;

/**
 * Created by appinventiv on 10/5/16.
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView logo;
    private Animation animTranslate;
    private EditText etNameText,etEmailText,etPasswordText;
    private View nameView,emailView,passView;
    private TextView tvLogin;
    private ImageView pwdshow;
    private RelativeLayout rel_showsignUppwd,relative_signUp_root,rel1;
    private int length=0;
    private String blank="";
    private boolean showPassword = false;
    private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private HashMap postDataParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);*/
        setContentView(R.layout.activity_sign_up);
        initView();
       //attachKeyboardListeners();





    }





   /* @Override
    protected void onShowKeyboard(int keyboardHeight) {
        // do things when keyboard is shown
        tvLogin.setVisibility(View.GONE);
    }

    @Override
    protected void onHideKeyboard() {
        // do things when keyboard is hidden
        tvLogin.setVisibility(View.VISIBLE);
    }*/




    private void initView(){
        logo = (ImageView) findViewById(R.id.iv_logo);
        etNameText = (EditText) findViewById(R.id.et_name);
        etEmailText = (EditText) findViewById(R.id.et_email_address);
        etPasswordText = (EditText) findViewById(R.id.et_password);
        nameView = (View) findViewById(R.id.iv_name_view);
        relative_signUp_root=(RelativeLayout)findViewById(R.id.relative_signUp_root);
        emailView = (View) findViewById(R.id.iv_email_view);
        rel_showsignUppwd=(RelativeLayout)findViewById(R.id.rel_showsignUppwd);
        pwdshow=(ImageView)findViewById(R.id.iv_pwdshow);
        passView = (View) findViewById(R.id.iv_pass_view);
        rel1=(RelativeLayout)findViewById(R.id.rel1);
        tvLogin = (TextView) findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(this);
        etPasswordText.setVisibility(View.VISIBLE);
        pwdshow.setVisibility(View.VISIBLE);
        etEmailText.setVisibility(View.VISIBLE);
        rel_showsignUppwd.setVisibility(View.GONE);
        etNameText.setVisibility(View.VISIBLE);
        nameView.setVisibility(View.VISIBLE);
        emailView.setVisibility(View.VISIBLE);
        passView.setVisibility(View.VISIBLE);
        tvLogin.setVisibility(View.VISIBLE);
        etPasswordText.addTextChangedListener(passwordWatcher);
        etEmailText.addTextChangedListener(emailWatcher);
        etNameText.addTextChangedListener(nameWatcher);
        setAnimation();
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loginLogo.startAnimation(animTranslate);

            }
        }, 500);*/

        /*etPasswordText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (etPasswordText.getRight() - etPasswordText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        if (showPassword == false) {
                            showPassword = true;
                            etPasswordText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_blue, 0);
                            etPasswordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            etPasswordText.setEllipsize(TextUtils.TruncateAt.END);
                        } else {
                            showPassword = false;
                            etPasswordText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eyeg_grey, 0);
                            etPasswordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            etPasswordText.setEllipsize(TextUtils.TruncateAt.END);
                        }

                        //Toast.makeText(LoginActivity.this,"Drawable clicked",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                return false;
            }
        });*/
        rel_showsignUppwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showPassword==false)
                {
                    showPassword = true;
                    pwdshow.setImageResource(R.drawable.eye_blue);
                    etPasswordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);



                    etPasswordText.setSelection(etPasswordText.length());
                }
                else
                {
                    showPassword = false;
                    pwdshow.setImageResource(R.drawable.eye_grey);
                    etPasswordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    etPasswordText.setSelection(etPasswordText.length());
                }
            }
        });

        etPasswordText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager inputManager = (InputMethodManager) SignUpActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(SignUpActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    String name=etNameText.getText().toString();
                    String mail=etEmailText.getText().toString();
                    String pwd=etPasswordText.getText().toString();


                    if(name.equals("")|| mail.equals("") || pwd.equals(""))
                    {

                        Snackbar snack = Snackbar.make(relative_signUp_root, "Enter all field", Snackbar.LENGTH_LONG);
                        View view = snack.getView();
                        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setTextColor(getResources().getColor(R.color.white));
                        snack.show();

                        //Toast.makeText(SignUpActivity.this,"Enter all field",Toast.LENGTH_SHORT).show();

                    }
                    if((!name.equals("") && !mail.equals("")&& !pwd.equals(""))&&(etPasswordText.getText().toString().length()<8))
                    {


                        Snackbar snack = Snackbar.make(relative_signUp_root, "Password must be 8 charecter", Snackbar.LENGTH_LONG);
                        View view = snack.getView();
                        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setTextColor(getResources().getColor(R.color.white));
                        snack.show();


                        //Toast.makeText(SignUpActivity.this,"Password must be 8 charecter",Toast.LENGTH_SHORT).show();
                    }

                    if(!name.equals("") && !mail.equals("") && !pwd.equals("") && etPasswordText.getText().toString().length()>=8 && mail.matches(EMAIL_PATTERN))
                    {
                        postDataParams = new HashMap<String, String>();
                        postDataParams.put("user_full_name", etNameText.getText().toString());
                        postDataParams.put("user_email", etEmailText.getText().toString());
                        postDataParams.put("user_password", etPasswordText.getText().toString());
                        SignUpAsyncTask task = new SignUpAsyncTask(SignUpActivity.this, postDataParams,blank);
                        task.execute();
                        return true;
                    }
                    if(!name.equals("") && pwd.length()>=8 && !mail.matches(EMAIL_PATTERN))
                    {

                        Snackbar snack = Snackbar.make(relative_signUp_root, "Please enter valid email address", Snackbar.LENGTH_LONG);
                        View view = snack.getView();
                        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setTextColor(getResources().getColor(R.color.white));
                        snack.show();


                        //Toast.makeText(SignUpActivity.this,"Please enter valid email address",Toast.LENGTH_SHORT).show();
                    }

                }
                return false;
            }
        });

    }


    private void setAnimation(){
        animTranslate  = AnimationUtils.loadAnimation(SignUpActivity.this, R.anim.translate);
        animTranslate.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) { }

            @Override
            public void onAnimationRepeat(Animation arg0) { }

            @Override
            public void onAnimationEnd(Animation arg0) {

                final AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
                animation1.setDuration(500);
                animation1.setStartOffset(0000);
                animation1.setFillAfter(true);
                etPasswordText.setVisibility(View.VISIBLE);
                etEmailText.setVisibility(View.VISIBLE);
                etNameText.setVisibility(View.VISIBLE);
                nameView.setVisibility(View.VISIBLE);
                pwdshow.setVisibility(View.VISIBLE);
                emailView.setVisibility(View.VISIBLE);
                passView.setVisibility(View.VISIBLE);
                tvLogin.setVisibility(View.VISIBLE);
                etPasswordText.setAnimation(animation1);
                etEmailText.setAnimation(animation1);
                etNameText.setAnimation(animation1);
                nameView.setAnimation(animation1);
                emailView.setAnimation(animation1);
                passView.setAnimation(animation1);
                tvLogin.setAnimation(animation1);

                //Toast.makeText(SplashActivity.this,"Hi",Toast.LENGTH_LONG).show();

            }
        });
    }


    private final TextWatcher passwordWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //textView.setVisibility(View.VISIBLE);
        }

        public void afterTextChanged(Editable s) {

            if(s.length()>=1)
            {
                rel_showsignUppwd.setVisibility(View.VISIBLE);
            }
            else
            {
                rel_showsignUppwd.setVisibility(View.GONE);
            }

            if (s.length() >= 8) {
                if(etEmailText.getText().toString().matches(EMAIL_PATTERN) && etNameText.getText().toString().length()>=1) {
                    int leftPadding = tvLogin.getPaddingLeft();
                    int rightPadding = tvLogin.getPaddingRight();
                    int topPadding = tvLogin.getPaddingTop();
                    int bottomPadding = tvLogin.getPaddingBottom();
                    tvLogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_blue_button));
                    tvLogin.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
                }else {
                    int leftPadding = tvLogin.getPaddingLeft();
                    int rightPadding = tvLogin.getPaddingRight();
                    int topPadding = tvLogin.getPaddingTop();
                    int bottomPadding = tvLogin.getPaddingBottom();
                    tvLogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_grey_button));
                    tvLogin.setPadding(leftPadding,topPadding,rightPadding,bottomPadding);
                }
                //textView.setVisibility(View.GONE);
            } else{
                int leftPadding = tvLogin.getPaddingLeft();
                int rightPadding = tvLogin.getPaddingRight();
                int topPadding = tvLogin.getPaddingTop();
                int bottomPadding = tvLogin.getPaddingBottom();
                tvLogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_grey_button));
                tvLogin.setPadding(leftPadding,topPadding,rightPadding,bottomPadding);
                //textView.setText("You have entered : " + passwordEditText.getText());
            }
        }
    };


    private final TextWatcher emailWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //textView.setVisibility(View.VISIBLE);
        }

        public void afterTextChanged(Editable s) {
            if (etPasswordText.getText().toString().length() >= 8 && etNameText.getText().toString().length()>=1) {

                if(etEmailText.getText().toString().matches(EMAIL_PATTERN)) {
                    int leftPadding = tvLogin.getPaddingLeft();
                    int rightPadding = tvLogin.getPaddingRight();
                    int topPadding = tvLogin.getPaddingTop();
                    int bottomPadding = tvLogin.getPaddingBottom();
                    tvLogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_blue_button));
                    tvLogin.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
                }else {
                    int leftPadding = tvLogin.getPaddingLeft();
                    int rightPadding = tvLogin.getPaddingRight();
                    int topPadding = tvLogin.getPaddingTop();
                    int bottomPadding = tvLogin.getPaddingBottom();
                    tvLogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_grey_button));
                    tvLogin.setPadding(leftPadding,topPadding,rightPadding,bottomPadding);
                }
                //textView.setVisibility(View.GONE);
            } else{
                int leftPadding = tvLogin.getPaddingLeft();
                int rightPadding = tvLogin.getPaddingRight();
                int topPadding = tvLogin.getPaddingTop();
                int bottomPadding = tvLogin.getPaddingBottom();
                tvLogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_grey_button));
                tvLogin.setPadding(leftPadding,topPadding,rightPadding,bottomPadding);

                //textView.setText("You have entered : " + passwordEditText.getText());
            }
        }
    };

    private final TextWatcher nameWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //textView.setVisibility(View.VISIBLE);
        }

        public void afterTextChanged(Editable s) {
            if (etPasswordText.getText().toString().length() >= 8 && etNameText.getText().toString().length()>=1) {

                if(etEmailText.getText().toString().matches(EMAIL_PATTERN)) {
                    int leftPadding = tvLogin.getPaddingLeft();
                    int rightPadding = tvLogin.getPaddingRight();
                    int topPadding = tvLogin.getPaddingTop();
                    int bottomPadding = tvLogin.getPaddingBottom();
                    tvLogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_blue_button));
                    tvLogin.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
                }else {
                    int leftPadding = tvLogin.getPaddingLeft();
                    int rightPadding = tvLogin.getPaddingRight();
                    int topPadding = tvLogin.getPaddingTop();
                    int bottomPadding = tvLogin.getPaddingBottom();
                    tvLogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_grey_button));
                    tvLogin.setPadding(leftPadding,topPadding,rightPadding,bottomPadding);
                }
                //textView.setVisibility(View.GONE);
            } else{
                int leftPadding = tvLogin.getPaddingLeft();
                int rightPadding = tvLogin.getPaddingRight();
                int topPadding = tvLogin.getPaddingTop();
                int bottomPadding = tvLogin.getPaddingBottom();
                tvLogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_grey_button));
                tvLogin.setPadding(leftPadding,topPadding,rightPadding,bottomPadding);

                //textView.setText("You have entered : " + passwordEditText.getText());
            }
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        AppUtils.hideKeyBoard(SignUpActivity.this);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_login:
            {
                    String name=etNameText.getText().toString();
                    String password=etPasswordText.getText().toString();
                    String mail=etEmailText.getText().toString();



               if(!etEmailText.getText().toString().matches(EMAIL_PATTERN) && !name.equals("") && !password.equals(""))
                {

                    Snackbar snack = Snackbar.make(relative_signUp_root, "Please enter valid email address", Snackbar.LENGTH_LONG);
                    View view = snack.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    snack.show();



                    //Toast.makeText(SignUpActivity.this,"Please enter valid email address",Toast.LENGTH_SHORT).show();
                }
                else if(etEmailText.getText().toString().length()>=4 && etPasswordText.getText().toString().length()<8)

                {

                    Snackbar snack = Snackbar.make(relative_signUp_root, "Password must be 8 character", Snackbar.LENGTH_LONG);
                    View view = snack.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    snack.show();


                    //Toast.makeText(SignUpActivity.this,"Password must be 8 character",Toast.LENGTH_SHORT).show();
                }
                else if (!mail.equals("")&& !password.equals("") && !name.equals(""))
                {

                    InputMethodManager inputManager = (InputMethodManager) SignUpActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(SignUpActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    postDataParams = new HashMap<String, String>();
                    postDataParams.put("user_full_name", etNameText.getText().toString());
                    postDataParams.put("user_email", etEmailText.getText().toString());
                    postDataParams.put("user_password", etPasswordText.getText().toString());
                    SignUpAsyncTask task = new SignUpAsyncTask(SignUpActivity.this, postDataParams,blank);
                    task.execute();
                }

                else
               {

                   Snackbar snack = Snackbar.make(relative_signUp_root, "Fill all field", Snackbar.LENGTH_LONG);
                   View view = snack.getView();
                   TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                   tv.setTextColor(getResources().getColor(R.color.white));
                   snack.show();


                  // Toast.makeText(SignUpActivity.this,"Fill all field",Toast.LENGTH_SHORT).show();
               }
            }
        }
    }

    public void onSuccessfulSignUp(String responseString) {

        Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
        intent.putExtra("toast",responseString);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
//        Toast.makeText(SignUpActivity.this,responseString,Toast.LENGTH_SHORT).show();
    }

    public void onSignUpFailed(String responseString) {

        Snackbar snack = Snackbar.make(relative_signUp_root, responseString, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(getResources().getColor(R.color.white));
        snack.show();
        //Toast.makeText(SignUpActivity.this,responseString,Toast.LENGTH_SHORT).show();

    }
}
