package com.progressoft.annotation.processor.generators;

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
        return element.asType().toString().substring(element.asType().toString().indexOf("<")+1, element.asType().toString().lastIndexOf(">"));
    }
}
