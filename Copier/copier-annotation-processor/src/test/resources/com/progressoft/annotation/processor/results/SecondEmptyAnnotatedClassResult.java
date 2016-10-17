package com.progressoft.annotation.processor;

class SecondEmptyAnnotatedClassCopier {

    SecondEmptyAnnotatedClass copy(SecondEmptyAnnotatedClass original) throws CloneNotSupportedException {
        SecondEmptyAnnotatedClass result=new SecondEmptyAnnotatedClass();
        return result;
    }
}