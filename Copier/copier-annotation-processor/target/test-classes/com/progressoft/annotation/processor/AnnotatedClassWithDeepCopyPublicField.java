package com.progressoft.annotation.processor;


@WithCopier(mode = WithCopier.Mode.PUBLIC_MEMEBERS)
public class AnnotatedClassWithDeepCopyPublicField {

    public class AnotherObject{
        @Override
        protected AnotherObject clone() throws CloneNotSupportedException {
            return new AnotherObject();
        }
    }

    public String stringValue;
    public int integerValue;
    public Double doubelValue;

    @DeepCopy
    public AnotherObject anotherObject;

    @Override
    protected AnnotatedClassWithDeepCopyPublicField clone() throws CloneNotSupportedException {
        return new AnnotatedClassWithDeepCopyPublicFieldCopier().copy(this);
    }
}
