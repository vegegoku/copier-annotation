package com.progressoft.annotation.processor;

class AnnotatedClassWithFields4Copier {

    AnnotatedClassWithFields4 copy(AnnotatedClassWithFields4 original) throws CloneNotSupportedException {
        AnnotatedClassWithFields4 result=new AnnotatedClassWithFields4();

        result.setStringValue(original.getStringValue());
        result.setIntegerValue(original.getIntegerValue());
        result.setDoubelValue(original.getDoubelValue());
        result.setFloatValue(original.getFloatValue());
        result.setBoolValue(original.isBoolValue());
        result.setBoolWraperValue(original.getBoolWraperValue());

        return result;
    }
}