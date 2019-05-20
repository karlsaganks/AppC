package com.example.appc;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String app = "MIAPPC";

    Tablero juego;
    double tiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pintarTablero();
    }

    // Dibujar Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // Acciones del Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.comenzar:
                Log.i(app, "Comenzar Juego");
                //Intent nuevo_inicio = new Intent(this, MainActivity.class);
                //startActivity(nuevo_inicio);
                //this.finish();
                pintarTablero();
                break;
            case R.id.salir:
                Log.i(app,"Salir");
                salir();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void pintarTablero(){
        juego = new Tablero();
        Log.i(app, "Filas: "+ juego.getFilas() + " Elementos: "+ juego.getElementos()  );
        LinearLayout padre = (LinearLayout) findViewById(R.id.root);
        padre.removeAllViews();
        int filas = juego.getFilas();
        for (int i = 0; i < filas; i++){

            LinearLayout hijo = new LinearLayout(this);
            LinearLayout.LayoutParams parametrosPadre = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, 0, 1F);
            hijo.setOrientation( LinearLayout.HORIZONTAL);
            hijo.setLayoutParams( parametrosPadre);
            hijo.setVisibility(View.VISIBLE);

            Box[] cajas = juego.getBoxEnFila(i);
            int cols = cajas.length;
            Log.i(app, String.format("Fila: %d - Columnas: %d ", i+1, cols ) );
            for (int j = 0; j < cols; j++){

                LinearLayoutCustom nieto = new LinearLayoutCustom(this);
                LinearLayoutCustom.LayoutParams parametrosNieto = new LinearLayoutCustom.LayoutParams( 0, LinearLayout.LayoutParams.MATCH_PARENT, 1F);
                nieto.setId(cajas[j].getId());
                nieto.setOrientation( LinearLayout.VERTICAL);
                nieto.setLayoutParams( parametrosNieto);
                nieto.setBackgroundColor( Color.parseColor( cajas[j].getColor()));
                nieto.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dibujar(v);
                    }
                });
                nieto.setVisibility(View.VISIBLE);
                hijo.addView(nieto);
               // nieto.setOnClickListener((box) -> { dibujar(box);});
            }
            padre.addView(hijo);
        }
        tiempo = System.currentTimeMillis();
    }

    public void dibujar(View v){
        LinearLayoutCustom box = (LinearLayoutCustom) v;
        if (!box.isUsado()){
            box.setBackgroundColor( getResources().getColor( R.color.negro));
            box.setUsado(true);
            juego.setTocados( juego.getTocados() +1);
            Log.i(app, "Id: " + v.getId() + " Tocados: " + juego.getTocados() + "/"+ juego.getElementos() );
            if(juego.getElementos() == juego.getTocados()) {
                //salir();
                pintarTablero();
            }
        }
    }

    private void salir(){
        String msg = String.format("Duracion: %1$.3f segundos",((double)System.currentTimeMillis() - tiempo) / 1000d);
        Log.i(app, msg);
        Toast t = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
        t.setMargin(50,50);
        t.show();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();
        } else {
            this.finish();
        }
    }
}
