package com.progressoft.annotation.processor.generators;

import com.progressoft.annotation.processor.copier.CollectionCopy;
import com.progressoft.annotations.processing.ProcessorElement;
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
        String simpleType=new ProcessorElement(element).asSimpleType();
        return simpleType.substring(simpleType.indexOf("<")+1, simpleType.lastIndexOf(">"));
    }
}
