package com.app.pocketvakil.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.app.pocketvakil.activities.OtpActivity;
import com.app.pocketvakil.bean.OtpResponseBean;
import com.app.pocketvakil.networkclass.NetworkCall;
import com.app.pocketvakil.networkclass.NetworkKeys;

import java.util.HashMap;

/**
 * Created by appbulous on 25/5/16.
 */
public class OtpAsyncTask extends AsyncTask<OtpResponseBean,OtpResponseBean,OtpResponseBean> {

    private Context context;
    private HashMap<String, String> postDataParams;
    private ProgressDialog progressDialog;
    private String responseString;
    private  String blank;

    public OtpAsyncTask(Context context, HashMap<String, String> postDataParams,String blank) {

        this.context = context;
        this.postDataParams = postDataParams;
        this.blank=blank;

    }

    @Override
    protected void onPostExecute(OtpResponseBean otpResponseBean) {
        super.onPostExecute(otpResponseBean);
        if(progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
        if (otpResponseBean !=null && otpResponseBean.getErrorCode()==0 ) {
            responseString = otpResponseBean.getResponseString();
//            Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((OtpActivity)context).onSuccessfulOtp(responseString);

        }
        else if (otpResponseBean !=null) {
            responseString = otpResponseBean.getResponseString();
            // Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((OtpActivity)context).onFailedOTP(responseString);
        }
        else {
            //Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show();
            ((OtpActivity)context).onFailedOTP("Network error");
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
    protected OtpResponseBean doInBackground(OtpResponseBean... params) {
        return NetworkCall.getInstance(context).OtpData(NetworkKeys.NET_KEY.OTP_URL, postDataParams,blank);
    }
}
