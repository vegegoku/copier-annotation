package com.progressoft.annotation.processor.copier;

class AnnotatedClassWithPublicMemebersAndNoAccessoriesCopier {

    AnnotatedClassWithPublicMemebersAndNoAccessories copy(AnnotatedClassWithPublicMemebersAndNoAccessories original) throws CloneNotSupportedException {

        AnnotatedClassWithPublicMemebersAndNoAccessories result=new AnnotatedClassWithPublicMemebersAndNoAccessories();

        result.stringValue=original.stringValue;
        result.integerValue=original.integerValue;
        result.doubelValue=original.doubelValue;
        result.floatValue=original.floatValue;
        result.boolValue=original.boolValue;
        result.boolWraperValue=original.boolWraperValue;

        return result;
    }

}
