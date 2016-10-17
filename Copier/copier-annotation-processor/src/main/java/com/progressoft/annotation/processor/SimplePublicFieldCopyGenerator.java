package com.progressoft.annotation.processor;

import javax.lang.model.element.Element;
import java.util.Objects;

public class SimplePublicFieldCopyGenerator implements FieldsCopyStatementGenerator{

    @Override
    public String generate(Element element) {
        StringBuilder sb = new StringBuilder();

        if(Objects.nonNull(element.getAnnotation(DeepCopy.class))){
            sb.append("\n       if(java.util.Objects.nonNull(original."+element.getSimpleName().toString()+")) {\n        ");
        }

        sb.append("       " + RESULT + ".");
        sb.append(element.getSimpleName().toString());
        sb.append("=" + ORIGINAL + ".");
        sb.append(element.getSimpleName().toString());

        if(Objects.nonNull(element.getAnnotation(DeepCopy.class))){
            sb.append(".clone();\n       }\n\n");
        }else {
            sb.append(";\n");
        }

        return sb.toString();
    }
}
