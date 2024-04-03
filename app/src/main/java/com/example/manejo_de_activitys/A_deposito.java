package com.example.manejo_de_activitys;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class A_deposito extends AppCompatActivity {

    // Variables para mantener un registro del estado de los TextView
    private TextView txt_anterior; // almacena el primer tv con el que se interactue
    private Drawable fondo_original; // almacena el brodeado original
    private TextView txt_primero, txt_segundo, txt_tercero, txt_cuarto, txt_quinto;
    private EditText txt_otro; // otro valor ingresado por el usuario
    private String str_valor = "";
    private boolean x = true; // evitar error primer click


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adeposito);
        // igualando parte logica con parte grafica
        txt_primero = findViewById(R.id.txt_primero);
        txt_segundo = findViewById(R.id.txt_segundo);
        txt_tercero = findViewById(R.id.txt_tercero);
        txt_cuarto = findViewById(R.id.txt_cuarto);
        txt_quinto = findViewById(R.id.txt_quinto);
        txt_otro = findViewById(R.id.txt_otro);
        // toolbar
        Toolbar toolbar = findViewById(R.id.tb_deposito); // igualando parte grafica con parte logica
        setSupportActionBar(toolbar); // para saber con que TB estamos trabajando
        getSupportActionBar().setTitle(R.string.btn_deposito); // agrega el titulo al TB
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // agrega botón de retroceso al TB
        // Guardar el fondo original del TextView
        fondo_original = txt_primero.getBackground();

        // oyentes de click
        txt_primero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarFondo(txt_primero);
                str_valor = "" + 50000;
                txt_otro.setText("");
                x = false;
            }
        });
        txt_segundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarFondo(txt_segundo);
                str_valor = "" + 100000;
                txt_otro.setText("");
                x = false;
            }
        });
        txt_tercero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarFondo(txt_tercero);
                str_valor = "" + 200000;
                txt_otro.setText("");
                x = false;
            }
        });
        txt_cuarto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarFondo(txt_cuarto);
                str_valor = "" + 500000;
                txt_otro.setText("");
                x = false;
            }
        });
        txt_quinto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarFondo(txt_quinto);
                str_valor = "" + 1000000;
                txt_otro.setText("");
                x = false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // oyente del teclado
        txt_otro.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    // Realiza la acción cuando se presiona la tecla Enter
                    str_valor = "0";
                    x = true;
                    confirmar(v);
                    return true; // Indica que el evento ha sido manejado por este oyenye
                }
                return false; // Permite que otros oyentes de teclado manejen el evento
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // acciones cuando la actividad está a punto de ser pausada, pero aún es visible
    }

    @Override
    protected void onStop() {
        super.onStop();
        // acciones cuando la actividad ya no está visible para el usuario
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Aquí puedes realizar acciones cuando la actividad está a punto de ser destruida y liberar recursos
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) { // si se clickea el boton de retroceso
            finish();
            return true; // se manejó correctamente
        }
        return super.onOptionsItemSelected(item); // manejo clase base
    } // funcionalidad botones tb

    private void cambiarFondo(TextView txt_actual) {
        if (txt_anterior != null) {
            // Devolver el TextView anterior a su estado original
            txt_anterior.setBackground(fondo_original);
        }
        // Cambiar el fondo del TextView actual
        Drawable drawable = getResources().getDrawable(R.drawable.border); // borde de seleccion
        txt_actual.setBackground(drawable);
        // Actualizar el TextView seleccionado anteriormente
        txt_anterior = txt_actual;
    } // cambiar fondo de tv seleccionado

    public void confirmar(View obj_vista) {
        if (x) {
            str_valor = txt_otro.getText().toString();
        } // manejo de error primer click

        if (str_valor.equals("") || !str_valor.matches("[0-9]+")) {
            Toast.makeText(this, "Ingrese un valor válido", Toast.LENGTH_SHORT).show();
        } else {
            final Float valor = Float.parseFloat(str_valor);
            if (valor <= 5000 || valor > 2000000) {
                Toast.makeText(this, "Ingrese un valor dentro del rango válido", Toast.LENGTH_SHORT).show();
            } else {
                // Mensaje de espera
                final ProgressDialog obj_carga = new ProgressDialog(this);
                obj_carga.setMessage("Procesando transacción...");
                obj_carga.setCancelable(false); // evita que se pueda camcelar el mensaje
                obj_carga.show();

                // Simulando un proceso de transacción con un Handler y un tiempo de espera
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Una vez completado el proceso de transacción, actualiza el saldo y cierra el ProgressDialog
                        Vista_2.saldo += valor;
                        obj_carga.dismiss();
                        // mensaje de éxito
                        Toast.makeText(A_deposito.this, "Transacción exitosa", Toast.LENGTH_SHORT).show();
                        // Cierra la actividad actual
                        finish();
                    }
                }, 2000); // 2000 milisegundos (2 segundos) de tiempo de espera simulado
            }
        }
    }
}