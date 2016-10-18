package com.progressoft.annotation.processor.copier;

import java.util.List;
import java.util.Map;
import java.util.Set;

@WithCopier
public class AnnotatedClassWithCollections {

    public class AnotherObject{
        @Override
        protected AnotherObject clone() throws CloneNotSupportedException {
            return new AnotherObject();
        }
    }

    @CollectionCopy(initializer = CollectionCopy.ARRAY_LIST)
    @DeepCopy
    private List<AnotherObject> anotherObjects;

    @CollectionCopy(initializer = CollectionCopy.ARRAY_LIST)
    private List<Integer> integersList;

    @CollectionCopy(initializer = CollectionCopy.HASH_MAP)
    private Map<String, String> stringsMap;

    @CollectionCopy(initializer = CollectionCopy.HASH_SET)
    private Set<String> stringsSet;

    @CollectionCopy(initializer = CollectionCopy.LINKED_LIST)
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
