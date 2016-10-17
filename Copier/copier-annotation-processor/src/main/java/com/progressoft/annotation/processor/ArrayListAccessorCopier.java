package com.progressoft.annotation.processor;

import org.apache.commons.lang.WordUtils;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;
import java.util.Objects;
import java.util.StringTokenizer;

public class ArrayListAccessorCopier implements FieldsCopyStatementGenerator {

    @Override
    public String generate(Element element) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n        if(java.util.Objects.nonNull(original.get" + getCapitalizedFieldName(element) + "())){\n");
        sb.append("                result.set" + getCapitalizedFieldName(element) + "(new java.util.ArrayList<>());\n");
        sb.append("                for (" + getGenericType(element) + " item :original.get" + getCapitalizedFieldName(element) + "()) {\n");
        sb.append("                                result.get" + getCapitalizedFieldName(element) + "().add(item" + (Objects.nonNull(element.getAnnotation(DeepCopy.class)) ? ".clone()" : "") + ");\n");
        sb.append("                }\n");
        sb.append("        }\n");
        return sb.toString();
    }

}
