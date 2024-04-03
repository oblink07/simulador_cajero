package com.example.manejo_de_activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Objects;

public class A_saldo extends AppCompatActivity {

    public TextView txt_saldo;
    public Float saldo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asaldo);
        // Mostrar saldo del usuario
        txt_saldo = findViewById(R.id.txt_saldoo);
        saldo = getIntent().getFloatExtra("saldooo", 0.0f);
        txt_saldo.setText("$" + saldo);
        // toolbar
        Toolbar toolbar = findViewById(R.id.toolbar); // igualando parte grafica con parte logica
        setSupportActionBar(toolbar); // para saber con que barra estamos trabajando
        getSupportActionBar().setTitle(R.string.tb_saldo); // agrega el titulo a la barra
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // agrega botón de retroceso a la barra
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){ // id es igual al boton de retroceso
            finish(); // regresar al activity anterior
            return true; // se manejó correctamente
        }
        return super.onOptionsItemSelected(item); // manejo clase base
    } // funcionalidad botones tb


}