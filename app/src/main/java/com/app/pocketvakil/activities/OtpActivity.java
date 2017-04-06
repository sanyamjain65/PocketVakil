package com.app.pocketvakil.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
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
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.pocketvakil.R;
import com.app.pocketvakil.asynctask.OtpAsyncTask;
import com.app.pocketvakil.asynctask.ResetPasswordAsyncTask;
import com.app.pocketvakil.bean.ForgotPasswordReponseBean;
import com.app.pocketvakil.utils.AppSharedPreference;
import com.app.pocketvakil.utils.AppUtils;

import java.util.HashMap;

/**
 * Created by appinventiv on 12/5/16.
 */
public class OtpActivity extends AppCompatActivity {

    private EditText etOtpOne, etOtpTwo, etOtpThree, etOtpFour;
    private TextView otpText, confirmPassword;
    private EditText password, etconfirmPassword;
    private boolean showPassword = false;
    private boolean showPassword1 = false;
    private LinearLayout lin_pass,lin_crn_pass;
    private LinearLayout passwordLayout;
    private ImageView showpwd,showcnfrmpwd;
    private RelativeLayout rel_otp;
    private String blank = "";
    private ForgotPasswordReponseBean forgotPasswordReponseBean;
    private String otp;
    private HashMap postDataParams;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        passwordLayout = (LinearLayout) findViewById(R.id.ll_password);
        password = (EditText) findViewById(R.id.et_forgot_password);
        etconfirmPassword = (EditText) findViewById(R.id.et_password_confirm);
        confirmPassword = (TextView) findViewById(R.id.tv_confirm_password);
        lin_pass=(LinearLayout)findViewById(R.id.lin_pass);
        lin_crn_pass=(LinearLayout)findViewById(R.id.lin_crn_pass);
        rel_otp=(RelativeLayout)findViewById(R.id.rel_otp);
        forgotPasswordReponseBean = new ForgotPasswordReponseBean();
        otpText = (TextView) findViewById(R.id.tv_otp_text);

