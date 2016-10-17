package com.progressoft.annotation.processor;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import com.google.common.truth.Truth;
import com.google.testing.compile.JavaFileObjects;

import java.io.IOException;
import java.io.InputStream;

import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static com.progressoft.annotation.processor.test.ProcessorAssert.*;

public class AnnotationProcessorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void givenAClassNotAnnotatedAsWithCopier_shouldCompileWithoutErrors() throws Exception {
        Truth.assert_().about(javaSource()).that(JavaFileObjects.forResource("com/progressoft/annotation/processor/NoneAnnotatedSource.java"))
                .processedWith(new CopierAnnotationProcessor()).compilesWithoutError();
    }

    @Test
    public void givenAClassAnnotatedAsWithCopier_shouldGenerateAClassWithSameNameButEndsWithCopier() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/EmptyAnnotatedClass.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("EmptyAnnotatedClassResult.java"));

        assertProcessing("com/progressoft/annotation/processor/SecondEmptyAnnotatedClass.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("SecondEmptyAnnotatedClassResult.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndHasAttribute_shouldGenerateAClassWithSameNameButEndsWithCopierAndCopyTheAttributes() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/AnnotatedClassWithSingleField.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithSingleFieldCopier.java"));

        assertProcessing("com/progressoft/annotation/processor/AnnotatedClassWithFields2.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithFields2Copier.java"));

        assertProcessing("com/progressoft/annotation/processor/AnnotatedClassWithFields3.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithFields3Copier.java"));

    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndHasAPrimitiveBooleanAttribute_shouldGenerateAClassWithSameNameButEndsWithCopierAndCopyTheAttributeUsingIsInsteadOfGet() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/AnnotatedClassWithFields4.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithFields4Copier.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierForPublicMemebersMode_shouldGenerateCopierUsingDirectPublicMembersAccess() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/AnnotatedClassWithPublicMemebersAndNoAccessories.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithPublicMemebersAndNoAccessoriesCopier.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndHasIgnoredField_shouldGenerateCopierWithoutIncludingTheField() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/AnnotatedClassWithIgnoredFieldUsingAccessors.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithIgnoredFieldUsingAccessorsCopier.java"));

        assertProcessing("com/progressoft/annotation/processor/AnnotatedClassWithIgnoredFieldUsingPublicFields.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithIgnoreFieldUsingPublicFieldsCopier.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndADepCopyField_shouldGenerateCopierThatCopyTheFieldUsingClone() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/AnnotatedClassWithDeepCopyField.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithDeepCopyFieldCopier.java"));

        assertProcessing("com/progressoft/annotation/processor/AnnotatedClassWithDeepCopyPublicField.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithDeepCopyPublicFieldCopier.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndHasCollectionFields_shouldGenerateCopierProperCollectionCopying() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/AnnotatedClassWithCollections.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithCollectionsCopier.java"));
    }

    private String getExpectedResultFileContent(String resourceName) throws IOException{
        try(InputStream resourceInputStream=this.getClass().getResourceAsStream("results/"+resourceName)){
            return IOUtils.toString(resourceInputStream,"UTF-8");
        }
    }

}