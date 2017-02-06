package es.telefonica.talentum.weatherworld.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import es.telefonica.talentum.weatherworld.R;
import es.telefonica.talentum.weatherworld.activities.CityDetailActivity;
import es.telefonica.talentum.weatherworld.adapters.CityListAdapter;
import es.telefonica.talentum.weatherworld.model.City;

public class CityListFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<City> cities;

    public CityListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_city_list_city_list);

        return view;
    }

    public List<City> getCities() {
        return this.cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void refresh(){
        if (getCities() == null){
            return;
        }

        CityListAdapter adapter = new CityListAdapter(getContext(), getCities());

        adapter.configInitialAndFinalActivity(getActivity(), CityDetailActivity.class);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