        etOtpOne = (EditText) findViewById(R.id.et_otp_one);
        etOtpTwo = (EditText) findViewById(R.id.et_otp_two);
        etOtpThree = (EditText) findViewById(R.id.et_otp_three);
        etOtpFour = (EditText) findViewById(R.id.et_otp_four);
        showpwd=(ImageView)findViewById(R.id.iv_pass);
        showcnfrmpwd=(ImageView)findViewById(R.id.iv_confrmpass);
        etOtpOne.getBackground().mutate().setColorFilter(getResources().getColor(R.color.app_blue), PorterDuff.Mode.SRC_ATOP);
        etOtpTwo.getBackground().mutate().setColorFilter(getResources().getColor(R.color.app_blue), PorterDuff.Mode.SRC_ATOP);
        etOtpThree.getBackground().mutate().setColorFilter(getResources().getColor(R.color.app_blue), PorterDuff.Mode.SRC_ATOP);
        etOtpFour.getBackground().mutate().setColorFilter(getResources().getColor(R.color.app_blue), PorterDuff.Mode.SRC_ATOP);
        etOtpOne.addTextChangedListener(otpOneWatcher);
        etOtpTwo.addTextChangedListener(otpTwoWatcher);
        etOtpThree.addTextChangedListener(otpThreeWatcher);
        etOtpFour.addTextChangedListener(otpFourWatcher);
        etconfirmPassword.addTextChangedListener(confrmpasswordWatcher);
        password.addTextChangedListener(passwordWatcher);
        lin_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showPassword == false) {
                    showPassword = true;
                    showpwd.setImageResource(R.drawable.eye_blue);
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    password.setSelection(password.length());
                } else {
                    showPassword = false;
                    showpwd.setImageResource(R.drawable.eye_grey);
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setSelection(password.length());
                }
            }
        });
        lin_crn_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showPassword1 == false) {
                    showPassword1 = true;
                    showcnfrmpwd.setImageResource(R.drawable.eye_blue);
                    etconfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    etconfirmPassword.setSelection(etconfirmPassword.length());
                } else {
                    showPassword1 = false;
                    showcnfrmpwd.setImageResource(R.drawable.eye_grey);
                    etconfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    etconfirmPassword.setSelection(etconfirmPassword.length());
                }

            }
        });
        /*etconfirmPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (etconfirmPassword.getRight() - etconfirmPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        if (showPassword == false) {
                            showPassword = true;
                            etconfirmPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_blue, 0);
                            etconfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        } else {
                            showPassword = false;
                            etconfirmPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eyeg_grey, 0);
                            etconfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        }

                        //Toast.makeText(LoginActivity.this,"Drawable clicked",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                return false;
            }
        });*/
       /* password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (password.getRight() - password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        if (showPassword == false) {
                            showPassword = true;
                            password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_blue, 0);
                            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        } else {
                            showPassword = false;
                            password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eyeg_grey, 0);
                            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        }

                        //Toast.makeText(LoginActivity.this,"Drawable clicked",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                return false;
            }
        });*/
        confirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (password.getText().toString().length() < 8) {

                    Snackbar snack = Snackbar.make(rel_otp, "Password must be 8 charecter", Snackbar.LENGTH_LONG);
                    View view = snack.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    snack.show();


                    //Toast.makeText(OtpActivity.this, "Password must be 8 charecter", Toast.LENGTH_SHORT).show();
                } else {

                    InputMethodManager inputManager = (InputMethodManager) OtpActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(OtpActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    postDataParams = new HashMap<String, String>();

                    postDataParams.put("user id", String.valueOf(AppSharedPreference.getInt(OtpActivity.this, AppSharedPreference.PREF_KEY.UserId)));
                    postDataParams.put("password", password.getText().toString());
                    postDataParams.put(" cnf_password", etconfirmPassword.getText().toString());
                    ResetPasswordAsyncTask task = new ResetPasswordAsyncTask(OtpActivity.this, postDataParams, blank);
                    task.execute();
                }


                /*Intent intent = new Intent(OtpActivity.this,QueryFormActivity.class);
                startActivity(intent);
                finish();*/
            }
        });

        /*test = (TextView) findViewById(R.id.tv_test);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


            }
        }, 2000);*/

        etconfirmPassword.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager inputManager = (InputMethodManager) OtpActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(OtpActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    String pwd=password.getText().toString();
                    String cnpwd=etconfirmPassword.getText().toString();
                    if(pwd.equals("")|| cnpwd.equals(""))
                    {
                       // Toast.makeText(OtpActivity.this,"Enter all field",Toast.LENGTH_SHORT).show();

                        Snackbar snack = Snackbar.make(rel_otp, "Enter all field", Snackbar.LENGTH_LONG);
                        View view = snack.getView();
                        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setTextColor(getResources().getColor(R.color.white));
                        snack.show();


                    }
                    if((!pwd.equals("") && !cnpwd.equals(""))&&(password.getText().toString().length()<8 || etconfirmPassword.getText().toString().length()<8))
                    {


                        Snackbar snack = Snackbar.make(rel_otp, "Password must be 8 charecter", Snackbar.LENGTH_LONG);
                        View view = snack.getView();
                        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setTextColor(getResources().getColor(R.color.white));
                        snack.show();


                        //Toast.makeText(OtpActivity.this,"Password must be 8 charecter",Toast.LENGTH_SHORT).show();
                    }
                    if(!pwd.equals("") && !cnpwd.equals("") && password.getText().toString().length()>=8 && etconfirmPassword.getText().toString().length()>=8)
                    {
                        postDataParams = new HashMap<String, String>();
                        postDataParams.put("user id", String.valueOf(AppSharedPreference.getInt(OtpActivity.this, AppSharedPreference.PREF_KEY.UserId)));
                        postDataParams.put("password", password.getText().toString());
                        postDataParams.put(" cnf_password", etconfirmPassword.getText().toString());
                        ResetPasswordAsyncTask task = new ResetPasswordAsyncTask(OtpActivity.this, postDataParams, blank);
                        task.execute();
                    }

                }
                return false;
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
                showpwd.setVisibility(View.VISIBLE);
            }
            else
            {
                showpwd.setVisibility(View.GONE);
            }
            if (s.length() >= 8)

            {
                int leftPadding = confirmPassword.getPaddingLeft();
                int rightPadding = confirmPassword.getPaddingRight();
                int topPadding = confirmPassword.getPaddingTop();
                int bottomPadding = confirmPassword.getPaddingBottom();

                confirmPassword.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_blue_button));
                confirmPassword.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);




                //textView.setVisibility(View.GONE);
            }

            else {
                int leftPadding = confirmPassword.getPaddingLeft();
                int rightPadding = confirmPassword.getPaddingRight();
                int topPadding = confirmPassword.getPaddingTop();
                int bottomPadding = confirmPassword.getPaddingBottom();
                confirmPassword.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_grey_button));
                confirmPassword.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);



                //textView.setText("You have entered : " + passwordEditText.getText());
            }
        }
    };



    private final TextWatcher confrmpasswordWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //textView.setVisibility(View.VISIBLE);
        }

        public void afterTextChanged(Editable s) {
            if(s.length()>=1)
            {
                showcnfrmpwd.setVisibility(View.VISIBLE);
            }
            else
            {
                showcnfrmpwd.setVisibility(View.GONE);
            }
            if (s.length() >= 8)

            {
                int leftPadding = confirmPassword.getPaddingLeft();
                int rightPadding = confirmPassword.getPaddingRight();
                int topPadding = confirmPassword.getPaddingTop();
                int bottomPadding = confirmPassword.getPaddingBottom();

                confirmPassword.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_blue_button));
                confirmPassword.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);





                //textView.setVisibility(View.GONE);
            }

            else {
                int leftPadding = confirmPassword.getPaddingLeft();
                int rightPadding = confirmPassword.getPaddingRight();
                int topPadding = confirmPassword.getPaddingTop();
                int bottomPadding = confirmPassword.getPaddingBottom();

                confirmPassword.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_grey_button));
                confirmPassword.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);


                //textView.setText("You have entered : " + passwordEditText.getText());
            }
        }
    };


    private final TextWatcher otpOneWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //textView.setVisibility(View.VISIBLE);
        }

        public void afterTextChanged(Editable s) {
            if (s.length() == 1) {
                if (etOtpTwo.getText().toString().length() == 1 && etOtpThree.getText().toString().length() == 1 && etOtpFour.getText().toString().length() == 1) {

                } else {
                    etOtpTwo.requestFocus();

                }
            }
        }
    };

    private final TextWatcher otpTwoWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //textView.setVisibility(View.VISIBLE);
        }

        public void afterTextChanged(Editable s) {
            if (s.length() == 1) {
                if (etOtpTwo.getText().toString().length() == 1 && etOtpThree.getText().toString().length() == 1 && etOtpFour.getText().toString().length() == 1) {

                } else {
                    etOtpThree.requestFocus();

                }
            }
        }
    };

    private final TextWatcher otpThreeWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //textView.setVisibility(View.VISIBLE);
        }

        public void afterTextChanged(Editable s) {
            if (s.length() == 1) {
                if (etOtpTwo.getText().toString().length() == 1 && etOtpThree.getText().toString().length() == 1 && etOtpFour.getText().toString().length() == 1) {

                } else {
                    etOtpFour.requestFocus();

                }
            }
        }
    };

    private final TextWatcher otpFourWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //textView.setVisibility(View.VISIBLE);
        }

        public void afterTextChanged(Editable s) {
            if (s.length() == 1) {
                if (etOtpTwo.getText().toString().length() == 1 && etOtpThree.getText().toString().length() == 1 && etOtpFour.getText().toString().length() == 1) {

                    AppUtils.hideKeyBoard(OtpActivity.this);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            otpText.animate()
                                    .translationY(-500)
                                    .alpha(0.0f)
                                    .setDuration(1500);
                            etOtpOne.animate()
                                    .translationY(-500)
                                    .alpha(0.0f)
                                    .setDuration(1500);
                            etOtpTwo.animate()
                                    .translationY(-500)
                                    .alpha(0.0f)
                                    .setDuration(1500);
                            etOtpThree.animate()
                                    .translationY(-500)
                                    .alpha(0.0f)
                                    .setDuration(1500);
                            etOtpFour.animate()
                                    .translationY(-500)
                                    .alpha(0.0f)
                                    .setDuration(1500);

                        }
                    }, 2000);

                    //--------------------------------------------

                    postDataParams = new HashMap<String, String>();

                    postDataParams.put("user_id", String.valueOf(AppSharedPreference.getInt(OtpActivity.this, AppSharedPreference.PREF_KEY.UserId)));
                    postDataParams.put("user_otp", etOtpOne.getText().toString() + etOtpTwo.getText().toString() + etOtpThree.getText().toString() + etOtpFour.getText().toString());
                   /* otp=etOtpOne.getText().toString()+etOtpTwo.getText().toString()+etOtpThree.getText().toString()+etOtpFour.getText().toString();
                    AppSharedPreference.putString(OtpActivity.this, AppSharedPreference.PREF_KEY.OTP, otp);*/

                    OtpAsyncTask task = new OtpAsyncTask(OtpActivity.this, postDataParams, blank);
                    task.execute();

                    /*new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            passwordLayout.setVisibility(View.VISIBLE);
                            *//*passwordLayout.animate()
                                    .translationY(-500)
                                    .alpha(1.0f)
                                    .setDuration(2000);*//*
                            *//*passwordLayout.setVisibility(View.VISIBLE);
                            AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
                            animation1.setDuration(500);
                            animation1.setStartOffset(1000);
                            animation1.setFillAfter(true);
                            passwordLayout.startAnimation(animation1);*//*


                        }
                    }, 4000);*/


                } else {

                }
            }
        }
    };


    public void onSuccessfulLogin(String responseString) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onSuccessfulOtp(String responseString) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                passwordLayout.setVisibility(View.VISIBLE);
                            /*passwordLayout.animate()
                                    .translationY(-500)
                                    .alpha(1.0f)
                                    .setDuration(2000);*/
                            /*passwordLayout.setVisibility(View.VISIBLE);
                            AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
                            animation1.setDuration(500);
                            animation1.setStartOffset(1000);
                            animation1.setFillAfter(true);
                            passwordLayout.startAnimation(animation1);*/


            }
        }, 3000);

    }

    public void onSuccessfulChangePassword(String responseString) {


        Intent intent = new Intent(OtpActivity.this, LoginActivity.class);
        intent.putExtra("toast",responseString);
        this.finish();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onFailedOTP(String responseString) {
        Intent intent = new Intent(OtpActivity.this, OtpActivity.class);
        startActivity(intent);
        Toast.makeText(OtpActivity.this, responseString, Toast.LENGTH_SHORT).show();
    }

    public void onFailedPasswordChange(String responseString) {


        Snackbar snack = Snackbar.make(rel_otp, "Password Not Same", Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(getResources().getColor(R.color.white));
        snack.show();

        //Toast.makeText(OtpActivity.this, "Password Not Same", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Intent intent=new Intent(OtpActivity.this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 1000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        AppUtils.hideKeyBoard(OtpActivity.this);
        return true;
    }
}
