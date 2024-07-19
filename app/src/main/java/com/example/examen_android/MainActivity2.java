package com.example.examen_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.examen_android.MainActivity;
import com.example.examen_android.MainActivity2;
import com.example.examen_android.R;
import com.example.examen_android.modelo.Street;
import com.example.examen_android.modelo.User;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private TextView textViewNombre, textViewEmail, textViewGenero, textViewTelefono, textViewAddress;
    private ImageView imageViewUser;
    private Button btnRegresar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);

        // Inicializar vistas
        textViewNombre = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewGenero = findViewById(R.id.textViewGender);
        textViewAddress = findViewById(R.id.textViewAddress);
        textViewTelefono = findViewById(R.id.textViewPhone);
        imageViewUser = findViewById(R.id.imageViewUser);
        btnRegresar = findViewById(R.id.btnRegresar);

        //obtener el objeto user del intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selected_user")) {
            User selectedUser = (User) intent.getParcelableExtra("selected_user");
            if (selectedUser != null) {
                // Mostrar los datos del usuario en los TextViews correspondientes
                textViewNombre.setText(selectedUser.getName().getTitle() + " "+ selectedUser.getName().getFirst() + " " + selectedUser.getName().getLast());
                textViewEmail.setText("Email: " + selectedUser.getEmail());
                textViewGenero.setText("Genero: " + selectedUser.getGenero());
                textViewTelefono.setText("Telefono: " + selectedUser.getTelefono());
                textViewAddress.setText("Dirección: " + selectedUser.getLocation().getStreet().getName()+" "+
                        selectedUser.getLocation().getCity() + " " +
                        selectedUser.getLocation().getState() + " " +
                        selectedUser.getLocation().getCountry() + " " +
                        selectedUser.getLocation().getPostcode()); // + " "+
                     //   selectedUser.getLocation().getTimezone().getOffset());
                    // Cargar imagen del usuario
                    Glide.with(this)
                            .load(selectedUser.getPicture().getLarge())
                            .placeholder(R.drawable.placeholder_image) // Placeholder mientras carga la imagen
                            .error(R.drawable.error_image) // Imagen a mostrar si hay un error al cargar
                            .into(imageViewUser);

                    // Configurar el botón para volver a la lista
                    btnRegresar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish(); // Regresar a la actividad anterior (MainActivity)
                        }
                    });
                }


            }
        }

    }