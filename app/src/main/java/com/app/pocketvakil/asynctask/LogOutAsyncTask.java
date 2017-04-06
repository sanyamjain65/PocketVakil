package com.app.pocketvakil.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.app.pocketvakil.activities.QueryFormActivity;
import com.app.pocketvakil.bean.LogOutResponseBean;
import com.app.pocketvakil.networkclass.NetworkCall;
import com.app.pocketvakil.networkclass.NetworkKeys;

import java.util.HashMap;

/**
 * Created by appbulous on 25/5/16.
 */
public class LogOutAsyncTask extends AsyncTask<LogOutResponseBean,LogOutResponseBean,LogOutResponseBean> {

    private Context context;
    private HashMap<String, String> postDataParams;
    private ProgressDialog progressDialog;
    private String responseString;
    private String access_token;


    public LogOutAsyncTask(Context context, HashMap<String, String> postDataParams,String access_token) {

        this.context = context;
        this.postDataParams = postDataParams;
        this.access_token=access_token;

    }
    @Override
    protected void onPostExecute(LogOutResponseBean logOutResponseBean) {
        super.onPostExecute(logOutResponseBean);
        if(progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
        if (logOutResponseBean !=null && logOutResponseBean.getErrorCode()==0 ) {
            responseString = logOutResponseBean.getResponseString();
//            Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((QueryFormActivity)context).onSuccessfulLogOut(responseString);

        }
        else if (logOutResponseBean !=null) {
            responseString = logOutResponseBean.getResponseString();
            // Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((QueryFormActivity)context).onLoginFailed(responseString);
        }
        else {
            //Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show();
            ((QueryFormActivity)context).onLoginFailed("Network error");
        }
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
    protected LogOutResponseBean doInBackground(LogOutResponseBean... params) {
        return NetworkCall.getInstance(context).logoutData(NetworkKeys.NET_KEY.LOGOUT_URL, postDataParams,access_token);
    }
}
