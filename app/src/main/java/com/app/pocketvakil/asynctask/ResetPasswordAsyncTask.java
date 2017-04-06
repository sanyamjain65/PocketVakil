package com.app.pocketvakil.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.app.pocketvakil.activities.OtpActivity;
import com.app.pocketvakil.bean.ResetPasswordBean;
import com.app.pocketvakil.networkclass.NetworkCall;
import com.app.pocketvakil.networkclass.NetworkKeys;

import java.util.HashMap;

/**
 * Created by appbulous on 24/5/16.
 */
public class ResetPasswordAsyncTask extends AsyncTask<ResetPasswordBean,ResetPasswordBean,ResetPasswordBean> {

    private Context context;
    private HashMap<String, String> postDataParams;
    private ProgressDialog progressDialog;
    private String responseString;
    private String blank;

    public ResetPasswordAsyncTask(Context context, HashMap<String, String> postDataParams,String blank) {
        this.context = context;
        this.postDataParams = postDataParams;
        this.blank=blank;

    }
    @Override
    protected void onPostExecute(ResetPasswordBean resetPasswordBean) {
        super.onPostExecute(resetPasswordBean);
        if(progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
        if (resetPasswordBean !=null && resetPasswordBean.getErrorCode()==0 ) {
            responseString = resetPasswordBean.getResponseString();
//            Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((OtpActivity)context).onSuccessfulChangePassword(responseString);

        }
        else if (resetPasswordBean !=null) {
            responseString = resetPasswordBean.getResponseString();
            // Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((OtpActivity)context).onFailedPasswordChange(responseString);
        }
        else {
            //Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show();
            ((OtpActivity)context).onFailedPasswordChange("Network error");
        }


    }



    @Override
    protected ResetPasswordBean doInBackground(ResetPasswordBean... params) {
        return NetworkCall.getInstance(context).resetData(NetworkKeys.NET_KEY.RESET_PASSWORD_URL, postDataParams,blank);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Processing...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


}
