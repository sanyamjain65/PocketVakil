package com.app.pocketvakil.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
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
import com.app.pocketvakil.asynctask.LoginAsyncTask;
import com.app.pocketvakil.utils.AppUtils;

import java.util.HashMap;

/**
 * Created by appinventiv on 10/5/16.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etPassword, etEmail;
    private TextView forgotPassword, signUp, signIn, signUpText;
    private boolean showPassword = false;
    private Animation animTranslate;
    private ImageView loginLogo;
    private TextView tv_ok,tv_loginMsg;
    private ImageView pwdshow;
    private RelativeLayout relshowloginpwd,rel_login;
    private String blank = "";
//    private MyLayout relativeLayout,rl_sign_up,relMyLogo;
    private String toastMsg;
    private View emailView, passwordView, forgotPasswordView, signUpView;
    private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private HashMap postDataParams;
    private float   mail_Y_axis;
    private float   mail_view_axis;
    private float   pwd_axis;
    private float pwd_view_axis;
    private int check=0;
    private boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = getIntent();
        if (intent != null)

        {
            toastMsg = intent.getStringExtra("toast");

        }
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE| WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        initView();

    }

    private void initView() {

        etPassword = (EditText) findViewById(R.id.et_password);
        forgotPasswordView = (View) findViewById(R.id.iv_view);
        signUpView = (View) findViewById(R.id.sign_up_view);
        etEmail = (EditText) findViewById(R.id.et_email);
        signUpText = (TextView) findViewById(R.id.tv_sign_up_text);
        relshowloginpwd=(RelativeLayout)findViewById(R.id.relshowloginpwd);
        pwdshow=(ImageView)findViewById(R.id.iv_pwdshow);
        signIn = (TextView) findViewById(R.id.bt_login);
        rel_login=(RelativeLayout)findViewById(R.id.rel_login);
//        relMyLogo=(MyLayout)findViewById(R.id.relMyLogo);
//        relativeLayout=(MyLayout)findViewById(R.id.rel);
//        rl_sign_up=(MyLayout)findViewById(R.id.rl_sign_up);
        forgotPassword = (TextView) findViewById(R.id.tv_forgot_password);
        signUp = (TextView) findViewById(R.id.tv_sign_up);
        loginLogo = (ImageView) findViewById(R.id.iv_login_image);
        emailView = (View) findViewById(R.id.iv_email_view);
        passwordView = (View) findViewById(R.id.iv_pass_view);
        passwordView.setVisibility(View.GONE);
        pwdshow.setVisibility(View.GONE);
        etPassword.setVisibility(View.GONE);
        pwdshow.setVisibility(View.GONE);
        relshowloginpwd.setVisibility(View.GONE);
        etEmail.setVisibility(View.GONE);
        signUp.setVisibility(View.GONE);
        signIn.setVisibility(View.GONE);
        forgotPassword.setVisibility(View.GONE);
        emailView.setVisibility(View.GONE);
        signUpText.setVisibility(View.GONE);
        forgotPasswordView.setVisibility(View.GONE);
        signUpView.setVisibility(View.GONE);
        /* Set Text Watcher listener */
        etPassword.addTextChangedListener(passwordWatcher);
        etEmail.addTextChangedListener(emailWatcher);

        setAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loginLogo.startAnimation(animTranslate);

            }
        }, 500);

        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        relshowloginpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showPassword==false)
                {
                    showPassword = true;
                    pwdshow.setImageResource(R.drawable.eye_blue);
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    etPassword.setSelection(etPassword.length());
                }
                else
                {
                    showPassword = false;
                    pwdshow.setImageResource(R.drawable.eye_grey);
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    etPassword.setSelection(etPassword.length());
                }
            }
        });

        /*etPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (etPassword.getRight() - etPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        if (showPassword == false) {
                            showPassword = true;
                            etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_blue, 0);
                            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        } else {
                            showPassword = false;
                            etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eyeg_grey, 0);
                            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        }

                        //Toast.makeText(LoginActivity.this,"Drawable clicked",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                return false;
            }
        });*/
        /*relativeLayout.setOnSoftKeyboardListener(new MyLayout.OnSoftKeyboardListener() {



            @Override
            public void onShown() {
                loginLogo.setVisibility(View.GONE);
                relMyLogo.setVisibility(View.GONE);
                 mail_Y_axis=etEmail.getY();
                float set_mail_axis=mail_Y_axis+200;
                etEmail.setY(set_mail_axis);

                 mail_view_axis=emailView.getY();
                float set_mail_view_axis=mail_view_axis+200;
                emailView.setY(set_mail_view_axis);

                 pwd_axis=etPassword.getY();
                float set_pwd_axis=pwd_axis+200;
                etPassword.setY(set_pwd_axis);

                  pwd_view_axis=passwordView.getY();
                float set_pwd_view_axis=pwd_view_axis+200;
                passwordView.setY(set_pwd_view_axis);



            }

            @Override
            public void onHidden() {
                loginLogo.setVisibility(View.VISIBLE);
                relMyLogo.setVisibility(View.VISIBLE);
                etEmail.setY(mail_Y_axis);
                emailView.setY(mail_view_axis);
                etPassword.setY(pwd_axis);
                passwordView.setY(pwd_view_axis);


            }
        });*/
        etPassword.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager inputManager = (InputMethodManager) LoginActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(LoginActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    String mail=etEmail.getText().toString();
                    String pwd=etPassword.getText().toString();
                    if(!mail.matches(EMAIL_PATTERN)&& pwd.length()>=8)
                    {

                        Snackbar snack = Snackbar.make(rel_login, "Please enter valid email address", Snackbar.LENGTH_LONG);
                        View view = snack.getView();
                        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setTextColor(getResources().getColor(R.color.white));
                        snack.show();
                        //Toast.makeText(LoginActivity.this,"Please enter valid email address",Toast.LENGTH_SHORT).show();
                    }
                    if(pwd.length()<8 && mail.matches(EMAIL_PATTERN))
                    {

                        Snackbar snack = Snackbar.make(rel_login, "Password must be 8 charecter", Snackbar.LENGTH_LONG);
                        View view = snack.getView();
                        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setTextColor(getResources().getColor(R.color.white));
                        snack.show();

                       // Toast.makeText(LoginActivity.this,"Password must be 8 charecter",Toast.LENGTH_SHORT).show();
                    }

                    if(!mail.matches(EMAIL_PATTERN)&& pwd.length()<8)
                    {

                        Snackbar snack = Snackbar.make(rel_login, "Invalid Email or password", Snackbar.LENGTH_LONG);
                        View view = snack.getView();
                        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setTextColor(getResources().getColor(R.color.white));
                        snack.show();



                       // Toast.makeText(LoginActivity.this,"Invalid Email or password",Toast.LENGTH_SHORT).show();
                    }

                    if(!mail.equals("")&& !pwd.equals("") && mail.matches(EMAIL_PATTERN) && pwd.length()>=8)
                    {
                        postDataParams = new HashMap<String, String>();
                        postDataParams.put("user_email", etEmail.getText().toString());
                        postDataParams.put("user_password", etPassword.getText().toString());
                        LoginAsyncTask task = new LoginAsyncTask(LoginActivity.this, postDataParams, blank);
                        task.execute();
                        return true;
                    }




                }
                return false;
            }
        });

        /*rel.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = rel.getRootView().getHeight() - rel.getHeight();
                if (heightDiff > 80) { // if more than 100 pixels, its probably a keyboard...
                    Toast.makeText(LoginActivity.this, "check", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Close key",Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        /*etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Toast.makeText(getApplicationContext(), "got the focus", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "lost the focus", Toast.LENGTH_LONG).show();
                }
            }
        });
*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        AppUtils.hideKeyBoard(LoginActivity.this);
        return true;
    }

    @Override
    public void onClick(View v) {

        if (v.equals(forgotPassword)) {


            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        } else if (v.equals(signUp)) {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        } else if (v.equals(signIn)) {
            callLoginWebService(v);
        }

    }

    private void callLoginWebService(View view) {

        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        if (email == null || email.equalsIgnoreCase("")) {

            AppUtils.showSnackBar(view, "Please enter email address");
            return;
        }
        if (!email.matches(EMAIL_PATTERN)) {
            AppUtils.showSnackBar(view, "Please enter valid email address");
            return;
        }
        if (password == null || password.equalsIgnoreCase("")) {
            AppUtils.showSnackBar(view, "Please enter password");
            return;
        }
        if(check==0)
        {
            AppUtils.showSnackBar(view, "Password must be 8 charecter");
            return;
        }
        InputMethodManager inputManager = (InputMethodManager) LoginActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(LoginActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        postDataParams = new HashMap<String, String>();
        postDataParams.put("user_email", etEmail.getText().toString());
        postDataParams.put("user_password", etPassword.getText().toString());
        LoginAsyncTask task = new LoginAsyncTask(LoginActivity.this, postDataParams, blank);
        task.execute();

//        new GetData(LoginActivity.this).execute();
       /* Intent intent = new Intent(LoginActivity.this,QueryFormActivity.class);
        startActivity(intent);*/
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        etPassword.setText("");
        etEmail.setText("");

    }

    private void setAnimation() {
        animTranslate = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.translate);
        animTranslate.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {

                final AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
                animation1.setDuration(500);
                animation1.setStartOffset(0000);
                animation1.setFillAfter(true);
                signUpText.setVisibility(View.VISIBLE);
                passwordView.setVisibility(View.VISIBLE);
                etPassword.setVisibility(View.VISIBLE);
                etEmail.setVisibility(View.VISIBLE);
                signUp.setVisibility(View.VISIBLE);
                pwdshow.setVisibility(View.VISIBLE);

                signIn.setVisibility(View.VISIBLE);
                forgotPassword.setVisibility(View.VISIBLE);
                emailView.setVisibility(View.VISIBLE);
                forgotPasswordView.setVisibility(View.VISIBLE);
                signUpView.setVisibility(View.VISIBLE);
                etPassword.setAnimation(animation1);
                etEmail.setAnimation(animation1);
                signUp.setAnimation(animation1);
                signIn.setAnimation(animation1);
                forgotPassword.setAnimation(animation1);
                emailView.setAnimation(animation1);
                passwordView.setAnimation(animation1);
                signUpText.setAnimation(animation1);
                signUpView.setAnimation(animation1);
                forgotPasswordView.setAnimation(animation1);
                //Toast.makeText(SplashActivity.this,"Hi",Toast.LENGTH_LONG).show();
                if (toastMsg != null) {

                    Snackbar snack = Snackbar.make(rel_login, toastMsg, Snackbar.LENGTH_LONG);
                    View view = snack.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    snack.show();

                   // Toast.makeText(LoginActivity.this, toastMsg, Toast.LENGTH_SHORT).show();

                }

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
                relshowloginpwd.setVisibility(View.VISIBLE);
            }
            else
            {
                relshowloginpwd.setVisibility(View.GONE);
            }

            if (s.length() >= 8) {

                if (etEmail.getText().toString().matches(EMAIL_PATTERN)) {

                    int leftPadding = signIn.getPaddingLeft();
                    int rightPadding = signIn.getPaddingRight();
                    int topPadding = signIn.getPaddingTop();
                    int bottomPadding = signIn.getPaddingBottom();
                    signIn.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_blue_button));
                    signIn.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
                    check=1;
                } else {

                    int leftPadding = signIn.getPaddingLeft();
                    int rightPadding = signIn.getPaddingRight();
                    int topPadding = signIn.getPaddingTop();
                    int bottomPadding = signIn.getPaddingBottom();
                    signIn.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_grey_button));
                    signIn.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
                    check=0;
                }
                //textView.setVisibility(View.GONE);
            } else {

                int leftPadding = signIn.getPaddingLeft();
                int rightPadding = signIn.getPaddingRight();
                int topPadding = signIn.getPaddingTop();
                int bottomPadding = signIn.getPaddingBottom();
                signIn.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_grey_button));
                signIn.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
                check=0;

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
            if (etPassword.getText().toString().length() >= 8) {

                if (etEmail.getText().toString().matches(EMAIL_PATTERN)) {
                    int leftPadding = signIn.getPaddingLeft();
                    int rightPadding = signIn.getPaddingRight();
                    int topPadding = signIn.getPaddingTop();
                    int bottomPadding = signIn.getPaddingBottom();
                    signIn.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_blue_button));
                    signIn.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
                    check=1;
                } else {
                    int leftPadding = signIn.getPaddingLeft();
                    int rightPadding = signIn.getPaddingRight();
                    int topPadding = signIn.getPaddingTop();
                    int bottomPadding = signIn.getPaddingBottom();
                    signIn.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_grey_button));
                    signIn.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
                    check=0;
                }
                //textView.setVisibility(View.GONE);
            } else {
                int leftPadding = signIn.getPaddingLeft();
                int rightPadding = signIn.getPaddingRight();
                int topPadding = signIn.getPaddingTop();
                int bottomPadding = signIn.getPaddingBottom();
                signIn.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_grey_button));
                signIn.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
                check=0;

                //textView.setText("You have entered : " + passwordEditText.getText());
            }
        }
    };

    public void onSuccessfulLogin(String responseString) {

        Intent intent = new Intent(this, QueryFormActivity.class);
        startActivity(intent);
        this.finish();

    }


    public void onLoginFailed(String responseString) {

        etPassword.setText("");
        etEmail.setText("");
        final Dialog dialog = new Dialog(LoginActivity.this);
        //setting custom layout to dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.login_dialog);
        //dialog.show();
        tv_ok=(TextView)dialog.findViewById(R.id.tv_ok);
        tv_loginMsg=(TextView)dialog.findViewById(R.id.tv_loginMsg);
        tv_loginMsg.setText("This email is not registered with us.\n please enter correct email.");

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        Snackbar snack = Snackbar.make(rel_login, "Please click back again to exit", Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(getResources().getColor(R.color.white));
        snack.show();

        //Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 1000);
    }

  public void onLoginNetWorkFailed(String s) {


      Snackbar snack = Snackbar.make(rel_login, s, Snackbar.LENGTH_LONG);
      View view = snack.getView();
      TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
      tv.setTextColor(getResources().getColor(R.color.white));
      snack.show();

      //Toast.makeText(LoginActivity.this,s,  Toast.LENGTH_SHORT).show();
   }
//
//
//    @ve rid
//    public void onShown() {
//
//    }
//
//    @Oerride
//    public void onHidden() {
//
//    }
}
