package com.progressoft.annotation.processor.copier;

@WithCopier
public class AnnotatedClassWithFields3 {

    private String stringValue;
    private int integerValue;
    private Double doubelValue;
    private float floatValue;

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(int integerValue) {
        this.integerValue = integerValue;
    }

    public Double getDoubelValue() {
        return doubelValue;
    }

    public void setDoubelValue(Double doubelValue) {
        this.doubelValue = doubelValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }
}
