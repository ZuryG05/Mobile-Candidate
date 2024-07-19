package com.example.examen_android.modelo;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen_android.MainActivity2;
import com.example.examen_android.modelo.UserAdapter;
import com.example.examen_android.R;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private ArrayList<User> userList; // guarda listado
    private Context context; //accede al contexto del activity main que muestra el recycler

    public UserAdapter(ArrayList<User> userList, Context context) {
        this.userList = userList;
        this.context = context;

}
@NonNull
@Override
public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ){  //especifica el xml que se inflara en el recycler
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(itemView);
}

@Override
public void onBindViewHolder(@NonNull UserViewHolder holder, @SuppressLint("RecyclerView") int position){  //coloca en los componentes del item los textView
    User user = userList.get(position);
    if (user != null) {
        // Obtener el nombre completo del usuario
        String fullName = user.getName().getTitle() + " " +
                user.getName().getFirst() + " " +
               user.getName().getLast();

        // Establecer los datos en las vistas
        holder.textViewNombre.setText(fullName);
        holder.textViewEmail.setText(user.getEmail());
        holder.textViewGenero.setText(user.getGenero());


        holder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //obtener el usuario seleccionado
                User user = userList.get(position);

                Intent intent = new Intent(context, MainActivity2.class);
                intent.putExtra("selected_user", user);
                context.startActivity(intent);
            }
        });

    }

}
//metodo update list
    public void updateList(List<User> filteredList){
        userList.clear();
        userList.addAll(filteredList);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount(){ //especifica la cantidad de items que devuelve
        return userList.size();
}


    public class UserViewHolder extends RecyclerView.ViewHolder { //especificar que componentes se utilizaran paa mostrar datos del json
        TextView textViewNombre;
        TextView textViewEmail;
        TextView textViewGenero;
        public UserViewHolder(View itemView) {  //los enlazamos
            super(itemView);
            textViewNombre =itemView.findViewById(R.id.textViewNombre);
            textViewEmail =itemView.findViewById((R.id.textViewEmail));
            textViewGenero =itemView.findViewById(R.id.textViewGenero);


        }
}
}