package com.progressoft.annotation.processor;

import com.progressoft.annotation.processor.copier.CollectionCopy;
import com.progressoft.annotation.processor.copier.WithCopier;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FieldCopyStatementFactory {

    private static final FieldsCopyStatementGenerator simpleFieldAccessorGenerator = new AccessorSingleFieldCopyGenerator();
    private static final FieldsCopyStatementGenerator simplePublicFieldGenerator = new SimplePublicFieldCopyGenerator();

    private static final Map<String, FieldsCopyStatementGenerator> collectionAccessorGenerators=new HashMap<String, FieldsCopyStatementGenerator>(){{
        put(CollectionCopy.ARRAY_LIST, new ArrayListAccessorCopier());
        put(CollectionCopy.HASH_SET, new HashSetAccessorCopier());
        put(CollectionCopy.LINKED_LIST, new LinkedListAccessorCopier());
        put(CollectionCopy.HASH_MAP, new HashMapAccessorCopier());
    }};

    private static final Map<String, FieldsCopyStatementGenerator> collectionPublicFieldsGenerators=new HashMap<String, FieldsCopyStatementGenerator>(){{
        put(CollectionCopy.ARRAY_LIST, new ArrayListPublicFieldGenerator());
        put(CollectionCopy.HASH_SET, new HashSetPublicFieldGenerator());
        put(CollectionCopy.LINKED_LIST, new LinkedListPublicFieldGenerator());
        put(CollectionCopy.HASH_MAP, new HashMapPublicFieldGenerator());
    }};

    public static FieldsCopyStatementGenerator getGenerator(TypeElement annotatedClassElement, Element element) {
        WithCopier withCopier=annotatedClassElement.getAnnotation(WithCopier.class);
        switch (withCopier.mode()){
            case GETTERS_SETTERS:
                if(Objects.nonNull(element.getAnnotation(CollectionCopy.class))){
                    return collectionAccessorGenerators.get(element.getAnnotation(CollectionCopy.class).initializer());
                }
                return simpleFieldAccessorGenerator;
            case PUBLIC_MEMEBERS:
                if(Objects.nonNull(element.getAnnotation(CollectionCopy.class))){
                    return collectionPublicFieldsGenerators.get(element.getAnnotation(CollectionCopy.class).initializer());
                }
                return simplePublicFieldGenerator;
        }
        return simpleFieldAccessorGenerator;
    }
}
