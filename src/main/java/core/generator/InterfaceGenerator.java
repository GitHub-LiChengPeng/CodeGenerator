package core.generator;


import core.database.Table;
import core.file.java.Interface;
import core.file.java.Method;
import core.file.java.Parameter;
import core.file.java.Type;

public class InterfaceGenerator extends Generator {
    protected Type interfaceType;
    protected Type entityType;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造初始化实例.
     * </pre>
     *
     * @param table 表
     */
    public InterfaceGenerator(Table table, String packageName) {
        // 调用父类构造函数
        super(table);

        this.interfaceType = new Type(packageName + getTableName() + "Dao");
        this.entityType = new Type("com.cn."+packageName + getTableName());
    }

    @Override
    public Interface generate() {

        Interface interface_ = new Interface();
        interface_.setType(interfaceType);
        interface_.setVisibility("public ");

        generateAddMethod(interface_);
        generateDeleteMethod(interface_);
        generateUpdateMethod(interface_);
        return interface_;
    }


    protected void generateAddMethod(Interface interface_) {
        Method method = new Method();
        method.setName("addEntity");
        method.setType(new Type("void"));
        method.addParameter(new Parameter(entityType,"entity"));
        interface_.addImport(entityType);
        interface_.addMethod(method);


        //generateMethod(interface_, "void", "addEntity", getTableName(), "entity");
    }

    protected void generateDeleteMethod(Interface interface_) {

        Method method = new Method();
        method.setName("deleteEntity");
        method.setType(new Type("void"));
        method.addParameter(new Parameter(new Type("int"),"id"));
        interface_.addImport(new Type("int"));
        interface_.addMethod(method);
    }


    protected void generateUpdateMethod(Interface interface_) {

        Method method = new Method();
        method.setName("updateEntity");
        method.setType(new Type("void"));
        method.addParameter(new Parameter(entityType,"id"));
        interface_.addImport(entityType);
        interface_.addMethod(method);


     //   generateMethod(interface_, "void", "updateEntity", getTableName(), "entity");
    }


    protected void generateReadMethod(Interface interface_) {
        Method method = new Method();
        method.setName("readEntity");
        //  method.


    }

/*
    protected void generateMethod(Interface interface_, String returnType, String methodName, String parameterType, String parameterName) {
        Method method = new Method();
        method.setName(methodName);
        method.setType(new Type(returnType));
        method.addParameter(new Parameter(new Type(parameterType), parameterName));
        interface_.addImport(new Type(parameterType));
        interface_.addMethod(method);
    }*/
}
