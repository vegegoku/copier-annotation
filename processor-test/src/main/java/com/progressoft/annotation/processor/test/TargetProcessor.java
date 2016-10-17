package com.progressoft.annotation.processor.test;

import com.google.common.truth.Truth;
import com.google.testing.compile.JavaFileObjects;

import javax.annotation.processing.Processor;

import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

public class TargetProcessor {
    private final String inputClassName;
    private final Processor processor;

    public TargetProcessor(String inputClassName, Processor processor) {
        this.inputClassName = inputClassName;
        this.processor = processor;
    }

    public void generates(String result){
        Truth.assert_().about(javaSource()).that(JavaFileObjects.forResource(inputClassName))
                .processedWith(processor).compilesWithoutError()
                .and()
                .generatesSources(JavaFileObjects.forSourceString("",result));
    }

}
