package com.somnel.httpcatsapi;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    final String baseUrl = "https://meowfacts.herokuapp.com/";
    String lang = "por-br";

    EditText editQtd;
    TextView tvResultado;
    Button btnBuscar;

    Retrofit retrofit;
    IFatosGatos service;

    private interface IFatosGatos {
        @GET("/")
        Call<GatoFatos> getFatos(@Query("lang") String lang, @Query("count") String count);
    }

    public class GatoFatos {
        private List<String> data;
        public List<String> getData() {
            return data;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(IFatosGatos.class);
        editQtd = findViewById(R.id.editQuantidade);
        tvResultado = findViewById(R.id.tvResultado);
        btnBuscar = findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(v -> getGatoFato());
    }


    public void getGatoFato() {
        String quantidade = editQtd.getText().toString();

        if(TextUtils.isEmpty(quantidade) || quantidade.isBlank()) {
            Toast.makeText(this, "Por favor preencha o campo de quantidade", Toast.LENGTH_SHORT).show();
            return;
        }

        if(Integer.parseInt(quantidade) <= 0) {
            Toast.makeText(this, "A quantidade deve ser maior que 0", Toast.LENGTH_SHORT).show();
            return;
        }

        service.getFatos(lang, quantidade).enqueue(new Callback<GatoFatos>() {
            @Override
            public void onResponse(Call<GatoFatos> call, Response<GatoFatos> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<String> fatos = response.body().getData();
                    tvResultado.setText(TextUtils.join("\n\n", fatos));
                } else {
                    tvResultado.setText("Falha ao buscar fatos");
                }
            }

            @Override
            public void onFailure(Call<GatoFatos> call, Throwable t) {
                tvResultado.setText("Erro: " + t.getMessage());
            }
        });
    }
}