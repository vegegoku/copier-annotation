package com.progressoft.annotation.processor;

class AnnotatedClassWithIgnoredFieldUsingAccessorsCopier {

    AnnotatedClassWithIgnoredFieldUsingAccessors copy(AnnotatedClassWithIgnoredFieldUsingAccessors original) throws CloneNotSupportedException {

        AnnotatedClassWithIgnoredFieldUsingAccessors result=new AnnotatedClassWithIgnoredFieldUsingAccessors();

        result.setIntegerValue(original.getIntegerValue());
        result.setDoubelValue(original.getDoubelValue());
        result.setFloatValue(original.getFloatValue());
        result.setBoolValue(original.isBoolValue());
        result.setBoolWraperValue(original.getBoolWraperValue());

        return result;
    }

}