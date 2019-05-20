package com.example.appc;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {
    private final int MAX_FILAS = 10;
    private final int MAX_COLS = 10;

    private int elementos;
    private int filas;
    private int tocados;
    private ArrayList<Box[]> caja;

    public Tablero() {
        this.filas = hasta(MAX_FILAS);
        this.elementos = 0;
        this.tocados = 0;
        this.caja = new ArrayList<Box[]>(this.filas);
        this.generar(this.filas);
    }
    private void generar(int filas){
        for( int i = 0; i< filas; i++){
            int cols = hasta(MAX_COLS);
            Box[] colCaja = new Box[cols];
            for( int j = 0; j < cols; j++){
                colCaja[j] = new Box();
                this.elementos += 1;
            }
            this.caja.add(colCaja);
        }
    }

    public ArrayList<Box[]> getCaja(){
        return caja;
    }

    public int getElementos(){
        return this.elementos;
    }
    public int getFilas(){
        return this.filas;
    }

    public Box[] getBoxEnFila(int fila){
        return caja.get(fila);
    }

    private int hasta(int mxm) {
        int res = -1;
        do {
            res = new Random().nextInt(mxm);
        } while( res < 1);
        return res;
    }

    public int getTocados() {
        return tocados;
    }
    public void setTocados(int tocados) {
        this.tocados = tocados;
    }
}

