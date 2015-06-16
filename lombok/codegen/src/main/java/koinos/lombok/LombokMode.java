package koinos.lombok;

public enum LombokMode { NONE {
    @Override
    public String classLevelAnnotation() {
        return "";
    }
    @Override
    public String getterAndSetter(String attrName) {
        String AttrName = attrName.substring(0,1).toUpperCase() + attrName.substring(1);
        return  String.format(
                "   public String get%2$s() {%n" +
                "       return %1$s;%n" +
                "   }%n%n" +
                "   public void set%2$s(String %1$s) {%n" +
                "       this.%1$s = %1$s;%n" +
                "   }%n%n", attrName, AttrName);
    }
}, GETTER_SETTER {
    @Override
    public String classLevelAnnotation() {
        return "@lombok.Getter @lombok.Setter\n";
    }
    @Override
    public String getterAndSetter(String attrName) {
        return "";
    }
}, DATA {
    @Override
    public String classLevelAnnotation() {
        return "@lombok.Data\n";
    }
    @Override
    public String getterAndSetter(String attrName) {
        return "";
    }
};

    public abstract String classLevelAnnotation();

    public abstract String getterAndSetter(String attrName);
}
