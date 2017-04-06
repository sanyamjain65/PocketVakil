package com.app.pocketvakil.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.app.pocketvakil.activities.QueryFormActivity;
import com.app.pocketvakil.bean.FaqResponseBean;
import com.app.pocketvakil.networkclass.NetworkGetCall;
import com.app.pocketvakil.networkclass.NetworkKeys;

import java.util.HashMap;

/**
 * Created by appbulous on 26/5/16.
 */
public class FaqAsyncTask extends AsyncTask<FaqResponseBean,FaqResponseBean,FaqResponseBean> {

    private Context context;
    private HashMap<String, String> postDataParams;
    private ProgressDialog progressDialog;
    private String responseString;
    private int flag=0;
    private  String blank;
    private int check;

    public FaqAsyncTask(Context context,int check) {
        this.context = context;
        this.postDataParams = postDataParams;
        this.blank=blank;
        this.check=check;
    }
    @Override
    protected void onPostExecute(FaqResponseBean faqResponseBean) {
        super.onPostExecute(faqResponseBean);
        if(progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
            //((QueryFormActivity)context).onFAQSucess(faqResponseBean);
        }

        if (faqResponseBean !=null && faqResponseBean.getErrorCode()!=100 ) {
           // responseString = loginResponseBean.getResponseString();
//            Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((QueryFormActivity)context).onFAQSucess(faqResponseBean);

        }
        else if(faqResponseBean!=null&&faqResponseBean.getErrorCode()==100)
        {
            ((QueryFormActivity)context).onFAQFail("Network Error");
        }

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

    @Override
    protected FaqResponseBean doInBackground(FaqResponseBean... params) {

        if(check==1)
        {
            return NetworkGetCall.getInstance(context).faqData(NetworkKeys.NET_KEY.FAQ_URL);
        }
        if(check==0){
            return NetworkGetCall.getInstance(context).faqData(NetworkKeys.NET_KEY.FAQ_URL_HINDI);
        }
        return null;

    }
}
