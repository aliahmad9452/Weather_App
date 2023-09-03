package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.Retrofit.RetrofitClientInstance;
import com.example.weatherapp.Retrofit.RetrofitServices;
import com.example.weatherapp.currentweather.MDCurrentWeather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
ImageView imageView;
TextView txtTempC,txtLastUpdated,txtWind,txtPressure,txtHumidity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        txtTempC = findViewById(R.id.txtTempC);
        txtLastUpdated = findViewById(R.id.txtLastUpdated);
        txtWind = findViewById(R.id.txtWind);
        txtHumidity = findViewById(R.id.txtHumidity);
        txtPressure = findViewById(R.id.txtPressure);
        getCurrentWeather();
    }
    private void getCurrentWeather() {
        RetrofitServices services = RetrofitClientInstance.getApiClient().create(RetrofitServices.class);
        Call<MDCurrentWeather> call = services.getCurrentWeather(getResources().getString(R.string.weather_api_key),
                "Lahore", "no");
        call.enqueue(new Callback<MDCurrentWeather>() {
            @Override
            public void onResponse(Call<MDCurrentWeather> call, Response<MDCurrentWeather> response) {
                if (response.isSuccessful()) {
                    MDCurrentWeather mdCurrentWeather = response.body();
                    MDCurrentWeather mdCurrentWeather1 = response.body();
                    txtTempC.setText("Temprature:  "+mdCurrentWeather.getCurrent().getTempC() + "°C");
                    txtWind.setText("Wind: "+mdCurrentWeather.getCurrent().getWindDir());
                    txtPressure.setText(mdCurrentWeather.getLocation().getName());
                    txtHumidity.setText("Humidity:  "+mdCurrentWeather.getCurrent().getHumidity());
                    txtLastUpdated.setText("Condition: " + mdCurrentWeather.getCurrent().getCondition().getText());
                    Glide.with(MainActivity.this).load("https:" + mdCurrentWeather.getCurrent().getCondition().getIcon()).into(imageView);
                }
            }

            @Override
            public void onFailure(Call<MDCurrentWeather> call, Throwable t) {

            }
        });
    }
}