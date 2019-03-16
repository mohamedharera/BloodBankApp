package com.example.mohamed.bank.Home;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mohamed.bank.DetailsDonationAsk;
import com.example.mohamed.bank.Models.Donation_recyclerView_items.DonationAskDatum;
import com.example.mohamed.bank.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Mohamed on 10/11/2018.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> implements Filterable {

    private List<DonationAskDatum> mData;
    private List<DonationAskDatum> mDataFull;
    private Context mcontext;

    public MyRecyclerViewAdapter(List<DonationAskDatum> Data,Context context) {
        this.mData = Data;
        mcontext=context;
        mDataFull = new ArrayList<>(mData);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview1, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final DonationAskDatum mdata = mData.get(position);

        holder.name.setText(mdata.getPatientName());
        holder.hospital.setText(mdata.getHospitalName());
        holder.country.setText(mdata.getCity().getName());
        holder.blood.setText(mdata.getBloodType().getName());
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = mdata.getPhone();

                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+phone));
                if (ActivityCompat.checkSelfPermission(mcontext, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    requestPermission();
                }
                else {
                    startActivity(mcontext,i,null);
                }
            }
            private void requestPermission(){
                ActivityCompat.requestPermissions((Activity) mcontext,new String[]{Manifest.permission.CALL_PHONE},1);
            }
        });

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, DetailsDonationAsk.class);
                int id = mData.get(position).getId();
                intent.putExtra("Id_I_NEED", id);
                view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DonationAskDatum>filteredList = new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(mDataFull);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (DonationAskDatum item :mDataFull){
                    if (item.getBloodType().getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values =filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mData.clear();
            mData.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView hospital;
        TextView country;
        TextView blood;
        Button call,details;
        RelativeLayout relative_details;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            hospital = (TextView) view.findViewById(R.id.hospital);
            country = (TextView) view.findViewById(R.id.country);
            blood = (TextView) view.findViewById(R.id.blood_type);
            call = view.findViewById(R.id.call);
            relative_details = view.findViewById(R.id.relative_details);
            details = view.findViewById(R.id.details);
        }
    }

}
