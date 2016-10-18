package com.progressoft.annotation.processor.copier;

import java.util.List;
import java.util.Map;
import java.util.Set;

@WithCopier(mode = WithCopier.Mode.PUBLIC_MEMEBERS)
public class AnnotatedClassWithPublicCollections {

    public class AnotherObject{
        @Override
        protected AnotherObject clone() throws CloneNotSupportedException {
            return new AnotherObject();
        }
    }

    @CollectionCopy(initializer = CollectionCopy.ARRAY_LIST)
    @DeepCopy
    public List<AnotherObject> anotherObjects;

    @CollectionCopy(initializer = CollectionCopy.ARRAY_LIST)
    public List<Integer> integersList;

    @CollectionCopy(initializer = CollectionCopy.HASH_MAP)
    public Map<String, String> stringsMap;

    @CollectionCopy(initializer = CollectionCopy.HASH_SET)
    public Set<String> stringsSet;

    @CollectionCopy(initializer = CollectionCopy.LINKED_LIST)
    public List<String> linkedList;
}
