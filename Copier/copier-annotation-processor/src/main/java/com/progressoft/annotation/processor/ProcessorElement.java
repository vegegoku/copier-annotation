package com.progressoft.annotation.processor;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

public class ProcessorElement {

    private final Element element;

    public ProcessorElement(Element element) {
        this.element = element;
    }

    String elementPackage() {
        return asTypeElement().getQualifiedName().toString().replace("." + simpleName(), "");
    }

    TypeElement asTypeElement() {
        return (TypeElement) element;
    }

    String simpleName() {
        return asTypeElement().getSimpleName().toString();
    }
}
