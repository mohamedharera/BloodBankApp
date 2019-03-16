package com.example.mohamed.bank.Notifications;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mohamed.bank.Models.Notifications.NotificationsDatum;
import com.example.mohamed.bank.R;

import java.util.List;

/**
 * Created by Mohamed on 11/4/2018.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {
    private List<NotificationsDatum> mData;
    private Context context;

    public NotificationsAdapter(List<NotificationsDatum> Data, Context context) {
        this.mData = Data;
        this.context = context;
    }

    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_row_item, null);
        NotificationsAdapter.ViewHolder viewHolder = new NotificationsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NotificationsAdapter.ViewHolder holder, final int position) {
        final NotificationsDatum data = mData.get(position);
        holder.title.setText(data.getTitle());
        holder.date.setText(data.getUpdatedAt());

        int r = data.getPivot().getIsRead();
        if (r==0){
            Glide.with(context).load(R.mipmap.light_notify).into(holder.notify_icon);
        }else {
            Glide.with(context).load(R.mipmap.notify).into(holder.notify_icon);
        }
    }

    @Override
    public int getItemCount() {
        return mData!=null?mData.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,date;
        ImageView notify_icon;


        public ViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.title);
            date = view.findViewById(R.id.date);
            notify_icon = view.findViewById(R.id.notify_icon);
        }
    }

}
