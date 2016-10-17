package com.progressoft.annotation.processor;

class EmptyAnnotatedClassCopier {

    EmptyAnnotatedClass copy(EmptyAnnotatedClass original) throws CloneNotSupportedException {
        EmptyAnnotatedClass result=new EmptyAnnotatedClass();
        return result;
    }
}