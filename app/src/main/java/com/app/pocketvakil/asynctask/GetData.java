package com.app.pocketvakil.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.app.pocketvakil.networkclass.ServiceHandler;

/**
 * Created by appbulous on 23/5/16.
 */
public class GetData extends AsyncTask {

    private Context context;
    private ProgressDialog pDialog;
    private static final String  USERNAME="pocketvakil";
    private static final String PASSWORD="12345!@#QWE";
    private static final String API_KEY="2994583b1183157b95131e9f656129e4";


    // URL to get contacts JSON
    private static String url = "http://pocketvakil.applaurels.com/v1/signup";


    public GetData(Context context) {
        this.context=context;

    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

    }

    @Override
    protected Object doInBackground(Object[] params) {
        // Creating service handler class instance
        ServiceHandler sh = new ServiceHandler();

        // Making a request to url and getting response




        String jsonStr = sh.makeServiceCall(url + "user_email" + USERNAME + "user_password" + PASSWORD , ServiceHandler.POST);

        Log.d("Response: ", "> " + jsonStr);



        return null;
    }
}
