package com.app.pocketvakil.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.app.pocketvakil.activities.LoginActivity;
import com.app.pocketvakil.activities.QueryFormActivity;
import com.app.pocketvakil.bean.QuaryResponseBean;
import com.app.pocketvakil.networkclass.NetworkCall;
import com.app.pocketvakil.networkclass.NetworkKeys;

import java.util.HashMap;

/**
 * Created by appbulous on 31/5/16.
 */
public class QuaryAsyncTask extends AsyncTask<QuaryResponseBean,QuaryResponseBean,QuaryResponseBean> {


    private Context context;
    private HashMap<String, String> postDataParams;
    private ProgressDialog progressDialog;
    private String responseString;
    private String blank;

    public QuaryAsyncTask(Context context, HashMap<String, String> postDataParams, String blank) {

        this.context = context;
        this.postDataParams = postDataParams;
        this.blank=blank;

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
    protected void onPostExecute(QuaryResponseBean quaryResponseBean) {
        super.onPostExecute(quaryResponseBean);
        if(progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
        if (quaryResponseBean !=null && quaryResponseBean.getErrorCode()==0 ) {
            responseString = quaryResponseBean.getResponseString();
//            Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((QueryFormActivity)context).onSuccessfulSendQuary(responseString);

        }
        else if (quaryResponseBean !=null && quaryResponseBean.getErrorCode()==1 ) {
            responseString = quaryResponseBean.getResponseString();
            // Toast.makeText(context, loginResponseBean.getResponseString(), Toast.LENGTH_LONG).show();
            ((QueryFormActivity)context).onQuaryFailed(responseString);
        }
        else if(quaryResponseBean !=null && quaryResponseBean.getErrorCode()==100){
            //Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show();
            ((QueryFormActivity)context).onQuaryNetWorkFailed("Network error");
        }
    }

    @Override
    protected QuaryResponseBean doInBackground(QuaryResponseBean... params) {
        return NetworkCall.getInstance(context).quaryData(NetworkKeys.NET_KEY.QUARY_URL, postDataParams, blank);
    }
}
