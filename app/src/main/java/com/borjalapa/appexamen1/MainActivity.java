package com.borjalapa.appexamen1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.borjalapa.appexamen1.pickers.DatePickerFragment;

public class MainActivity extends AppCompatActivity {
EditText user,password,email;
Button loginButton;
TextView fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.editTextUser);
        password = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.botonsalir);
        fecha = findViewById(R.id.fecha);
        email = findViewById(R.id.editTextEmail);
    }

    public void login(View view) {
        if(user.getText().toString().trim().equals("admin") && password.getText().toString().equals("admin") && email.getText().toString().equals("admin@gmail.com")){
            Intent i = new Intent(this, tabs.class);
            Bundle extras = new Bundle();
            extras.putString("Usuario",user.getText().toString().trim());
            extras.putString("Fecha de Nacimiento", fecha.getText().toString().trim());
            extras.putString("Email", email.getText().toString().trim());
            i.putExtras(extras);
            startActivity(i);
        }

        if(user.getText().toString().trim().equals("")){
            user.setError("Es necesario completar este campo");
        }

        if(password.getText().toString().trim().equals("")){
            password.setError("Es necesario completar este campo");
        }

        if(email.getText().toString().trim().equals("")){
            email.setError("Es necesario completar este campo");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (item.getItemId()){
            case R.id.salir:
                builder.setTitle("Salir de la app")
                        .setMessage("Pulsa si para salir de la aplicación")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                AlertDialog dialog = builder.show();

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void abrirDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment().newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                final String selectedDate = day+"/"+month+"/"+year;
                fecha.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "DATEPICKER");
    }
}