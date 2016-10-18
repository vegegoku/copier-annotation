package com.progressoft.annotation.processor;

import com.google.auto.service.AutoService;
import com.progressoft.annotation.processor.copier.IgnoreCopy;
import com.progressoft.annotation.processor.copier.WithCopier;
import com.progressoft.annotation.processor.generators.FieldCopyStatementFactory;
import com.progressoft.annotation.processor.generators.FieldsCopyStatementGenerator;

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
import java.util.Objects;
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
            generateCopier(new ProcessorElement(element));
        }
        return true;
    }

    private void validateElementKind(Element element) {
        if (element.getKind() != ElementKind.CLASS)
            throw new ProcessingException(element, "Only classes can be annotated with @%s",
                    WithCopier.class.getSimpleName());
    }

    private void generateCopier(ProcessorElement processorElement) {

        try (Writer sourceWriter = obtainSourceWriter(processorElement.simpleName(), processorElement.elementPackage())) {
            sourceWriter.write(writeCopierClass(processorElement));
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "could not generate class");
        }
    }

    private String writeCopierClass(ProcessorElement processorElement) {
        return writePackage(processorElement.elementPackage()) +
                writeClassName(processorElement.simpleName()) +
                writeCopyMethodSignature(processorElement.simpleName()) +
                writeThrowsDeclaration() +
                writeInstanceCreationStatement(processorElement.simpleName()) +
                writeCopyFieldsStatements(processorElement.asTypeElement()) +
                writeReturnStatement() +
                closeMethod() +
                closeClass();
    }

    private String writeThrowsDeclaration() {
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

    private String writePackage(String targetPackage) {
        return "package " + targetPackage + ";\n\n";
    }

    private Writer obtainSourceWriter(String className, String targetPackage) throws IOException {
        return createSourceFile(className, targetPackage).openWriter();
    }

    private JavaFileObject createSourceFile(String className, String targetPackage) throws IOException {
        return filer.createSourceFile(targetPackage + "." + className + FieldsCopyStatementGenerator.COPIER_POSTFIX);
    }

    private String writeCopyFieldsStatements(TypeElement annotatedClassElement) {
        StringBuilder sb = new StringBuilder();
        generatedCopStatements(annotatedClassElement, sb);
        return sb.append("\n").toString();
    }

    private void generatedCopStatements(TypeElement annotatedClassElement, StringBuilder sb) {
        annotatedClassElement.getEnclosedElements().stream()
                .filter(element -> element.getKind() == ElementKind.FIELD && notIgnored(element))
                .forEach(element -> sb.append(FieldCopyStatementFactory.getGenerator(annotatedClassElement, element)
                        .generate(element)));
    }

    private boolean notIgnored(Element element) {
        return Objects.isNull(element.getAnnotation(IgnoreCopy.class));
    }
}
