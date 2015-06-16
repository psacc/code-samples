package koinos.lombok;

import org.junit.Test;

public class ClassGeneratorTest {

    @Test
    public void testGenerate() throws Exception {
        System.out.println(ClassGenerator.generate("koinos.lombok.generated.package1", "SampleClass1", 2, LombokMode.GETTER_SETTER));
        System.out.println(ClassGenerator.generate("koinos.lombok.generated.package1", "SampleClass1", 2, LombokMode.DATA));
        System.out.println(ClassGenerator.generate("koinos.lombok.generated.package1", "SampleClass1", 2, LombokMode.NONE));
    }
}