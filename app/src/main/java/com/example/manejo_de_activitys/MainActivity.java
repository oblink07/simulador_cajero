package com.example.manejo_de_activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txt_user, txt_pass;
    private String user = "3134437119", pass = "123456";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_user = (EditText) findViewById(R.id.txt_user);
        txt_pass = (EditText) findViewById(R.id.txt_pass);
    }

    public void validar(View obj_vista){
        String str_user = txt_user.getText().toString();
        String str_pass = txt_pass.getText().toString();

        if(str_user.isEmpty() || str_pass.isEmpty()){
            Toast.makeText(this, "Por favor rellene ambos campos", Toast.LENGTH_LONG).show();
        }else if(!str_user.equals(user) || !str_pass.equals(pass)){
            Toast.makeText(this, "Usuario o Contraseña incorrecta", Toast.LENGTH_LONG).show();
            txt_user.setText("");
            txt_pass.setText("");
            txt_user.requestFocus();
        }else {
            Intent i = new Intent(this, Vista_2.class); //creando nuevo actitivy
            // se le da un nombre al parametro que se mandará
            i.putExtra("user", "Luis Arévalo");

            startActivity(i); // se inicia el activity indicado
        }


    }
}