package com.progressoft.annotation.processor;

@WithCopier
public class AnnotatedClassWithSingleField {

    private int integerField;

    public int getIntegerField() {
        return integerField;
    }

    public void setIntegerField(int integerField) {
        this.integerField = integerField;
    }
}
