package com.example.mohamed.bank.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohamed.bank.Models.Posts.Category_Datum;
import com.example.mohamed.bank.Models.Posts.Category_Example;
import com.example.mohamed.bank.R;
import com.example.mohamed.bank.Service.GetDataService;
import com.example.mohamed.bank.Service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondFragment extends Fragment implements SearchView.OnQueryTextListener {

    RecyclerView mRecyclerView;
//    Spinner spinnerSearch;
    SearchView search;
    List<Category_Datum> category_data;
    MyRecyclerViewAdapter2 recyclerViewAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
//        spinnerSearch = view.findViewById(R.id.spinnerSearch);
        mRecyclerView = view.findViewById(R.id.recycler_view2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        search = view.findViewById(R.id.Articlessearch);
        search.setOnQueryTextListener(this);
        search.setQueryHint("ادخل اسم المقال هنا");

        getRecyclerView();
//        getArticles();
        return view;
    }


    public void getRecyclerView(){
        final GetDataService getDataService = RetrofitInstance.getDataService();
        Call<Category_Example> call = getDataService.getCategory("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        call.enqueue(new Callback<Category_Example>() {
            @Override
            public void onResponse(Call<Category_Example> call, Response<Category_Example> response) {
                category_data= response.body().getCategory_data().getData();
                recyclerViewAdapter = new MyRecyclerViewAdapter2(category_data,getContext());
                mRecyclerView.setAdapter(recyclerViewAdapter);

            }

            @Override
            public void onFailure(Call<Category_Example> call, Throwable t) {
                if( t.getMessage() != null) {
                    Log.i("response error", t.getMessage());
                }

            }
        });
    }

//    public void getArticles(){
//        final GetDataService dataservices2 = RetrofitInstance.getDataService();
//        Call<Category_Example> call = dataservices2.getCategory("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
//        call.enqueue(new Callback<Category_Example>() {
//            @Override
//            public void onResponse(Call<Category_Example> call, Response<Category_Example> response) {
//                List<Category_Datum> category_data = response.body().getCategory_data().getData();
//                final List<String> listSpinner = new ArrayList<String>();
//                final List<Integer> listSpinnerIds = new ArrayList<>();
//                listSpinner.add("اختر المقال");
//                for (int i = 0; i < category_data.size(); i++) {
//                    listSpinner.add(category_data.get(i).getCategory().getName());
//                    listSpinnerIds.add(category_data.get(i).getCategory().getId());
//                }
//
//                final int[] id = {-1};
//
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item ,listSpinner);
//                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//                    spinnerSearch.setAdapter(adapter);
//
//                spinnerSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                        if (position != 0) {
//                            id[0] = listSpinnerIds.get(position - 1);
//                            String text=spinnerSearch.getSelectedItem().toString();
//                        }
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(Call<Category_Example> call, Throwable t) {
//
//            }
//        });
//    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (recyclerViewAdapter != null){
            recyclerViewAdapter.getFilter().filter(newText);
        }
        return false;
    }
}
