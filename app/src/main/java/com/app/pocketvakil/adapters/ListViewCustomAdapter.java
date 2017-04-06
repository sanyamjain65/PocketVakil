package com.app.pocketvakil.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.pocketvakil.R;
import com.app.pocketvakil.activities.FAQ_Question_Extend_Activity;
import com.app.pocketvakil.bean.FaqResponseBean;

import java.util.List;

/**
 * Created by appbulous on 27/5/16.
 */
public class ListViewCustomAdapter extends BaseAdapter {

    Context context;
    List<FaqResponseBean> faqResponseBeans;


    public ListViewCustomAdapter(Context context, List<FaqResponseBean> faqResponseBeans) {
        this.context = context;
        this.faqResponseBeans = faqResponseBeans;
    }

    @Override
    public int getCount() {
        return faqResponseBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return faqResponseBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return faqResponseBeans.indexOf(getItem(position));
    }

    private class ViewHolder {

        TextView tv_ques;
        RelativeLayout relativeLayout;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listview_row_item, null);
            holder.tv_ques=(TextView)convertView.findViewById(R.id.tv_ques);
            holder.relativeLayout=(RelativeLayout)convertView.findViewById(R.id.rel);
            convertView.setTag(holder);


        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }
        final FaqResponseBean row_pos = faqResponseBeans.get(position);
        holder.tv_ques.setText(row_pos.getQuestion());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, FAQ_Question_Extend_Activity.class);
                intent.putExtra("question",row_pos.getQuestion());
                intent.putExtra("ans",row_pos.getAnswer());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
