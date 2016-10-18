package com.progressoft.annotation.processor.copier;

class AnnotatedClassWithFields2Copier {

    AnnotatedClassWithFields2 copy(AnnotatedClassWithFields2 original) throws CloneNotSupportedException {
        AnnotatedClassWithFields2 result=new AnnotatedClassWithFields2();

        result.setStringValue(original.getStringValue());
        result.setIntegerValue(original.getIntegerValue());
        result.setDoubelValue(original.getDoubelValue());

        return result;
    }
}