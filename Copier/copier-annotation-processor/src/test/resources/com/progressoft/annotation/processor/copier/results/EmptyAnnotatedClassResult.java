package com.progressoft.annotation.processor.copier;

class EmptyAnnotatedClassCopier {

    EmptyAnnotatedClass copy(EmptyAnnotatedClass original) throws CloneNotSupportedException {
        EmptyAnnotatedClass result=new EmptyAnnotatedClass();
        return result;
    }
}