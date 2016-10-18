package com.progressoft.annotation.processor.copier;

class AnnotatedClassWithVariablesAndCollectionCopier{

    AnnotatedClassWithVariablesAndCollection copy(AnnotatedClassWithVariablesAndCollection original) throws CloneNotSupportedException{
        AnnotatedClassWithVariablesAndCollection result=new AnnotatedClassWithVariablesAndCollection();

        result.intValeu=original.intValeu;
        result.strValue=original.strValue;

        if(java.util.Objects.nonNull(original.samples)){
            result.samples=new java.util.ArrayList<>();
            for (com.progressoft.annotation.processor.copier.AnnotatedClassWithVariablesAndCollection item :original.samples) {
                result.samples.add(item.clone());
            }
        }

        return result;
    }

}
