package com.app.pocketvakil.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.app.pocketvakil.activities.ConfirmEmailActivity;
import com.app.pocketvakil.activities.ForgotPasswordActivity;
import com.app.pocketvakil.bean.ForgotPasswordReponseBean;
import com.app.pocketvakil.networkclass.NetworkCall;
import com.app.pocketvakil.networkclass.NetworkKeys;
import com.app.pocketvakil.utils.AppSharedPreference;

import java.util.HashMap;

/**
 * Created by appbulous on 23/5/16.
 */
public class ForgotPasswordAsyncTask extends AsyncTask<ForgotPasswordReponseBean,ForgotPasswordReponseBean,ForgotPasswordReponseBean> {

    private Context context;
    private HashMap<String, String> postDataParams;
    private ProgressDialog progressDialog;
    private String responseString;
    private int flag=0;
    private  String blank;

    public ForgotPasswordAsyncTask(Context context, HashMap<String, String> postDataParams,String blank) {
        this.context = context;
        this.postDataParams = postDataParams;
        this.blank=blank;
    }

    @Override
    protected ForgotPasswordReponseBean doInBackground(ForgotPasswordReponseBean... params) {
        return NetworkCall.getInstance(context).ForgotPwdData(NetworkKeys.NET_KEY.FORGOT_PASSWORD, postDataParams,blank);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Processing...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(ForgotPasswordReponseBean forgotPasswordReponseBean) {
        super.onPostExecute(forgotPasswordReponseBean);

        if(progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
        flag= AppSharedPreference.getInt(context, AppSharedPreference.PREF_KEY.HIT_COUNT);
        if (forgotPasswordReponseBean !=null && forgotPasswordReponseBean.getErrorCode()==0 && flag==0) {
            responseString = forgotPasswordReponseBean.getResponseString();
//            Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((ForgotPasswordActivity)context).onSuccessfulMailSend(responseString);


        }
        else if (forgotPasswordReponseBean !=null&& flag==0) {
            responseString = forgotPasswordReponseBean.getResponseString();
            // Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((ForgotPasswordActivity)context).onmailsendFailed(responseString);
        }
        else if (forgotPasswordReponseBean !=null && forgotPasswordReponseBean.getErrorCode()==0 && flag!=0) {
            responseString = forgotPasswordReponseBean.getResponseString();
            AppSharedPreference.clearInt(context, AppSharedPreference.PREF_KEY.HIT_COUNT);
//            Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((ConfirmEmailActivity)context).onSuccessfulResendMailSend(responseString);
        }
        else if (forgotPasswordReponseBean !=null&& flag!=0) {
            responseString = forgotPasswordReponseBean.getResponseString();
            // Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((ConfirmEmailActivity)context).onConfermMailFailed(responseString);
        }
    }


}
