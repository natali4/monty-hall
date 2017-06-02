package com.avega;

public class Box {
    private int number;
    private boolean isOpen;

    public Box(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void open() {
        this.isOpen = true;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
