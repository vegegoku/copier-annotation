package com.progressoft.annotation.processor;

import com.progressoft.annotation.processor.copier.DeepCopy;
import org.apache.commons.lang.WordUtils;

import javax.lang.model.element.Element;
import java.util.Objects;

public class AccessorSingleFieldCopyGenerator implements FieldsCopyStatementGenerator{

    @Override
    public String generate(Element element) {
        StringBuilder sb = new StringBuilder();

        if(Objects.nonNull(element.getAnnotation(DeepCopy.class))){
            sb.append("\n        if(java.util.Objects.nonNull(original.get"+ WordUtils.capitalize(element.getSimpleName().toString()+"())) {\n        "));
        }

        sb.append("        " + RESULT + ".set");
        sb.append(WordUtils.capitalize(element.getSimpleName().toString()));
        sb.append("(" + ORIGINAL + "." + ("boolean".equals(element.asType().toString()) ? "is" : "get"));
        sb.append(WordUtils.capitalize(element.getSimpleName().toString()));

        if(Objects.nonNull(element.getAnnotation(DeepCopy.class))){
            sb.append("().clone());\n        }\n\n");
        }else {
            sb.append("());\n");
        }


        return sb.toString();
    }
}
