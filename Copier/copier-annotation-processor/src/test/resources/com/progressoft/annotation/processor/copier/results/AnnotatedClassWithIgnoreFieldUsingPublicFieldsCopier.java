package com.progressoft.annotation.processor.copier;

class AnnotatedClassWithIgnoredFieldUsingPublicFieldsCopier {

    AnnotatedClassWithIgnoredFieldUsingPublicFields copy(AnnotatedClassWithIgnoredFieldUsingPublicFields original) throws CloneNotSupportedException {

        AnnotatedClassWithIgnoredFieldUsingPublicFields result=new AnnotatedClassWithIgnoredFieldUsingPublicFields();

        result.integerValue=original.integerValue;
        result.doubelValue=original.doubelValue;
        result.floatValue=original.floatValue;
        result.boolValue=original.boolValue;
        result.boolWraperValue=original.boolWraperValue;

        return result;
    }

}