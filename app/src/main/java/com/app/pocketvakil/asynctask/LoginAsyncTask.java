package com.app.pocketvakil.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.app.pocketvakil.activities.LoginActivity;
import com.app.pocketvakil.bean.LoginResponseBean;
import com.app.pocketvakil.networkclass.NetworkCall;
import com.app.pocketvakil.networkclass.NetworkKeys;

import java.util.HashMap;

/**
 * Created by appinventiv on 7/4/16.
 */
public class LoginAsyncTask extends AsyncTask<LoginResponseBean,LoginResponseBean,LoginResponseBean> {

    private Context context;
    private HashMap<String, String> postDataParams;
    private ProgressDialog progressDialog;
    private String responseString;
   private String blank;

    public LoginAsyncTask(Context context, HashMap<String, String> postDataParams, String blank) {

        this.context = context;
        this.postDataParams = postDataParams;
        this.blank=blank;

    }


    @Override
    protected LoginResponseBean doInBackground(LoginResponseBean... params) {

        return NetworkCall.getInstance(context).loginData(NetworkKeys.NET_KEY.LOGIN_IN_URL, postDataParams,blank);

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
    protected void onPostExecute(LoginResponseBean loginResponseBean) {
        super.onPostExecute(loginResponseBean);
        if(progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
        if (loginResponseBean !=null && loginResponseBean.getErrorCode()==0 ) {
            responseString = loginResponseBean.getResponseString();
//            Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((LoginActivity)context).onSuccessfulLogin(responseString);

        }
        else if (loginResponseBean !=null && loginResponseBean.getErrorCode()==1 ) {
            responseString = loginResponseBean.getResponseString();
          // Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((LoginActivity)context).onLoginFailed(responseString);
        }
        else if(loginResponseBean !=null && loginResponseBean.getErrorCode()==100){
            //Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show();
            ((LoginActivity)context).onLoginNetWorkFailed("Network error");
        }
    }
}
