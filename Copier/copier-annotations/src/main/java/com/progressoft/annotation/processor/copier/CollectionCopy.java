package com.progressoft.annotation.processor.copier;

import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface CollectionCopy {

    final String ARRAY_LIST="new java.util.ArrayList<>()";
    final String LINKED_LIST="new java.util.LinkedList<>()";
    final String HASH_SET="new java.util.HashSet<>()";
    final String HASH_MAP="new java.util.HashMap<>()";

    @NotNull
    String initializer();

    @NotNull
    String genericType();
}
