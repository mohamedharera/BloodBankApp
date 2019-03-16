package com.example.mohamed.bank.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mohamed.bank.Models.Donation_recyclerView_items.DonationAskDatum;
import com.example.mohamed.bank.Models.Donation_recyclerView_items.DonationAskResponse;
import com.example.mohamed.bank.R;
import com.example.mohamed.bank.Service.GetDataService;
import com.example.mohamed.bank.Service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragment extends Fragment implements SearchView.OnQueryTextListener {

    RecyclerView mRecyclerView;
    Button call, details;
    Spinner spinnerCity, spinnerBlood;
    MyRecyclerViewAdapter recyclerViewAdapter;
    int max_page;
    SearchView search;
    private List<DonationAskDatum> category_data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        call = view.findViewById(R.id.call);
        details = view.findViewById(R.id.details);
//      spinnerBlood = view.findViewById(R.id.spinnerbags);
        spinnerCity = view.findViewById(R.id.spinnerCity);
        search = view.findViewById(R.id.DonationSearch);
        search.setQueryHint("ادخل نوع فصيلة الدم");
        search.setOnQueryTextListener(this);

        mRecyclerView = view.findViewById(R.id.recycler_view1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        OnEndless onEndlesslistener = new OnEndless(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page < max_page) {
                    getRecyclerView(current_page);
                } else {
                    Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
                }
            }
        };
        mRecyclerView.addOnScrollListener(onEndlesslistener);
        getRecyclerView(1);

//        getSpinnersCity(1);
//        getSpinnerBlood("A-");

        return view;
    }

    public void getRecyclerView(final int page) {

        String url = "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";

        final GetDataService getDataService = RetrofitInstance.getDataService();
        Call<DonationAskResponse> call = getDataService.getRequest(url,page);
        call.enqueue(new Callback<DonationAskResponse>() {
            @Override
            public void onResponse(Call<DonationAskResponse> call, Response<DonationAskResponse> response) {
                DonationAskResponse body = response.body();
                viewResponse(body, page);
                max_page = response.body().getData().getLastPage();
            }
            @Override
            public void onFailure(Call<DonationAskResponse> call, Throwable t) {
//                Log.i("response error", t.getMessage());
            }
        });
    }

    public void viewResponse(DonationAskResponse body, int page) {
        if (page == 1) {
            category_data = body.getData().getData();
            recyclerViewAdapter = new MyRecyclerViewAdapter(category_data,getActivity());
            mRecyclerView.setAdapter(recyclerViewAdapter);
        } else{
            category_data.addAll(body.getData().getData());
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    public void getSpinnersCity(final int cityId) {
        final GetDataService dataservices2 = RetrofitInstance.getDataService();
        Call<DonationAskResponse> call = dataservices2.getCity("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27",cityId);
        call.enqueue(new Callback<DonationAskResponse>() {
            @Override
            public void onResponse(Call<DonationAskResponse> call, Response<DonationAskResponse> response) {
                DonationAskResponse category_example = response.body();
                List<DonationAskDatum> cateogry = category_example.getData().getData();

                final List<String> listSpinner = new ArrayList<String>();
                listSpinner.add("اختر المدينه");
                final List<Integer> listSpinnerIds = new ArrayList<>();

                for (int i = 0; i < cateogry.size(); i++) {
                    listSpinner.add(cateogry.get(i).getCity().getName());
                    listSpinnerIds.add(cateogry.get(i).getCity().getId());
                }

                final int[] id = {-1};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinnerCity.setAdapter(adapter);
                spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        if (position != 0) {
                            id[0] = listSpinnerIds.get(position - 1);
                            String text = spinnerCity.getSelectedItem().toString();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<DonationAskResponse> call, Throwable t) {

            }
        });

    }

    public void getSpinnerBlood(final String bloodtype) {

        final GetDataService dataservices2 = RetrofitInstance.getDataService();
        Call<DonationAskResponse> call = dataservices2.getBlood("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27",bloodtype);
        call.enqueue(new Callback<DonationAskResponse>() {
            @Override
            public void onResponse(Call<DonationAskResponse> call, Response<DonationAskResponse> response) {
                DonationAskResponse category_example = response.body();
                List<DonationAskDatum> cateogry = category_example.getData().getData();
                final List<String> listSpinner = new ArrayList<String>();
                listSpinner.add("اختر فصيلة الدم");
                final List<Integer> listSpinnerIds = new ArrayList<>();

                for (int i = 0; i < cateogry.size(); i++) {
                    listSpinner.add(cateogry.get(i).getBloodType().getName());
                    listSpinnerIds.add(cateogry.get(i).getId());
                }
                final int[] id = {-1};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinnerBlood.setAdapter(adapter);
                spinnerBlood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        if (position != 0) {
                            id[0] = listSpinnerIds.get(position - 1);
                            String text = spinnerBlood.getSelectedItem().toString();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<DonationAskResponse> call, Throwable t) {

            }
        });
    }


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
