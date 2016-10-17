package com.progressoft.annotation.processor;

@WithCopier(mode = WithCopier.Mode.GETTERS_SETTERS)
public class AnnotatedClassWithIgnoredFieldUsingAccessors {

    @IgnoreCopy
    private String stringValue;
    private int integerValue;
    private Double doubelValue;
    private float floatValue;
    private boolean boolValue;
    private Boolean boolWraperValue;

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

    public boolean isBoolValue() {
        return boolValue;
    }

    public void setBoolValue(boolean boolValue) {
        this.boolValue = boolValue;
    }

    public Boolean getBoolWraperValue() {
        return boolWraperValue;
    }

    public void setBoolWraperValue(Boolean boolWraperValue) {
        this.boolWraperValue = boolWraperValue;
    }
}
