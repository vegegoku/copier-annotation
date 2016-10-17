package com.progressoft.annotation.processor;

class SimpleClassCopier {

    SimpleClass copy(SimpleClass original)throws CloneNotSupportedException {
        SimpleClass result=new SimpleClass();

        result.setValue(original.getValue());
        result.setDoubleValue(original.getDoubleValue());

        return result;
    }
}