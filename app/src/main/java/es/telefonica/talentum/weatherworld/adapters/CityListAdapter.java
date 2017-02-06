package es.telefonica.talentum.weatherworld.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import es.telefonica.talentum.weatherworld.R;
import es.telefonica.talentum.weatherworld.model.City;
import es.telefonica.talentum.weatherworld.views.CityViewHolder;

public class CityListAdapter extends RecyclerView.Adapter<CityViewHolder> {
    private List<City> cityList;
    private LayoutInflater inflater;
    private Activity initialActivity;
    private Class<Activity> finalActivity; //La clase class representa a las demás clases

    public CityListAdapter(Context context, List<City> cityList){
        inflater = LayoutInflater.from(context);
        this.cityList = cityList;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("Adapter", "Creando una nueva lista en memoria");

        View view = inflater.inflate(R.layout.row_city, parent, false);

        CityViewHolder row = new CityViewHolder(view);

        return row;
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) { //crea tantos diseños como quepan en pantalla
        Log.d("ADAPTER", "Reciclando una fila antigua y pintandola en la posición" + position);

        final City city = cityList.get(position);
        holder.setCity(city);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: finish him!
                Intent intent = new Intent(initialActivity, finalActivity);
                intent.putExtra("CITY_DETAIL_NAME", city.getName());
                initialActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { //sirve para devolver el numero que hay que devolver
        if (cityList == null){
            Log.w("Retard", "some retard");
            return 0;
        }

        return cityList.size();
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public void configInitialAndFinalActivity(Activity initialActivity, Class<? extends Activity> finalActivity) {
        this.initialActivity = initialActivity;
        this.finalActivity = (Class<Activity>) finalActivity;
    }
}
