package com.example.mywsclientretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mywsclientretrofit.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        binding.btnSearchCEP.setOnClickListener(view -> {
            Call<CEP> call = new RetrofitClient().getCEPService().getCEP(binding.etCEP.getText().toString());
            call.enqueue(new Callback<CEP>() {
                @Override
                public void onResponse(Call<CEP> call, Response<CEP> response) {
                    CEP cep = response.body();
                    if (!cep.toString().equals("")) {
                        binding.tvResult.setText(cep.toString());
                    } else {
                        System.out.println("Error");
                    }
                }

                @Override
                public void onFailure(Call<CEP> call, Throwable t) {
                    Log.e("CEPService", "Erro ao buscar o CEP " + t.getMessage());
                }
            });
        });
    }
}