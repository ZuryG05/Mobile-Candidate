package com.example.examen_android;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen_android.interf.randomuserAPI;
import com.example.examen_android.modelo.RetrofitClient;
import com.example.examen_android.modelo.User;
import com.example.examen_android.modelo.UserAdapter;
import com.example.examen_android.modelo.resultados;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUsers;
    private UserAdapter userAdapter;
    private ArrayList<User> userList = new ArrayList<>();

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFilter = findViewById(R.id.btnFilter);
        Button btnFilterMale = findViewById(R.id.btnFilterMale);
        Button btnReset = findViewById(R.id.btnReset);
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
        recyclerViewUsers.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        // Método para cargar usuarios desde la API
           loadUsers();

        // Configurar onClickListener para botones de filtro
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterList("female");
            }
        });
        btnFilterMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterList("male");
            }
        });

        // Configurar onClickListener para el botón de reset
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadUsers();
            }
        });
    }

    private void loadUsers() {

        Call<resultados> call = RetrofitClient.getClient("https://randomuser.me/").create(randomuserAPI.class).getUser(50); // Obtener 50 usuarios
        call.enqueue(new Callback<resultados>() {
            @Override
            public void onResponse(Call<resultados> call, Response<resultados> response) {
                if(response.isSuccessful() && response.body() != null){
                    userList = response.body().getResults();
                    userAdapter = new UserAdapter(userList, MainActivity.this);
                    recyclerViewUsers.setAdapter(userAdapter);

                } else {
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<resultados> call, Throwable throwable) {
                Log.e(TAG, "Network error: ", throwable);  // Registrar el error completo en el Logcat
                Toast.makeText(MainActivity.this, "Network error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }



        });

    };

    private void filterList(String gender) {
        Call<resultados> call = RetrofitClient.getClient("https://randomuser.me/").create(randomuserAPI.class).getUsersByGender(50, gender);
        call.enqueue(new Callback<resultados>() {
            @Override
            public void onResponse(Call<resultados> call, Response<resultados> response) {
                if (response.isSuccessful() && response.body() != null) {
                    userList = response.body().getResults();
                    userAdapter.updateList(userList); // Actualizar adapter con la nueva lista filtrada
                } else {
                    Toast.makeText(MainActivity.this, "Error al filtrar", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<resultados> call, Throwable throwable) {
                Log.e(TAG, "Network error: ", throwable);  // Registrar el error completo en el Logcat
                Toast.makeText(MainActivity.this, "Network error: " , Toast.LENGTH_SHORT).show();
            }
        });

    }




    }



