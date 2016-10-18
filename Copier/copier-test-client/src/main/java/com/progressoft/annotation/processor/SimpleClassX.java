package com.progressoft.annotation.processor;

import com.progressoft.annotation.processor.copier.WithCopier;

@WithCopier
public class SimpleClassX implements Cloneable{

    private int x;
    private int y;
    private int z;
    private String a;
    private String b;
    private String c;
    private Double d;
    private double e;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public Double getD() {
        return d;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    @Override
    public SimpleClassX clone() throws CloneNotSupportedException {
        return new SimpleClassXCopier().copy(this);
    }
}
