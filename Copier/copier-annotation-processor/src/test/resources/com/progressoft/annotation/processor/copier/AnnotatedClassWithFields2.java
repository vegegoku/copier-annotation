package com.progressoft.annotation.processor.copier;

@WithCopier
public class AnnotatedClassWithFields2 {

    private String stringValue;
    private int integerValue;
    private Double doubelValue;

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
}
