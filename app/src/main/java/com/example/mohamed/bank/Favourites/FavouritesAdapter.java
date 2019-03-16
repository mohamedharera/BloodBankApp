package com.example.mohamed.bank.Favourites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mohamed.bank.Models.Favourites.Favourite_Datum;
import com.example.mohamed.bank.Models.Posts.Category_Example;
import com.example.mohamed.bank.R;
import com.example.mohamed.bank.Service.RetrofitInstance;
import com.example.mohamed.bank.Views.ReportDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mohamed on 10/30/2018.
 */

public class FavouritesAdapter  extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {
    private List<Favourite_Datum> mData;
    private Context context;

    public FavouritesAdapter(List<Favourite_Datum> Data, Context context) {
        this.mData = Data;
        this.context = context;
    }

    @Override
    public FavouritesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_row_item, null);
        FavouritesAdapter.ViewHolder viewHolder = new FavouritesAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Favourite_Datum donationData = mData.get(position);
        holder.title.setText(donationData.getTitle());
        Glide.with(context).load(donationData.getThumbnailFullPath()).into(holder.thumbnail);

        if (donationData.getIsFavourite()) {
            holder.check_favourite.setChecked(true);

        } else {
            holder.check_favourite.setChecked(false);
        }

        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReportDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
                        mData.get(position).setIsFavourite(true);
                        notifyDataSetChanged();
                    }
                } else {
                    boolean check = addToFavourite(mData.get(position).getId());

                    if (check) {
                        Log.i("isChecked", "start");
                        mData.get(position).setIsFavourite(false);
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
            check_favourite = view.findViewById(R.id.checkFavourite);
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
