package com.progressoft.annotation.processor.test;


public class ProcessorAssert {
    public static InputSource assertProcessing(String inputClassName){
        return new InputSource(inputClassName);
    }
}
