package com.progressoft.annotation.processor;

@WithCopier
public class SimpleClass implements Cloneable{

    private int value;
    private double doubleValue;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleClass that = (SimpleClass) o;

        if (value != that.value) return false;
        return Double.compare(that.doubleValue, doubleValue) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = value;
        temp = Double.doubleToLongBits(doubleValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public SimpleClass clone() throws CloneNotSupportedException {
        return new SimpleClassCopier().copy(this);
    }
}
