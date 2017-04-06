package com.app.pocketvakil.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.app.pocketvakil.activities.SignUpActivity;
import com.app.pocketvakil.bean.SingUpResponseBean;
import com.app.pocketvakil.networkclass.NetworkCall;
import com.app.pocketvakil.networkclass.NetworkKeys;

import java.util.HashMap;

/**
 * Created by appbulous on 23/5/16.
 */
public class SignUpAsyncTask extends AsyncTask<SingUpResponseBean,SingUpResponseBean,SingUpResponseBean> {
    private Context context;
    private HashMap<String, String> postDataParams;
    private ProgressDialog progressDialog;
    private String responseString;
    private String blank;
    public SignUpAsyncTask(Context context, HashMap<String, String> postDataParams,String blank) {

        this.context = context;
        this.postDataParams = postDataParams;
        this.blank=blank;
    }

    @Override
    protected SingUpResponseBean doInBackground(SingUpResponseBean... params) {
        return NetworkCall.getInstance(context).signupData(NetworkKeys.NET_KEY.SIGNUP_URL, postDataParams,blank);
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
    protected void onPostExecute(SingUpResponseBean singUpResponseBean) {
        super.onPostExecute(singUpResponseBean);
        if(progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
        if (singUpResponseBean !=null && singUpResponseBean.getErrorCode()==0 ) {
            responseString = singUpResponseBean.getResponseString();
//            Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((SignUpActivity)context).onSuccessfulSignUp(responseString);

        }
        else if (singUpResponseBean !=null) {
            responseString = singUpResponseBean.getResponseString();
            // Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((SignUpActivity)context).onSignUpFailed(responseString);
        }
        else {
            //Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show();
            ((SignUpActivity)context).onSignUpFailed("Network error");
        }

    }
}
