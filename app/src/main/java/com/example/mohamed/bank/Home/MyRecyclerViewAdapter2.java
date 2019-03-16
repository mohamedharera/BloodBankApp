package com.example.mohamed.bank.Home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mohamed.bank.Models.Posts.Category_Datum;
import com.example.mohamed.bank.Models.Posts.Category_Example;
import com.example.mohamed.bank.R;
import com.example.mohamed.bank.Service.RetrofitInstance;
import com.example.mohamed.bank.Views.ReportDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mohamed on 10/11/2018.
 */

public class MyRecyclerViewAdapter2 extends RecyclerView.Adapter<MyRecyclerViewAdapter2.ViewHolder> implements Filterable {

    private List<Category_Datum> mData;
    private List<Category_Datum> mDataFull;
    private Context context;


    public MyRecyclerViewAdapter2(List<Category_Datum> Data, Context context) {
        this.mData = Data;
        this.context = context;
        mDataFull = new ArrayList<>(mData);
    }

    @Override
    public MyRecyclerViewAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview2, null);
        MyRecyclerViewAdapter2.ViewHolder viewHolder = new MyRecyclerViewAdapter2.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Category_Datum donationData = mData.get(position);
        holder.title.setText(donationData.getTitle());
        Glide.with(context).load(donationData.getThumbnailFullPath()).into(holder.thumbnail);

        if (donationData.is_favourite()) {
            holder.check_favourite.setChecked(true);

        } else {
            holder.check_favourite.setChecked(false);
        }
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReportDetails.class);
                int id = mData.get(position).getId();
                intent.putExtra("Id_I_NEED", id);
                String title = mData.get(position).getTitle();
                intent.putExtra("title_I_NEED", title);
                context.startActivity(intent);
            }
        });

        holder.check_favourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Log.i("isChecked", "is checked");

                if (holder.check_favourite.isChecked()) {
                    boolean check = addToFavourite(mData.get(position).getId());

                    if (check) {
                        Log.i("isChecked", "start");
                        mData.get(position).setIs_favourite(true);
                        notifyDataSetChanged();
                    }
                } else {
                    boolean check = addToFavourite(mData.get(position).getId());

                    if (check) {
                        Log.i("isChecked", "start");
                        mData.get(position).setIs_favourite(false);
                        notifyDataSetChanged();

                    }
                }

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
            List<Category_Datum>filteredList = new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(mDataFull);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Category_Datum item :mDataFull){
                    if (item.getTitle().toLowerCase().contains(filterPattern)){
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

        protected ImageView thumbnail;
        protected TextView title;
        RelativeLayout parent_layout;
        CheckBox check_favourite;

        public ViewHolder(View view) {
            super(view);
            thumbnail = view.findViewById(R.id.thumbnail);
            title = view.findViewById(R.id.title);
            parent_layout = view.findViewById(R.id.parent_layout);
            check_favourite = view.findViewById(R.id.check_favourite);
        }
    }


    public Boolean addToFavourite(int id) {
        final boolean[] isChecked = {false};
        Call<Category_Example> call = RetrofitInstance.getDataService().addFavourite(id, "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");

        call.enqueue(new Callback<Category_Example>() {
            @Override
            public void onResponse(Call<Category_Example> call, Response<Category_Example> response) {
                Category_Example user = response.body();
                if (user.getStatus() == 1) {
                    isChecked[0] = true;
                } else {
                    isChecked[0] = false;
                }
            }

            @Override
            public void onFailure(Call<Category_Example> call, Throwable t) {
                Log.i("isChecked", "error  " + t.getMessage());
                isChecked[0] = false;
            }
        });

        return isChecked[0];
    }

}
