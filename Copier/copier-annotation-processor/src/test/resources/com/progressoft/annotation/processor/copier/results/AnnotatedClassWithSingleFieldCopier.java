package com.progressoft.annotation.processor.copier;

class AnnotatedClassWithSingleFieldCopier {

    AnnotatedClassWithSingleField copy(AnnotatedClassWithSingleField original) throws CloneNotSupportedException {
        AnnotatedClassWithSingleField result=new AnnotatedClassWithSingleField();
        result.setIntegerField(original.getIntegerField());
        return result;
    }
}