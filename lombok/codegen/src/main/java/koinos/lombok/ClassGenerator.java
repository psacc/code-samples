package koinos.lombok;

public class ClassGenerator {
    private final String packageName;
    private final String className;
    private final int attrsPerClass;
    private final LombokMode mode;

    private StringBuilder code = new StringBuilder();

    private ClassGenerator(String packageName, String className, int attrsPerClass, LombokMode mode) {
        this.packageName = packageName;
        this.className = className;
        this.attrsPerClass = attrsPerClass;
        this.mode = mode;
    }

    private void generate() {
        code.append("package ").append(packageName).append(";\n\n");

        code.append(mode.classLevelAnnotation());
        code.append("public class ").append(className).append(" {\n");

        for (int i = 0; i < attrsPerClass; i++) {
            String attrName = "attr" + (i+1);
            code.append("   private String ").append(attrName).append(";\n");
            code.append(mode.getterAndSetter(attrName));
        }

        code.append("}");
    }

    public static String generate(String packageName, String className, int attrsPerClass, LombokMode mode) {
        final ClassGenerator generator = new ClassGenerator(packageName, className, attrsPerClass, mode);
        generator.generate();
        return generator.code.toString();
    }
}
