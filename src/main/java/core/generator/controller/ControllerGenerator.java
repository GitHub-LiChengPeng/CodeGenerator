package core.generator.controller;

import core.file.java.*;
import core.database.Table;
import core.file.java.Class;
import core.generator.ClassGenerator;
import core.util.PackageUtils;

/**
 * Controller类生成器.
 *
 * @author 李程鹏
 */
public class ControllerGenerator extends ClassGenerator {
    /**
     * Service层接口的类型
     */
    private Type serviceType;

    /**
     * Service层接口的名字
     */
    private String serviceName;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造初始化生成器.
     * </pre>
     *
     * @param table 表格对象
     */
    public ControllerGenerator(Table table) {
        // 调用父类的构造函数
        super(table);
        // 初始化类的类型
        this.classType = new Type(PackageUtils.CONTROLLER.getValue() + getTableName() + "Controller");
        // 初始化Service层接口的类型
        this.serviceType = new Type(PackageUtils.SERVICE.getValue() + getTableName() + "Service");
        // 初始化Service层接口的名字
        this.serviceName = getCommonName("Service");
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成代码.
     * </pre>
     *
     * @return {@code core.file.java.Class} - 生成的Controller类.
     */
    @Override
    public Class generate() {
        // 新建一个类
        Class class_ = new Class();
        // 生成类的文档注释
        generateFileDocument(class_, "操作" + table.getRemark() + "对象的控制层代码");
        // 添加类的注解
        class_.addAnnotation("@Controller");
        class_.addAnnotation("@RequestMapping(\"/" + getCommonName("") + "\")");
        // 设置访问控制符
        class_.setVisibility("public ");
        // 设置类的类型
        class_.setType(classType);
        // 为类生成属性
        generateField(class_);
        // 为类生成增加方法
        generateAddMethod(class_);
        // 为类生成删除方法
        generateDeleteMethod(class_);
        // 为类生成修改方法
        generateUpdateMethod(class_);
        // 为类生成查询方法(按主键查).
        generateReadOneMethod(class_);
        // 为类生成查询方法(查询所有).
        generateReadAllMethod(class_);
        // 添加类需要导入的类型
        addImports(class_);
        // 返回生成的Controller类
        return class_;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加类需要导入的类型.
     * </pre>
     *
     * @param class_ 类
     */
    private void addImports(Class class_) {
        // 为类导入通用类型
        addCommonImports(class_);
        // 导入接口的类型
        class_.addImport(serviceType);
        // 导入实体类型
        class_.addImport(entityType);
        // 导入的这个包下包含@RequestMapping,@ResponseBody和@PathVariable
        class_.addImport(new Type("org.springframework.web.bind.annotation.*"));
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为类生成属性.
     * </pre>
     *
     * @param class_ 类
     */
    private void generateField(Class class_) {
        // 新建一个属性
        Field field = new Field();
        // 为属性添加文档注释
        generateFieldDocument(field, "业务层接口");
        // 为属性添加注解
        field.addAnnotation("@Autowired");
        field.addAnnotation("@Qualifier(\"" + serviceName + "\")");
        // 设置访问控制符
        field.setVisibility("private ");
        // 设置属性的类型
        field.setType(serviceType);
        // 设置属性名
        field.setName(serviceName);
        // 为类添加属性
        class_.addField(field);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为类生成增加方法.
     * </pre>
     *
     * @param class_ 类
     */
    private void generateAddMethod(Class class_) {
        // 新建一个方法
        Method method = new Method();
        // 放入增加方法的基本信息
        putAddMethodInfo(method);
        // 为方法添加注解
        method.addAnnotation("@RequestMapping(value = \"/add\", method = RequestMethod.POST)");
        method.addAnnotation("@ResponseBody");
        // 设置方法的返回值
        method.setType(new Type("java.lang.String"));
        // 为方法的参数添加注解
        method.getParameters().get(0).addAnnotation("@RequestBody");
        // 添加方法语句
        method.addStatement("// 调用业务层接口添加实体对象");
        method.addStatement(serviceName + ".addEntity(entity);");
        method.addStatement("// 返回增加操作结果");
        method.addStatement("return \"Add success!\";");
        // 为类添加方法
        class_.addMethod(method);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为类生成删除方法.
     * </pre>
     *
     * @param class_ 类
     */
    private void generateDeleteMethod(Class class_) {
        // 新建一个方法
        Method method = new Method();
        // 放入删除方法的基本信息
        putDeleteMethodInfo(method);
        // 为方法添加注解
        method.addAnnotation("@RequestMapping(value = \"/delete/{id}\", method = RequestMethod.GET)");
        method.addAnnotation("@ResponseBody");
        // 设置方法的返回值
        method.setType(new Type("java.lang.String"));
        // 为方法参数添加注解
        method.getParameters().get(0).addAnnotation("@PathVariable");
        // 添加方法语句
        method.addStatement("// 调用业务层接口删除实体对象");
        method.addStatement(serviceName + ".deleteEntity(id);");
        method.addStatement("// 返回删除操作结果");
        method.addStatement("return \"Delete success!\";");
        // 为类添加方法
        class_.addMethod(method);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为类生成修改方法.
     * </pre>
     *
     * @param class_ 类
     */
    private void generateUpdateMethod(Class class_) {
        // 新建一个方法
        Method method = new Method();
        // 放入修改方法的基本信息
        putUpdateMethodInfo(method);
        // 为方法添加注解
        method.addAnnotation("@RequestMapping(value = \"/update\", method = RequestMethod.POST)");
        method.addAnnotation("@ResponseBody");
        // 设置方法的返回值
        method.setType(new Type("java.lang.String"));
        // 为方法参数添加注解
        method.getParameters().get(0).addAnnotation("@RequestBody");
        // 添加方法语句
        method.addStatement("// 调用业务层接口修改实体对象");
        method.addStatement(serviceName + ".updateEntity(entity);");
        method.addStatement("// 返回修改操作结果");
        method.addStatement("return \"Update success!\";");
        // 为类添加方法
        class_.addMethod(method);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为类生成查询方法(按主键查).
     * </pre>
     *
     * @param class_ 类
     */
    private void generateReadOneMethod(Class class_) {
        // 新建一个方法
        Method method = new Method();
        // 放入查询方法的基本信息
        putReadOneMethodInfo(method);
        // 为方法参数添加注解
        method.getParameters().get(0).addAnnotation("@PathVariable");
        // 为方法添加注解
        method.addAnnotation("@RequestMapping(value = \"/read/{id}\", method = RequestMethod.GET)");
        method.addAnnotation("@ResponseBody");
        // 添加注释
        method.addStatement("// 调用业务层接口查询实体对象");
        // 添加方法语句
        method.addStatement("return " + serviceName + ".readEntity(id);");
        // 为类添加方法
        class_.addMethod(method);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为类生成查询方法(查询所有).
     * </pre>
     *
     * @param class_ 类
     */
    private void generateReadAllMethod(Class class_) {
        // 新建一个方法
        Method method = new Method();
        // 放入查询方法的基本信息
        putReadAllMethodInfo(method);
        // 为方法添加注解
        method.addAnnotation("@RequestMapping(value = \"/read\", method = RequestMethod.GET)");
        method.addAnnotation("@ResponseBody");
        // 添加注释
        method.addStatement("// 调用业务层接口查询实体对象集合");
        // 添加方法语句
        method.addStatement("return " + serviceName + ".readEntities();");
        // 为类导入返回值类型
        class_.addImport(method.getType());
        // 为类添加方法
        class_.addMethod(method);
    }
}