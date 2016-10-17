package com.progressoft.annotation.processor;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface WithCopier {
    enum Mode{
        GETTERS_SETTERS, PUBLIC_MEMEBERS;
    }

    Mode mode() default Mode.GETTERS_SETTERS;
}
