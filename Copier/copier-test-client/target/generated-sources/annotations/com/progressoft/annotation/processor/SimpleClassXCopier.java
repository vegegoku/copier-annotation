package com.progressoft.annotation.processor;

class SimpleClassXCopier {

    SimpleClassX copy(SimpleClassX original)throws CloneNotSupportedException {
        SimpleClassX result=new SimpleClassX();

        result.setX(original.getX());
        result.setY(original.getY());
        result.setZ(original.getZ());
        result.setA(original.getA());
        result.setB(original.getB());
        result.setC(original.getC());
        result.setD(original.getD());
        result.setE(original.getE());

        return result;
    }
}