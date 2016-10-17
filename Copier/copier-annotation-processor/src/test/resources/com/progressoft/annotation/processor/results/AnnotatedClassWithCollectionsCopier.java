package com.progressoft.annotation.processor;

class AnnotatedClassWithCollectionsCopier {

    AnnotatedClassWithCollections copy(AnnotatedClassWithCollections original) throws CloneNotSupportedException {
        AnnotatedClassWithCollections result=new AnnotatedClassWithCollections();

        if(java.util.Objects.nonNull(original.getAnotherObjects())){
            result.setAnotherObjects(new java.util.ArrayList<>());
            for (com.progressoft.annotation.processor.AnnotatedClassWithCollections.AnotherObject item :original.getAnotherObjects()) {
                result.getAnotherObjects().add(item.clone());
            }
        }

        if(java.util.Objects.nonNull(original.getIntegersList())){
            result.setIntegersList(new java.util.ArrayList<>());
            for(java.lang.Integer item:original.getIntegersList()){
                result.getIntegersList().add(item);
            }
        }

        if(java.util.Objects.nonNull(original.getStringsMap())){
            result.setStringsMap(new java.util.HashMap<>());
            for(java.util.Map.Entry<java.lang.String,java.lang.String> entry:original.getStringsMap().entrySet()){
                result.getStringsMap().put(entry.getKey(), entry.getValue());
            }
        }

        if(java.util.Objects.nonNull(original.getStringsSet())){
            result.setStringsSet(new java.util.HashSet<>());
            for(java.lang.String item:original.getStringsSet()){
                result.getStringsSet().add(item);
            }
        }

        if(java.util.Objects.nonNull(original.getLinkedList())){
            result.setLinkedList(new java.util.LinkedList<>());
            for(java.lang.String item:original.getLinkedList()){
                result.getLinkedList().add(item);
            }
        }

        return result;
    }
}
