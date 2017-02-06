package es.telefonica.talentum.weatherworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import es.telefonica.talentum.weatherworld.R;

public class EditCityActivity extends AppCompatActivity {

   EditText citynameEditText;
    Button saveCityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_city);

        citynameEditText = (EditText) findViewById(R.id.activity_edit_city_city_name);
        saveCityButton = (Button) findViewById(R.id.activity_edit_city___save_city);

        saveCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName = citynameEditText.getText().toString();
                if (cityName.length() > 0){
                    Intent i = new Intent();
                    i.putExtra("CITY_NAME", cityName);
                    setResult(RESULT_OK, i);
                }else{
                    setResult(RESULT_CANCELED);
                }
                finish();
            }
        });
    }
}
