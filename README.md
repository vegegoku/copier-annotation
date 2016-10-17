# copier-annotation
An annotation with annotation processor to generate copier classes for datastructures

processor-test project contains the required dependencies to test an annotation processor plus a wrapper i wrote to make the testing easier.

Copier annotation example :

the following class :

```java

package com.progressoft.annotation.processor;

@WithCopier
public class AnnotatedClassWithSingleField {

    private int integerField;

    public int getIntegerField() {
        return integerField;
    }

    public void setIntegerField(int integerField) {
        this.integerField = integerField;
    }
}
```

will generate the following copier class :

```java
package com.progressoft.annotation.processor;

class AnnotatedClassWithSingleFieldCopier {

    AnnotatedClassWithSingleField copy(AnnotatedClassWithSingleField original) throws CloneNotSupportedException {
        AnnotatedClassWithSingleField result=new AnnotatedClassWithSingleField();
        result.setIntegerField(original.getIntegerField());
        return result;
    }
}
```

for more examples please refer to the unit test included in the annotation processor project.


Processor testing example :
general syntax

```java
assertProcessing("input source file full path goes here")
                .withProcessor(processor instance goes here)
                .generates("expected generated source string goes here");

```
sample test case

```java
    @Test
    public void givenAClassAnnotatedAsWithCopier_shouldGenerateAClassWithSameNameButEndsWithCopier() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/AnnotatedClassWithSingleField.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithSingleFieldCopier.java"));
     }
```
for more testing examples please refer to the copier annotation processor project.

Enjoy :-)
