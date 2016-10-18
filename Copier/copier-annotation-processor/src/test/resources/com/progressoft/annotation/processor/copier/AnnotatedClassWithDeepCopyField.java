package com.progressoft.annotation.processor.copier;


@WithCopier
public class AnnotatedClassWithDeepCopyField {

    public class AnotherObject{
        @Override
        protected AnotherObject clone() throws CloneNotSupportedException {
            return new AnotherObject();
        }
    }

    private String stringValue;
    private int integerValue;
    private Double doubelValue;

    @DeepCopy
    private AnotherObject anotherObject;

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

    public AnotherObject getAnotherObject() {
        return anotherObject;
    }

    public void setAnotherObject(AnotherObject anotherObject) {
        this.anotherObject = anotherObject;
    }

    @Override
    protected AnnotatedClassWithDeepCopyField clone() throws CloneNotSupportedException {
        return new AnnotatedClassWithDeepCopyFieldCopier().copy(this);
    }
}
