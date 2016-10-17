package com.progressoft.annotation.processor;

import java.util.*;

@WithCopier
public class AnnotatedClassWithCollections {

    public class AnotherObject{
        @Override
        protected AnotherObject clone() throws CloneNotSupportedException {
            return new AnotherObject();
        }
    }

    @CollectionCopy(initializer = CollectionCopy.ARRAY_LIST, genericType = "com.progressoft.annotation.processor.AnnotatedClassWithCollections.AnotherObject")
    @DeepCopy
    private List<AnotherObject> anotherObjects;

    @CollectionCopy(initializer = CollectionCopy.ARRAY_LIST, genericType = "java.lang.Integer")
    private List<Integer> integersList;

    @CollectionCopy(initializer = CollectionCopy.HASH_MAP, genericType = "java.lang.String,java.lang.String")
    private Map<String, String> stringsMap;

    @CollectionCopy(initializer = CollectionCopy.HASH_SET, genericType = "java.lang.String")
    private Set<String> stringsSet;

    @CollectionCopy(initializer = CollectionCopy.LINKED_LIST, genericType = "java.lang.String")
    private List<String> linkedList;

    public List<AnotherObject> getAnotherObjects() {
        return anotherObjects;
    }

    public void setAnotherObjects(List<AnotherObject> anotherObjects) {
        this.anotherObjects = anotherObjects;
    }

    public List<Integer> getIntegersList() {
        return integersList;
    }

    public void setIntegersList(List<Integer> integersList) {
        this.integersList = integersList;
    }

    public Map<String, String> getStringsMap() {
        return stringsMap;
    }

    public void setStringsMap(Map<String, String> stringsMap) {
        this.stringsMap = stringsMap;
    }

    public Set<String> getStringsSet() {
        return stringsSet;
    }

    public void setStringsSet(Set<String> stringsSet) {
        this.stringsSet = stringsSet;
    }

    public List<String> getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(List<String> linkedList) {
        this.linkedList = linkedList;
    }
}
