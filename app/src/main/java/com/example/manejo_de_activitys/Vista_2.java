package com.example.manejo_de_activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Vista_2 extends AppCompatActivity {

    public static Float saldo = 1000000f;
    private TextView txt_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista2);
        txt_user = (TextView) findViewById(R.id.txt_usuario);
        mostrar_saldo();
        String mensaje = getIntent().getStringExtra("user");
        txt_user.setText("Bienvenido "+ mensaje);
    }

    public void salir(View obj_vista){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void Ver_saldo(View obj_vista){
       // mostrar_saldo();
        Intent i = new Intent(this, A_saldo.class); //creando nuevo actitivy
        // se le da un nombre al parametro que se mandará
        i.putExtra("saldooo", saldo);
        startActivity(i); // se inicia el activity indicado

    }

    public void Ingresar_dinero(View obj_vista) {
        Intent i = new Intent(this, A_deposito.class);
        startActivity(i);

    }

    public void Retirar_dinero(View obj_vista) {
        Intent i = new Intent(this, A_retiro.class);
        startActivity(i);
    }

    public void mostrar_saldo() {

        if (getIntent().getStringExtra("valor") != null) {
            String valor = getIntent().getStringExtra("valor");
            Toast.makeText(this, ""+valor, Toast.LENGTH_SHORT).show();
            saldo += Float.parseFloat(valor);
        }
    }
}