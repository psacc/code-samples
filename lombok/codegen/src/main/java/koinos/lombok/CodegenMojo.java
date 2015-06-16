package koinos.lombok;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;

@Mojo( name = "codegen")
public class CodegenMojo extends AbstractMojo {
    @Parameter(defaultValue = "koinos.lombok.samplecode" )
    private String basePackage;

    @Parameter(defaultValue = "10" )
    private int packageCount;

    @Parameter(defaultValue = "100" )
    private int classesPerPackage;

    @Parameter(defaultValue = "50" )
    private int attrsPerClass;

    @Parameter(defaultValue = "${lombokMode}")
    private LombokMode lombokMode;

    @Parameter(defaultValue = "${project.build.directory}/generated-sources" )
    private String basedir;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().debug("basePackage = " + basePackage);
        getLog().debug("packageCount = " + packageCount);
        getLog().debug("classesPerPackage = " + classesPerPackage);
        getLog().debug("attrsPerClass = " + attrsPerClass);
        getLog().debug("lombokMode = " + lombokMode);
        getLog().debug("basedir = " + basedir);

        CodeDescriptor descriptor = new CodeDescriptor();
        descriptor.basePackage = basePackage;
        descriptor.packageCount = packageCount;
        descriptor.classesPerPackage = classesPerPackage;
        descriptor.attrsPerClass = attrsPerClass;
        descriptor.mode = lombokMode;

        try {
            new CodeGenerator(descriptor, basedir).generate();
        } catch (IOException e) {
            getLog().error(e);
        }
    }
}
