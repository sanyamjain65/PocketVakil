package com.app.pocketvakil.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.pocketvakil.R;

/**
 * Created by appinventiv on 11/5/16.
 */
public class CaseTypeAdapter extends ArrayAdapter<String> {

    private Activity mActivity;
    private String[] strings;

    public CaseTypeAdapter(Activity context, int textViewResourceId, String[] objects) {
        super(context, textViewResourceId, objects);
        this.mActivity = context;
        this.strings = objects;


    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=mActivity.getLayoutInflater();
        View row=inflater.inflate(R.layout.spinner_row, parent, false);
        TextView label=(TextView)row.findViewById(R.id.company);
        label.setText(strings[position]);


       /* switch (position)
        {
            case 0:
            {
                label.setTextColor(getContext().getResources().getColor(R.color.text_hint_color));
                break;
            }
        }*/

        /*TextView sub=(TextView)row.findViewById(R.id.sub);
        sub.setText(subs[position]);

        ImageView icon=(ImageView)row.findViewById(R.id.image);
        icon.setImageResource(arr_images[position]);*/

        return row;
    }

}
