package com.progressoft.annotation.processor;

import com.progressoft.annotation.processor.copier.CollectionCopy;
import org.apache.commons.lang.WordUtils;

import javax.lang.model.element.Element;
import java.util.Objects;

public interface FieldsCopyStatementGenerator {

    final String COPIER_POSTFIX = "Copier";
    final String ORIGINAL = "original";
    final String RESULT = "result";

    String generate(Element element);
    default String getCapitalizedFieldName(Element element){
        return WordUtils.capitalize(element.getSimpleName().toString());
    }

    default String getGenericType(Element element){
        if(Objects.nonNull(element.getAnnotation(CollectionCopy.class))){
            return element.getAnnotation(CollectionCopy.class).genericType();
        }else{
            return "";
        }
    }
}
