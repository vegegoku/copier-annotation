package com.progressoft.annotation.processor.copier;

class AnnotatedClassWithPublicCollectionsCopier {

    AnnotatedClassWithPublicCollections copy(AnnotatedClassWithPublicCollections original) throws CloneNotSupportedException {
        AnnotatedClassWithPublicCollections result=new AnnotatedClassWithPublicCollections();

        if(java.util.Objects.nonNull(original.anotherObjects)){
            result.anotherObjects=new java.util.ArrayList<>();
            for (com.progressoft.annotation.processor.copier.AnnotatedClassWithPublicCollections.AnotherObject item :original.anotherObjects) {
                result.anotherObjects.add(item.clone());
            }
        }

        if(java.util.Objects.nonNull(original.integersList)){
            result.integersList=new java.util.ArrayList<>();
            for(java.lang.Integer item:original.integersList){
                result.integersList.add(item);
            }
        }

        if(java.util.Objects.nonNull(original.stringsMap)){
            result.stringsMap=new java.util.HashMap<>();
            for(java.util.Map.Entry<java.lang.String,java.lang.String> entry:original.stringsMap.entrySet()){
                result.stringsMap.put(entry.getKey(), entry.getValue());
            }
        }

        if(java.util.Objects.nonNull(original.stringsSet)){
            result.stringsSet=new java.util.HashSet<>();
            for(java.lang.String item:original.stringsSet){
                result.stringsSet.add(item);
            }
        }

        if(java.util.Objects.nonNull(original.linkedList)){
            result.linkedList=new java.util.LinkedList<>();
            for(java.lang.String item:original.linkedList){
                result.linkedList.add(item);
            }
        }

        return result;
    }
}
