package com.mpizzzle.mandelbrotviewer;

/**
 * Created by mpizzzle on 31/01/18.
 */

public class Complex {
    private double natural;
    private double imaginary;

    public Complex() {
        this.natural = 0d;
        this.imaginary = 0d;
    }

    public Complex(double natural, double imaginary) {
        this.natural = natural;
        this.imaginary = imaginary;
    }

    public void setNatural(double n) {
        this.natural = n;
    }

    public void setImaginary(double i) {
        this.imaginary = i;
    }

    public double getNatural() {
        return natural;
    }

    public double getImaginary() {
        return imaginary;
    }
}
