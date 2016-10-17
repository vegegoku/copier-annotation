package com.progressoft.annotation.processor;

class AnnotatedClassWithFields3Copier {

    AnnotatedClassWithFields3 copy(AnnotatedClassWithFields3 original) throws CloneNotSupportedException {
        AnnotatedClassWithFields3 result=new AnnotatedClassWithFields3();

        result.setStringValue(original.getStringValue());
        result.setIntegerValue(original.getIntegerValue());
        result.setDoubelValue(original.getDoubelValue());
        result.setFloatValue(original.getFloatValue());

        return result;
    }
}