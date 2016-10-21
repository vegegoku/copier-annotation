package com.progressoft.annotation.processor.copier;

import java.util.Set;

import java.util.List;

import java.util.Map;

import com.progressoft.annotation.processor.copier.AnnotatedClassWithPublicCollections.AnotherObject;

class AnnotatedClassWithPublicCollectionsCopier {

    AnnotatedClassWithPublicCollections copy(AnnotatedClassWithPublicCollections original) throws CloneNotSupportedException {
        AnnotatedClassWithPublicCollections result=new AnnotatedClassWithPublicCollections();

        if(java.util.Objects.nonNull(original.anotherObjects)){
            result.anotherObjects=new java.util.ArrayList<>();
            for (AnotherObject item :original.anotherObjects) {
                result.anotherObjects.add(item.clone());
            }
        }

        if(java.util.Objects.nonNull(original.integersList)){
            result.integersList=new java.util.ArrayList<>();
            for(Integer item:original.integersList){
                result.integersList.add(item);
            }
        }

        if(java.util.Objects.nonNull(original.stringsMap)){
            result.stringsMap=new java.util.HashMap<>();
            for(java.util.Map.Entry<String,String> entry:original.stringsMap.entrySet()){
                result.stringsMap.put(entry.getKey(), entry.getValue());
            }
        }

        if(java.util.Objects.nonNull(original.stringsSet)){
            result.stringsSet=new java.util.HashSet<>();
            for(String item:original.stringsSet){
                result.stringsSet.add(item);
            }
        }

        if(java.util.Objects.nonNull(original.linkedList)){
            result.linkedList=new java.util.LinkedList<>();
            for(String item:original.linkedList){
                result.linkedList.add(item);
            }
        }

        return result;
    }
}
