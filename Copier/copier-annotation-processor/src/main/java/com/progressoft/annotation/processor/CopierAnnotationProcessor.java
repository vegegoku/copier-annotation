package com.progressoft.annotation.processor;

import com.google.auto.service.AutoService;
import com.progressoft.annotation.processor.copier.IgnoreCopy;
import com.progressoft.annotation.processor.copier.WithCopier;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@AutoService(value = Processor.class)
@SupportedAnnotationTypes(value = "com.progressoft.annotation.processor.copier.WithCopier")
@SupportedSourceVersion(value = SourceVersion.RELEASE_8)
public class CopierAnnotationProcessor extends AbstractProcessor {



    private Types typesUtil;
    private Elements elementsUtil;
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.typesUtil = processingEnv.getTypeUtils();
        this.elementsUtil = processingEnv.getElementUtils();
        this.filer = processingEnv.getFiler();
        this.messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        for (Element element : roundEnv.getElementsAnnotatedWith(WithCopier.class)) {
            validateElementKind(element);
            generateCopier(asTypeElement(element), annotatedClassSimpleName(element), annotatedClassPackageName(element));
        }

        return true;
    }

    private void generateCopier(TypeElement annotatedClassElement, String className, String annotatedClassPackage) {

        try (Writer sourceWriter = obtainSourceWriter(className, annotatedClassPackage)) {
            String result = writeCopierClass(annotatedClassElement, className, annotatedClassPackage);
            sourceWriter.write(result);
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "could not generate class");
        }
    }

    private String writeCopierClass(TypeElement annotatedClassElement, String className, String annotatedClassPackage) {
        return writePackage(annotatedClassPackage) +
                writeClassName(className) +
                writeCopyMethodSignature(className) +
                writeThrowsDecleration()+
                writeInstanceCreationStatement(className) +
                writeCopyFieldsStatements(annotatedClassElement) +
                writeReturnStatement() +
                closeMethod() +
                closeClass();
    }

    private String writeThrowsDecleration() {
        return "throws CloneNotSupportedException {\n";
    }

    private String closeClass() {
        return "}";
    }

    private String closeMethod() {
        return "    }\n";
    }

    private String writeReturnStatement() {
        return "        return " + FieldsCopyStatementGenerator.RESULT + ";\n";
    }

    private String writeInstanceCreationStatement(String className) {
        return "        " + className + " " + FieldsCopyStatementGenerator.RESULT + "=new " + className + "();\n\n";
    }

    private String writeCopyMethodSignature(String className) {
        return "    " + className + " copy(" + className + " " + FieldsCopyStatementGenerator.ORIGINAL + ")";
    }

    private String writeClassName(String className) {
        return "class " + className + FieldsCopyStatementGenerator.COPIER_POSTFIX + " {\n\n";
    }

    private String writePackage(String annotatedClassPackage) {
        return "package " + annotatedClassPackage + ";\n\n";
    }

    private Writer obtainSourceWriter(String className, String annotatedClassPackage) throws IOException {
        return createSourceFile(className, annotatedClassPackage).openWriter();
    }

    private JavaFileObject createSourceFile(String className, String annotatedClassPackage) throws IOException {
        return filer.createSourceFile(annotatedClassPackage + "." + className + FieldsCopyStatementGenerator.COPIER_POSTFIX);
    }

    private String annotatedClassPackageName(Element element) {
        return asTypeElement(element).getQualifiedName().toString().replace("." + annotatedClassSimpleName(element), "");
    }

    private String annotatedClassSimpleName(Element element) {
        return asTypeElement(element).getSimpleName().toString();
    }

    private TypeElement asTypeElement(Element element) {
        return (TypeElement) element;
    }

    private void validateElementKind(Element element) {
        if (element.getKind() != ElementKind.CLASS)
            throw new ProcessingException(element, "Only classes can be annotated with @%s",
                    WithCopier.class.getSimpleName());
    }

    private String writeCopyFieldsStatements(TypeElement annotatedClassElement) {
        StringBuilder sb = new StringBuilder();
        generatedCopStatements(annotatedClassElement, sb);
        return sb.append("\n").toString();
    }

    private WithCopier getCopierAnnotation(TypeElement annotatedClassElement) {
        return annotatedClassElement.getAnnotation(WithCopier.class);
    }

    private void generatedCopStatements(TypeElement annotatedClassElement, StringBuilder sb) {
        for (Element element : annotatedClassElement.getEnclosedElements())
            if (element.getKind() == ElementKind.FIELD && notIgnored(element)) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>  "+element.asType().toString());
                sb.append(FieldCopyStatementFactory.getGenerator(annotatedClassElement, element).generate(element));
            }
    }

    private boolean notIgnored(Element element) {
        return element.getAnnotation(IgnoreCopy.class)==null;
    }
}
