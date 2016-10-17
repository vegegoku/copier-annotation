package com.progressoft.annotation.processor;

class AnnotatedClassWithDeepCopyFieldCopier {

    AnnotatedClassWithDeepCopyField copy(AnnotatedClassWithDeepCopyField original) throws CloneNotSupportedException {
        AnnotatedClassWithDeepCopyField result=new AnnotatedClassWithDeepCopyField();

        result.setStringValue(original.getStringValue());
        result.setIntegerValue(original.getIntegerValue());
        result.setDoubelValue(original.getDoubelValue());
        if(java.util.Objects.nonNull(original.getAnotherObject())) {
            result.setAnotherObject(original.getAnotherObject().clone());
        }

        return result;
    }
}