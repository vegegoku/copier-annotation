package com.progressoft.annotation.processor.generators;

import com.progressoft.annotation.processor.copier.CollectionCopy;
import com.progressoft.annotation.processor.copier.WithCopier;
import com.progressoft.annotation.processor.generators.accessor.*;
import com.progressoft.annotation.processor.generators.publicmembers.*;

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
        if(isPublicMembersCopier(annotatedClassElement))
            return getPublicFieldGenerator(element);
        else
            return getAccessorGenerator(element);
    }

    private static boolean isPublicMembersCopier(TypeElement annotatedClassElement) {
        return WithCopier.Mode.PUBLIC_MEMEBERS.equals(getCopierMode(annotatedClassElement));
    }

    private static FieldsCopyStatementGenerator getPublicFieldGenerator(Element element) {
        if(isCollection(element))
            return collectionPublicFieldsGenerators.get(initializer(element));
        return simplePublicFieldGenerator;
    }

    private static FieldsCopyStatementGenerator getAccessorGenerator(Element element) {
        if(isCollection(element))
            return collectionAccessorGenerators.get(initializer(element));
        return simpleFieldAccessorGenerator;
    }

    private static WithCopier.Mode getCopierMode(TypeElement annotatedClassElement) {
        return annotatedClassElement.getAnnotation(WithCopier.class).mode();
    }

    private static String initializer(Element element) {
        return element.getAnnotation(CollectionCopy.class).initializer();
    }

    private static boolean isCollection(Element element) {
        return Objects.nonNull(element.getAnnotation(CollectionCopy.class));
    }

}
