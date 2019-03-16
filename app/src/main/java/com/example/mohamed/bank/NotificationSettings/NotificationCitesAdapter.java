package com.example.mohamed.bank.NotificationSettings;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.mohamed.bank.Models.DatumCity;
import com.example.mohamed.bank.R;

import java.util.List;

public class NotificationCitesAdapter extends RecyclerView .Adapter<NotificationCitesAdapter.ViewHolder>{

    List<DatumCity> myItems;
    Context mContext;


    public NotificationCitesAdapter(Context context) {
        mContext = context;
    }
    public void sendData(List<DatumCity> dataList) {
        this.myItems = dataList;
    }

    @Override
    public int getItemCount() {
        return myItems !=null?myItems.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_box_row_item, null);
        ViewHolder viewHolder = new ViewHolder(view);

        SharedPreferences preferences = mContext.getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        boolean valueBoolean = preferences.getBoolean("KEY", false);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("KEY", valueBoolean);
        editor.commit();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final DatumCity data = myItems.get(position);
        holder.countryCheckBox.setText(data.getName());


        if (data.isSelected()) {
            holder.countryCheckBox.setChecked(true);

        } else {
            holder.countryCheckBox.setChecked(false);
        }
        holder.countryCheckBox.setChecked(getFromSP("Chk"));
        holder.countryCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                saveInSp("Chk", b);
            }
        });
        holder.countryCheckBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(holder.countryCheckBox.isChecked()){
                    saveInSp("Chk", true);
                } else{
                    saveInSp("chk", false);
                }
            }
        });

        }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public CheckBox countryCheckBox;

        public ViewHolder(View v) {
            super(v);

            this.setIsRecyclable(false);
            countryCheckBox = v.findViewById(R.id.country_name);
        }


    }


    private boolean getFromSP(String key) {
        SharedPreferences preferences = mContext.getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    private void saveInSp(String key,boolean value){
        SharedPreferences preferences = mContext.getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }


//    public Boolean  addnotification(){
//        final boolean[] ischecked = {false};
//        final GetDataService dataservices = RetrofitInstance.getDataService();
//
//        Call<NotificationSetting_Example> call = dataservices.selectNotification("mzj4aDhSta6ulLzA4igpTrNXSSNqeNpzYBOKQKCyBNaCf9EgtOh5Vu6sXFyf");
//        call.enqueue(new Callback<NotificationSetting_Example>() {
//            @Override
//            public void onResponse(Call<NotificationSetting_Example> call, Response<NotificationSetting_Example> response) {
//                NotificationSetting_Example notificationSettingExample =response.body();
//                if (notificationSettingExample.getStatus() == 1){
//
//                    ischecked[0] = true;
//                    Toast.makeText(mContext,notificationSettingExample.getMsg() , Toast.LENGTH_SHORT).show();
//                }else {
//                    ischecked[0] = false;
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<NotificationSetting_Example> call, Throwable t) {
//
//            }
//        });
//        return ischecked[0];
//    }

}

