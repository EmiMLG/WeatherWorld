package es.telefonica.talentum.weatherworld.model;

//TODO: paso 1 - crear la clase de modelo con los datos de la ciudad

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class City extends RealmObject {
    @PrimaryKey private String name;

    public City(){

    }

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
