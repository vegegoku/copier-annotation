package com.progressoft.annotation.processor.copier;

import com.google.common.truth.Truth;
import com.google.testing.compile.JavaFileObjects;
import com.progressoft.annotation.processor.CopierAnnotationProcessor;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static com.progressoft.annotation.processor.test.ProcessorAssert.assertProcessing;

public class CopierAnnotationProcessorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void givenAClassNotAnnotatedAsWithCopier_shouldCompileWithoutErrors() throws Exception {
        Truth.assert_().about(javaSource()).that(JavaFileObjects.forResource("com/progressoft/annotation/processor/copier/NoneAnnotatedSource.java"))
                .processedWith(new CopierAnnotationProcessor()).compilesWithoutError();
    }

    @Test
    public void givenAClassAnnotatedAsWithCopier_shouldGenerateAClassWithSameNameButEndsWithCopier() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/copier/EmptyAnnotatedClass.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("EmptyAnnotatedClassResult.java"));

        assertProcessing("com/progressoft/annotation/processor/copier/SecondEmptyAnnotatedClass.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("SecondEmptyAnnotatedClassResult.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndHasAttribute_shouldGenerateAClassWithSameNameButEndsWithCopierAndCopyTheAttributes() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/copier/AnnotatedClassWithSingleField.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithSingleFieldCopier.java"));

        assertProcessing("com/progressoft/annotation/processor/copier/AnnotatedClassWithFields2.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithFields2Copier.java"));

        assertProcessing("com/progressoft/annotation/processor/copier/AnnotatedClassWithFields3.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithFields3Copier.java"));

    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndHasAPrimitiveBooleanAttribute_shouldGenerateAClassWithSameNameButEndsWithCopierAndCopyTheAttributeUsingIsInsteadOfGet() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/copier/AnnotatedClassWithFields4.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithFields4Copier.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierForPublicMemebersMode_shouldGenerateCopierUsingDirectPublicMembersAccess() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/copier/AnnotatedClassWithPublicMemebersAndNoAccessories.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithPublicMemebersAndNoAccessoriesCopier.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndHasIgnoredField_shouldGenerateCopierWithoutIncludingTheField() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/copier/AnnotatedClassWithIgnoredFieldUsingAccessors.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithIgnoredFieldUsingAccessorsCopier.java"));

        assertProcessing("com/progressoft/annotation/processor/copier/AnnotatedClassWithIgnoredFieldUsingPublicFields.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithIgnoreFieldUsingPublicFieldsCopier.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndADepCopyField_shouldGenerateCopierThatCopyTheFieldUsingClone() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/copier/AnnotatedClassWithDeepCopyField.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithDeepCopyFieldCopier.java"));

        assertProcessing("com/progressoft/annotation/processor/copier/AnnotatedClassWithDeepCopyPublicField.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithDeepCopyPublicFieldCopier.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndHasCollectionFields_shouldGenerateCopierProperCollectionCopying() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/copier/AnnotatedClassWithCollections.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithCollectionsCopier.java"));

        assertProcessing("com/progressoft/annotation/processor/copier/AnnotatedClassWithPublicCollections.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithPublicCollectionsCopier.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndHasvariablesAndCollectionFields_shouldGenerateCopierProperVariablsAndCollectionCopying() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/copier/AnnotatedClassWithVariablesAndCollection.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithVariablesAndCollectionCopier.java"));

    }

    private String getExpectedResultFileContent(String resourceName) throws IOException{
        try(InputStream resourceInputStream=this.getClass().getResourceAsStream("results/"+resourceName)){
            return IOUtils.toString(resourceInputStream,"UTF-8");
        }
    }

}