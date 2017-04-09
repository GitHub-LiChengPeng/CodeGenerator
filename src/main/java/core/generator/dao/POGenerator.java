package core.generator.dao;

import core.database.Column;
import core.database.Table;
import core.file.java.Class;
import core.file.java.Field;
import core.file.java.Type;
import core.resolver.TypeResolver;

import java.util.List;

/**
 * 持久化对象(Persistent Object)生成器.
 *
 * @author 李程鹏
 */
public class POGenerator extends POJOGenerator {
    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造初始化实例.
     * </pre>
     *
     * @param table 表格对象
     */
    public POGenerator(Table table) {
        // 调用父类的构造方法
        super(table);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成代码.
     * </pre>
     *
     * @return {@code core.file.java.Class} - 生成的PO类
     */
    @Override
    public Class generate() {
        // 新建一个类
        Class class_ = new Class();
        // 设置类的文档注释
        generateFileDocument(class_, table.getRemark());
        // 设置类的注解
        class_.addAnnotation("@Entity");
        class_.addAnnotation("@Table(name = \"" + table.getName() + "\")");
        // 设置类的访问控制符
        class_.setVisibility("public ");
        // 设置类的类型
        class_.setType(classType);
        // 获取表中的所有列
        List<Column> columns = table.getColumns();
        // 遍历列集合
        for (Column column : columns) {
            // 新建一个属性
            Field field = new Field();
            // 为属性添加文档注释
            generateFieldDocument(field, column.getRemark());
            // 设置属性的访问控制符
            field.setVisibility("private ");
            // 设置属性的类型
            field.setType(new TypeResolver().resolve(column.getType()));
            // 设置属性名
            field.setName(getColumnName(column));
            // 如果该列是主键
            if (column.isPrimaryKey()) {
                // 为属性添加主键注解
                field.addAnnotation("@Id");
                // 为属性添加主键生成策略注解
                field.addAnnotation("@GeneratedValue(strategy = GenerationType.IDENTITY)");
            }
            // 为属性添加列标识注解
            field.addAnnotation("@Column(name = \"" + column.getName() + "\")");
            // 为类添加属性
            class_.addField(field);
            // 为类添加需要导入的类型
            class_.addImport(field.getType());
            class_.addImport(new Type("javax.persistence.*"));
            // 生成Getter方法
            generateGetterMethod(class_, field);
            // 生成Setter方法
            generateSetterMethod(class_, field);
        }
        // 返回POJO类
        return class_;
    }
}