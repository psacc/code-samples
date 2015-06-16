package koinos.lombok;

import java.io.IOException;

public class CodeGenerator {
    private final CodeDescriptor descriptor;
    private final String basedir;

    public CodeGenerator(CodeDescriptor descriptor, String basedir) {
        this.descriptor = descriptor;
        this.basedir = basedir;
    }

    public void generate() throws IOException {
        for (int packageN = 0; packageN < descriptor.packageCount; packageN++) {
            for (int classN = 0; classN < descriptor.classesPerPackage; classN++) {
                final String packageName = descriptor.basePackage + ".package" + (packageN+1);
                final String className = "SampleClass" + (classN+1);
                String classCode = ClassGenerator.generate(packageName, className, descriptor.attrsPerClass, descriptor.mode);
                ClassWriter.write(basedir, packageName, className, classCode);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        CodeDescriptor descriptor = new CodeDescriptor();
        descriptor.basePackage = System.getProperty("basePackage");
        descriptor.packageCount = Integer.parseInt(System.getProperty("packageCount"));
        descriptor.classesPerPackage = Integer.parseInt(System.getProperty("classesPerPackage"));
        descriptor.attrsPerClass = Integer.parseInt(System.getProperty("attrsPerClass"));
        descriptor.mode = LombokMode.valueOf(System.getProperty("lombokMode"));

        String basedir = System.getProperty("basedir");

        new CodeGenerator(descriptor, basedir).generate();
    }

}
