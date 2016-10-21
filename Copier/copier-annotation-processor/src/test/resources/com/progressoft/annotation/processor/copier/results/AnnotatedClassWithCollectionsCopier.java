package com.progressoft.annotation.processor.copier;

import java.util.Set;

import com.progressoft.annotation.processor.copier.AnnotatedClassWithCollections.AnotherObject;

import java.util.List;

import java.util.Map;

class AnnotatedClassWithCollectionsCopier {

    AnnotatedClassWithCollections copy(AnnotatedClassWithCollections original) throws CloneNotSupportedException {
        AnnotatedClassWithCollections result=new AnnotatedClassWithCollections();

        if(java.util.Objects.nonNull(original.getAnotherObjects())){
            result.setAnotherObjects(new java.util.ArrayList<>());
            for (AnotherObject item :original.getAnotherObjects()) {
                result.getAnotherObjects().add(item.clone());
            }
        }

        if(java.util.Objects.nonNull(original.getIntegersList())){
            result.setIntegersList(new java.util.ArrayList<>());
            for(Integer item:original.getIntegersList()){
                result.getIntegersList().add(item);
            }
        }

        if(java.util.Objects.nonNull(original.getStringsMap())){
            result.setStringsMap(new java.util.HashMap<>());
            for(java.util.Map.Entry<String,String> entry:original.getStringsMap().entrySet()){
                result.getStringsMap().put(entry.getKey(), entry.getValue());
            }
        }

        if(java.util.Objects.nonNull(original.getStringsSet())){
            result.setStringsSet(new java.util.HashSet<>());
            for(String item:original.getStringsSet()){
                result.getStringsSet().add(item);
            }
        }

        if(java.util.Objects.nonNull(original.getLinkedList())){
            result.setLinkedList(new java.util.LinkedList<>());
            for(String item:original.getLinkedList()){
                result.getLinkedList().add(item);
            }
        }

        return result;
    }
}
