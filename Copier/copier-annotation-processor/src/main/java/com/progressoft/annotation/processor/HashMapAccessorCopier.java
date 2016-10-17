package com.progressoft.annotation.processor;

import javax.lang.model.element.Element;
import java.util.Objects;

public class HashMapAccessorCopier implements FieldsCopyStatementGenerator {

    @Override
    public String generate(Element element) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n        if(java.util.Objects.nonNull(original.get" + getCapitalizedFieldName(element) + "())){\n");
        sb.append("                result.set" + getCapitalizedFieldName(element) + "(new java.util.HashMap<>());\n");
        sb.append("                for (java.util.Map.Entry<"+getGenericType(element)+"> entry :original.get" + getCapitalizedFieldName(element) + "().entrySet()) {\n");
        sb.append("                                result.get" + getCapitalizedFieldName(element) + "().put(entry.getKey(), entry.getValue()" + (Objects.nonNull(element.getAnnotation(DeepCopy.class)) ? ".clone()" : "") + ");\n");
        sb.append("                }\n");
        sb.append("        }\n");
        return sb.toString();
    }
}
