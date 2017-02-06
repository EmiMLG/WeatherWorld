package es.telefonica.talentum.weatherworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;
import java.util.List;

import es.telefonica.talentum.weatherworld.R;
import es.telefonica.talentum.weatherworld.fragments.CityListFragment;
import es.telefonica.talentum.weatherworld.model.City;
import io.realm.Realm;
import io.realm.RealmResults;

public class CityListActivity extends AppCompatActivity {
    private static final int ADD_CIYT = 1;

    List<City> cityList = new LinkedList<>();

    // TODO: paso 2 crear CityListActivity vacía
    CityListFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        readListOfCitiesFromRealm();

        fragment = (CityListFragment) getSupportFragmentManager().findFragmentById(R.id.activity_city_list___city_list_fragment);
        fragment.setCities(cityList);
        fragment.refresh();

    }

    void readListOfCitiesFromRealm(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<City> cityResults = realm.where(City.class).findAllSorted("name");
        for(City city: cityResults){
            cityList.add(city);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        for (City city: cityList){
            realm.copyToRealmOrUpdate(city);
        }

        realm.commitTransaction();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_main_action_edit_city) {

            Intent i = new Intent(CityListActivity.this, EditCityActivity.class);
            startActivityForResult(i, ADD_CIYT);

            return true;
        } else if (id == R.id.menu_main_action_delete_all){
            ClearAllCities();

        }

        return super.onOptionsItemSelected(item);

    }

    private void ClearAllCities(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(City.class);
        realm.commitTransaction();

        cityList = new LinkedList<>();
        fragment.setCities(cityList);
        fragment.refresh();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_CIYT){
            if (resultCode == RESULT_OK){
                String cityName = data.getStringExtra("CITY_NAME");
                Log.d("CityName", cityName);

                City city = new City(cityName);
                cityList.add(city);

                fragment.setCities(cityList);
                fragment.refresh();

            }
        }
    }
}
