The project allows to measure time to compile large number of classes 
written with o without using lombok. 
    
`mvn clean install` first generates some code using `codegen-maven-plugin`, then compiles it.
By default, plugin generates 10 packages with 100 classes in each, 
and 50 string attributes per class. Default values can be overridden by plugin configuration.

### profiles
#### how to generate code
* `no-lombok` : no lombok annotations, attributes with getters and setters
* `getter-setter`: lombok `@Getter @Setter` annotations on classes
* `data`: lombok `@Data` annotations on classes
    
If no profile is set no-lombok mode will be used.

#### what version of lombok will be used
* if no profile is specified, `1.16.4` version will be used
* `1.12.4` version will be used with the profile `fast`
    