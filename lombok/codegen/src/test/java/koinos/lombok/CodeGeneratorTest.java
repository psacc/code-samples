package koinos.lombok;

import org.junit.Test;

import java.io.IOException;

public class CodeGeneratorTest {
    @Test
    public void createAll() throws IOException {
        CodeDescriptor descriptor = new CodeDescriptor();
        descriptor.packageCount = 10;
        descriptor.classesPerPackage = 100;
        descriptor.attrsPerClass = 100;
        descriptor.basePackage = "koinos.lombok.getterandsetter";
        descriptor.mode = LombokMode.GETTER_SETTER;

        new CodeGenerator(descriptor, "/temp").generate();
    }
}