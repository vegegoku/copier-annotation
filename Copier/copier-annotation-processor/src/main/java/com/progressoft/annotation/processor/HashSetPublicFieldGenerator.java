package com.progressoft.annotation.processor;

import com.progressoft.annotation.processor.copier.DeepCopy;

import javax.lang.model.element.Element;
import java.util.Objects;

public class HashSetPublicFieldGenerator implements FieldsCopyStatementGenerator {
    @Override
    public String generate(Element element) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n        if(java.util.Objects.nonNull(original." + element.getSimpleName().toString() + ")){\n");
        sb.append("                result." + element.getSimpleName().toString() + "=new java.util.HashSet<>();\n");
        sb.append("                for (" + getGenericType(element) + " item :original." + element.getSimpleName().toString() + ") {\n");
        sb.append("                                result." + element.getSimpleName().toString() + ".add(item" + (Objects.nonNull(element.getAnnotation(DeepCopy.class)) ? ".clone()" : "") + ");\n");
        sb.append("                }\n");
        sb.append("        }\n");
        return sb.toString();
    }
}
