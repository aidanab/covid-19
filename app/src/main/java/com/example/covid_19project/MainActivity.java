package com.example.covid_19project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.covid_19project.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    private TextView textView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textViewResult);
        progressBar = findViewById(R.id.progressBar);

        getDataFromApi();
    }

    private void getDataFromApi() {

        showLoading(true);


        ApiService.endpoint().getData()
                .enqueue(new Callback<List<MainModel>>() {
                    @Override
                    public void onResponse(Call<List<MainModel>> call, Response<List<MainModel>> response) {
                        showLoading(false);
                        if (response.isSuccessful()) {
//                            List<MainModel> results = response.body();
//                            showResult( result );
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MainModel>> call, Throwable t) {
                        Log.d(TAG, t.toString());
                        showLoading(false);
                    }
                });

    }

    private void showLoading(boolean loading){
        if(loading){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void showResult(List<MainModel> mainModels){
        MainModel result = mainModels.get(0);
        textView.setText(
            "positif: " + result.getPositif() + "\nsembuh: " + result.getSembuh()+
                    "\nmeninggal: " + result.getMeninggal() + "\ndirawat: " + result.getDirawat()
        );
    }
}