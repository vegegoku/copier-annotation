package com.progressoft.annotation.processor;

import com.progressoft.annotation.processor.copier.CollectionCopy;
import com.progressoft.annotation.processor.copier.DeepCopy;
import com.progressoft.annotation.processor.copier.WithCopier;

import java.util.List;

@WithCopier(mode = WithCopier.Mode.PUBLIC_MEMEBERS)
public class AnnotatedClassWithVariablesAndCollection implements  Cloneable{

    public int intValeu;
    public String strValue;

    @CollectionCopy(initializer = CollectionCopy.ARRAY_LIST)
    @DeepCopy
    public List<AnnotatedClassWithVariablesAndCollection> samples;

    @Override
    protected AnnotatedClassWithVariablesAndCollection clone() throws CloneNotSupportedException {
        return new AnnotatedClassWithVariablesAndCollectionCopier().copy(this);
    }
}
