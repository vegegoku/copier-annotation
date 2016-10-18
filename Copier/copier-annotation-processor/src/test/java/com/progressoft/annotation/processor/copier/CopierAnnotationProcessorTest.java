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

    private static final String BASE_PACKAGE = "com/progressoft/annotation/processor/copier/";

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void givenAClassNotAnnotatedAsWithCopier_shouldCompileWithoutErrors() throws Exception {
        Truth.assert_().about(javaSource()).that(JavaFileObjects.forResource(BASE_PACKAGE + "NoneAnnotatedSource.java"))
                .processedWith(newProcessor()).compilesWithoutError();
    }

    @Test
    public void givenAClassAnnotatedAsWithCopier_shouldGenerateAClassWithSameNameButEndsWithCopier() throws Exception {
        assertProcessing(BASE_PACKAGE + "EmptyAnnotatedClass.java")
                .withProcessor(newProcessor())
                .generates(getExpectedResultFileContent("EmptyAnnotatedClassResult.java"));

        assertProcessing(BASE_PACKAGE + "SecondEmptyAnnotatedClass.java")
                .withProcessor(newProcessor())
                .generates(getExpectedResultFileContent("SecondEmptyAnnotatedClassResult.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndHasAttribute_shouldGenerateAClassWithSameNameButEndsWithCopierAndCopyTheAttributes() throws Exception {
        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithSingleField.java")
                .withProcessor(newProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithSingleFieldCopier.java"));

        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithFields2.java")
                .withProcessor(newProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithFields2Copier.java"));

        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithFields3.java")
                .withProcessor(newProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithFields3Copier.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndHasAPrimitiveBooleanAttribute_shouldGenerateAClassWithSameNameButEndsWithCopierAndCopyTheAttributeUsingIsInsteadOfGet() throws Exception {
        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithFields4.java")
                .withProcessor(newProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithFields4Copier.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierForPublicMemebersMode_shouldGenerateCopierUsingDirectPublicMembersAccess() throws Exception {
        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithPublicMemebersAndNoAccessories.java")
                .withProcessor(newProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithPublicMemebersAndNoAccessoriesCopier.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndHasIgnoredField_shouldGenerateCopierWithoutIncludingTheField() throws Exception {
        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithIgnoredFieldUsingAccessors.java")
                .withProcessor(newProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithIgnoredFieldUsingAccessorsCopier.java"));

        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithIgnoredFieldUsingPublicFields.java")
                .withProcessor(newProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithIgnoreFieldUsingPublicFieldsCopier.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndADepCopyField_shouldGenerateCopierThatCopyTheFieldUsingClone() throws Exception {
        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithDeepCopyField.java")
                .withProcessor(newProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithDeepCopyFieldCopier.java"));

        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithDeepCopyPublicField.java")
                .withProcessor(newProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithDeepCopyPublicFieldCopier.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndHasCollectionFields_shouldGenerateCopierProperCollectionCopying() throws Exception {
        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithCollections.java")
                .withProcessor(newProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithCollectionsCopier.java"));

        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithPublicCollections.java")
                .withProcessor(newProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithPublicCollectionsCopier.java"));
    }

    @Test
    public void givenAClassAnnotatedAsWithCopierAndHasVariablesAndCollectionFields_shouldGenerateCopierProperVariablsAndCollectionCopying() throws Exception {
        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithVariablesAndCollection.java")
                .withProcessor(newProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithVariablesAndCollectionCopier.java"));
    }

    private CopierAnnotationProcessor newProcessor(){
        return new CopierAnnotationProcessor();
    }

    private String getExpectedResultFileContent(String resourceName) throws IOException{
        try(InputStream resourceInputStream=this.getClass().getResourceAsStream("results/"+resourceName)){
            return IOUtils.toString(resourceInputStream,"UTF-8");
        }
    }
}