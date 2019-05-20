package com.example.appc;

class Box {
    private int id;
    private String color;

    public Box(){
        int unColor = ColorAleatorio.getOneColor();
        this.color = ColorAleatorio.getOneColorHex(unColor);
        this.id = unColor * -1;
    }

    @Override
    public String toString() {
        return this.id + " - " +this.color;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

}