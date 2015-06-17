package koinos.lombok;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class ClassGeneratorTest {

    @Test
    public void testGenerateGetterSetter() throws Exception {
        final String classCode = ClassGenerator.generate("koinos.lombok.generated.package1", "SampleClass1", 2, LombokMode.GETTER_SETTER);
        Assert.assertThat(classCode, Matchers.containsString("@lombok.Getter"));
        Assert.assertThat(classCode, Matchers.containsString("@lombok.Setter"));
    }

    @Test
    public void testGenerateData() throws Exception {
        final String classCode = ClassGenerator.generate("koinos.lombok.generated.package1", "SampleClass1", 2, LombokMode.DATA);
        Assert.assertThat(classCode, Matchers.containsString("@lombok.Data"));
    }

    @Test
    public void testGenerateNoLombok() throws Exception {
        final String classCode = ClassGenerator.generate("koinos.lombok.generated.package1", "SampleClass1", 2, LombokMode.NONE);
        Assert.assertThat(classCode, Matchers.containsString("public String get"));
    }
}