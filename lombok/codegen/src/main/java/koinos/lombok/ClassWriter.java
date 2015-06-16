package koinos.lombok;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ClassWriter {
    public static void write(String basedir, String packageName, String className, String code) throws IOException {
        String dir = basedir + "/" + packageName.replace('.', '/');
        FileUtils.write(new File(dir, className + ".java"), code);
    }
}
